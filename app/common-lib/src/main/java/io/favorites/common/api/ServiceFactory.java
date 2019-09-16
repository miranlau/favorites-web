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
                        Object invokeResult = realMethod.invoke(client,
                                targetArgs.toArray(new Object[targetArgs.size()]));
                        if (invokeResult != null) {
                            if (invokeResult instanceof PageResult) {
                                PageResult result = (PageResult) invokeResult;
                                Page page = result.getPage();
                                invokeResult = new PageImpl(result.getEmbedded().getData(),
                                        PageRequest.of(page.getNumber(), page.getSize(), sort),
                                        page.getTotalelements());
                            } else if (invokeResult instanceof ListResult) {
                                ListResult result = (ListResult) invokeResult;
                                invokeResult = result.getEmbedded().getData();
                            }
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
}
