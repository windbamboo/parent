package com.weituitu.zipkin.motan.extension.filter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/25-下午4:54
 * @版本:v1.0
 */
@Component
public class TracerContextAware implements ApplicationContextAware {


    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }
}