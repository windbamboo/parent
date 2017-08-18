package com.weituitu.task.treasure.conf;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/17-下午4:26
 * @版本:v1.0
 */

@Configuration
@EnableApolloConfig(value = {"back-end.motan"}, order = 1)
public class AppConfig {
    @Bean
    public MotanConfig sampleRedisConfig() {
        return new MotanConfig();
    }


}
