package com.weituitu.task.treasure.conf;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return config;
    }
}
