package com.favorites.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContextReference;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContextReference = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContextReference.getBean(beanName);
    }
}
