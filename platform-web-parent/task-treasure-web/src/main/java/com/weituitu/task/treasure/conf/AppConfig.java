package com.weituitu.task.treasure.conf;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.kristofa.brave.Brave;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/18-下午2:36
 * @版本:v1.0
 */
@Configuration
@EnableApolloConfig(value = {"back-end.motan"}, order = 1)
public class AppConfig {


    /**
     * motan 配置文件
     */
    @Autowired
    private MotanConfig motanConfig;


    /**
     * motan配置文件
     *
     * @return
     */
    @Bean
    public MotanConfig motanConfig() {
        motanConfig = new MotanConfig();
        return motanConfig;
    }


    @Bean
    public ZipkinConfig zipkinConfig() {
        return new ZipkinConfig();
    }


    public static final String BRAVE_ZIPKIN_BEAN_NAME = "spring-boot-brave-of-zipkin";

    @Bean(name = BRAVE_ZIPKIN_BEAN_NAME)
    public Brave brave(ZipkinConfig zipkinConfig) {
        System.out.println("读取配置文件:" + zipkinConfig.toString());
        Brave.Builder builder = new Brave.Builder(zipkinConfig.getApplication())
                .reporter(
                        AsyncReporter
                                .builder(
                                        // okhttp3
                                        OkHttpSender.builder().endpoint("http://" + zipkinConfig.getHost() + ":9411/api/v1/spans").compressionEnabled(true).build()
                                )
                                .build()
                );
        Brave brave = builder.build();
        System.out.println("初始化 Brave : " + brave);

        return brave;
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
        config.setRegProtocol(motanConfig.getRegProtocol());
        config.setAddress(motanConfig.getRegAddress());
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
        config.setCheck(motanConfig.getCheck());
        config.setAccessLog(motanConfig.getAccessLog());
        config.setRetries(motanConfig.getRetries());
        config.setThrowException(motanConfig.getThrowException());
        config.setRequestTimeout(motanConfig.getRequestTimeout());
        config.setFilter("zipkinfilter");
        return config;
    }
}
