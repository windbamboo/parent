package com.weituitu.task.treasure;

import com.weibo.api.motan.closable.ShutDownHookListener;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.filter.opentracing.OpenTracingContext;
import com.weituitu.zipkin.tracer.MyTracerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @描述:任务宝项目启动类
 * @作者:liuguozhu
 * @创建:2017/8/16-下午10:45
 * @版本:v1.0
 */
@SpringBootApplication
public class Application implements ServletContextInitializer {

    public static void main(String[] args) {
        // set tracer implementation
        OpenTracingContext.tracerFactory = new MyTracerFactory("task-treasure");
        SpringApplication.run(Application.class, args);
    }

    /**
     * 添加关闭motan客户端监听器
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new ShutDownHookListener());
    }

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("com.weituitu.task.treasure");
        return motanAnnotationBean;
    }

}
