package com.weituitu.id.conf;

import brave.Tracing;
import brave.context.log4j2.ThreadContextCurrentTraceContext;
import brave.http.HttpTracing;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.kristofa.brave.Brave;
import com.weituitu.motan.filter.MotanZipkinFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
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


    /**
     * Configuration for how to send spans to Zipkin
     */
    @Bean
    Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v1/spans");
    }

    /**
     * Configuration for how to buffer spans into messages for Zipkin
     */
    @Bean
    Reporter<Span> reporter() {
        return AsyncReporter.builder(sender()).build();
    }

    /**
     * Controls aspects of tracing such as the name that shows up in the UI
     */
    @Bean(name = MotanZipkinFilter.ZIPKIN_TRACING_BEAN_NAME)
    Tracing tracing() {
        return Tracing.newBuilder()
                .localServiceName(zipkinConfig().getApplication())
                .currentTraceContext(ThreadContextCurrentTraceContext.create()) // puts trace IDs into logs
                .reporter(reporter()).build();
    }

    // decides how to name and tag spans. By default they are named the same as the http method.
    @Bean
    HttpTracing httpTracing() {
        return HttpTracing.create(tracing());
    }

}
