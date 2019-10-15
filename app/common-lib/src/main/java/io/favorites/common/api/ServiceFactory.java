/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package io.favorites.common.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import io.favorites.common.api.PageResult.Page;

/**
 * The purpose of this class is to create service dynamic proxy from a
 * client(e.g. Feign).
 */
public class ServiceFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(ServiceFactory.class);

    @SuppressWarnings("unchecked")
    public static <T> T createService(Class<T> serviceInterface, Object client) {
        return (T) Proxy.newProxyInstance(client.getClass().getClassLoader(), new Class[] { serviceInterface },
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Class<?>[] sourceParameterTypes = method.getParameterTypes();
                        List<Class<?>> targetParameterTypes = new ArrayList<Class<?>>(sourceParameterTypes.length);
                        for (Class<?> type : sourceParameterTypes) {
                            if (Pageable.class.isAssignableFrom(type)) {
                                targetParameterTypes.add(Integer.class);
                                targetParameterTypes.add(Integer.class);
                                targetParameterTypes.add(String.class);
                                continue;
                            }
                            targetParameterTypes.add(type);
                        }
                        Method realMethod = client.getClass().getMethod(method.getName(),
                                targetParameterTypes.toArray(new Class[targetParameterTypes.size()]));
                        List<Object> targetArgs = new ArrayList<Object>(targetParameterTypes.size());
                        Sort sort = null;
                        for (int i = 0; i < sourceParameterTypes.length; ++i) {
                            Object arg = args[i];
                            if (Pageable.class.isAssignableFrom(sourceParameterTypes[i])) {
                                if (arg == null) {
                                    targetArgs.add(null);
                                    targetArgs.add(null);
                                    targetArgs.add(null);
                                    continue;
                                }
                                Pageable p = (Pageable) arg;
                                targetArgs.add(p.getPageNumber());
                                targetArgs.add(p.getPageSize());
                                sort = p.getSort();
                                targetArgs.add(convertSortToString(sort));
                                continue;
                            }
                            targetArgs.add(arg);
                        }
                        Object invokeResult = null;
                        try {
                            invokeResult = realMethod.invoke(client, targetArgs.toArray(new Object[targetArgs.size()]));
                        } catch (Exception e) {
                            if (e.getCause().getClass().getName().endsWith("FeignException")) {
                                LOGGER.debug("some exception in calling remote service, it's better to "
                                        + "response a list other than an object from remote service "
                                        + "because object NotFound conflict with path NotFound", e);
                            } else {
                                LOGGER.error("some exception in calling remote service", e);
                            }
                        }
                        if (invokeResult != null) {
                            if (invokeResult instanceof PageResult) {
                                PageResult result = (PageResult) invokeResult;
                                Page page = result.getPage();
                                invokeResult = new PageImpl(getActualData(result.getEmbedded().getData(), realMethod),
                                        PageRequest.of(page.getNumber(), page.getSize(), sort),
                                        page.getTotalelements());
                            } else if (invokeResult instanceof ListResult) {
                                ListResult result = (ListResult) invokeResult;
                                invokeResult = getActualData(result.getEmbedded().getData(), realMethod);
                            }
                        } else {
                            LOGGER.info("remote service " + serviceInterface.getSimpleName() + "." + method.getName()
                                    + " return null");
                        }
                        return invokeResult;
                    }
                });
    }

    static String convertSortToString(Sort sort) {
        StringBuilder builder = new StringBuilder();
        if (sort != null) {
            sort.iterator().forEachRemaining(new Consumer<Order>() {

                @Override
                public void accept(Order t) {
                    builder.append(t.getProperty());
                    builder.append(",");
                    builder.append(t.getDirection());
                    builder.append("&sort=");
                }
            });
        }
        return builder.toString();
    }

    static List<?> getActualData(List<?> originalData, Method method) {
        List<Object> actualData = new ArrayList<Object>();
        List<BasicType<?>> data = (List<BasicType<?>>) originalData;
        try {
            for (BasicType<?> item : data) {
                actualData.addAll(item.getData().values());
            }
        } catch (ClassCastException e) {
            LOGGER.info("result in response is not basic type");
            return originalData;
        }
        return actualData;
    }
}
