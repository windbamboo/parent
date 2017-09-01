package com.weituitu.message.conf;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.kristofa.brave.Brave;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/28-下午9:02
 * @版本:v1.0
 */
@Configuration
@EnableApolloConfig
public class AppConfig {
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

}
