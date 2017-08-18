package com.weituitu.task.treasure;

import com.weibo.api.motan.closable.ShutDownHookListener;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
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

    /**
     * 设置motan协议
     *
     * @return
     */
    @Bean(name = "protocol")
    public ProtocolConfigBean protocolConfig() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        config.setMaxContentLength(1048576);
        return config;
    }

    /**
     * motan注册中心
     *
     * @return
     */
    @Bean(name = "registry")
    public RegistryConfigBean registryConfig() {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setRegProtocol("zookeeper");
        config.setAddress("127.0.0.1:2181");
        config.setDefault(true);
        return config;
    }

    /**
     * motan基础配置
     *
     * @return
     */
    @Bean(name = "motanClientBasicConfig")
    public BasicRefererConfigBean baseRefererConfig() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        config.setProtocol("protocol");
        config.setRegistry("registry");
        //检查服务提供者是否存在
        config.setCheck(false);
        config.setAccessLog(true);
        config.setRetries(2);
        config.setThrowException(true);
        config.setRequestTimeout(10000);
        return config;
    }

}
