package com.weituitu.task.treasure;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import com.weibo.api.motan.closable.ShutDownHookListener;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * @描述:任务宝项目启动类
 * @作者:liuguozhu
 * @创建:2017/8/16-下午10:45
 * @版本:v1.0
 */
@ServletComponentScan
@SpringBootApplication
public class Application implements ServletContextInitializer {

    public static void main(String[] args) {
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
        Brave.Builder builder = new Brave.Builder("web")
                .reporter(
                        AsyncReporter
                                .builder(
                                        // okhttp3
                                        OkHttpSender.builder().endpoint("http://" + "127.0.0.1" + ":9411/api/v1/spans").compressionEnabled(true).build()
                                )
                                .build()
                );
        Brave brave = builder.build();
        try {
            servletContext.addFilter("BraveServletFilter", BraveServletFilter.create(brave))
                    .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("#############" + e);
        }


    }

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("com.weituitu");
        return motanAnnotationBean;
    }


}
