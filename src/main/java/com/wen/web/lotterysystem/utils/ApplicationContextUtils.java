package com.wen.web.lotterysystem.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author admin
 * @date 2018-11-19 10:10
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * @param name bean的名字或者类的全路径，如果找不到对应的bean，再通过类名查找
     * @return 返回一个bean对象
     */
    public static Object getBean(String name) {
        Object object = null;
        if (applicationContext.containsBean(name)) {
            object = applicationContext.getBean(name);
        } else {
            Class<?> requiredType = null;
            try {
                requiredType = Class.forName(name);
                object = getBean(requiredType);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个Bean
     *
     * @param requiredType bean的类型
     * @return 返回一个bean对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
