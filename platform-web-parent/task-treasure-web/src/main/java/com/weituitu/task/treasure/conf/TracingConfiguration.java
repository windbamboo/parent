package com.weituitu.task.treasure.conf;


import brave.Tracing;
import brave.context.log4j2.ThreadContextCurrentTraceContext;
import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import brave.spring.webmvc.TracingHandlerInterceptor;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.weituitu.motan.filter.MotanZipkinFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.annotation.PostConstruct;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/9/1-下午4:55
 * @版本:v1.0
 */
@Configuration
// Importing these classes is effectively the same as declaring bean methods
@Import({TracingClientHttpRequestInterceptor.class, TracingHandlerInterceptor.class})
public class TracingConfiguration extends WebMvcConfigurerAdapter {

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
    Tracing tracing(ZipkinConfig zipkinConfig) {
        return Tracing.newBuilder()
                .localServiceName(zipkinConfig.getApplication())
                .currentTraceContext(ThreadContextCurrentTraceContext.create()) // puts trace IDs into logs
                .reporter(reporter()).build();
    }

    // decides how to name and tag spans. By default they are named the same as the http method.
    @Bean
    HttpTracing httpTracing(ZipkinConfig zipkinConfig) {
        return HttpTracing.create(tracing(zipkinConfig));
    }

    @Autowired
    private TracingHandlerInterceptor serverInterceptor;

    @Autowired
    private TracingClientHttpRequestInterceptor clientInterceptor;

    @Bean
    public SpanNameProvider spanNameProvider() {
        return new DefaultSpanNameProvider() {
            @Override
            public String spanName(com.github.kristofa.brave.http.HttpRequest request) {
                StringBuilder sb = new StringBuilder();
                String name = request.getHttpMethod();
                sb.append(name);
                sb.append("_");
                String uri = request.getUri().getPath();
                sb.append(uri);
                return sb.toString();
            }
        };

    }




    @PostConstruct
    public void init() {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptor);
    }
}