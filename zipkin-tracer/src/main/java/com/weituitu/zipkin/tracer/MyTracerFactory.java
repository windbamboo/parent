package com.weituitu.zipkin.tracer;


import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import com.weibo.api.motan.filter.opentracing.TracerFactory;
import io.opentracing.Tracer;
import io.opentracing.impl.BraveTracer;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/21-上午10:30
 * @版本:v1.0
 */


public class MyTracerFactory implements TracerFactory {

    private String serviceName;

    HttpSpanCollector.Config spanConfig = HttpSpanCollector.Config.builder().compressionEnabled(false)//默认false，span在transport之前是否会被gzipped。
            .connectTimeout(5000)//5s，默认10s
            .flushInterval(1)//1s
            .readTimeout(6000)//5s，默认60s
            .build();

    public MyTracerFactory(String serviceName) {
        this.serviceName = serviceName;
    }


    @Override
    public Tracer getTracer() {
        final Tracer braveTracer = new BraveTracer((new Brave.Builder(serviceName))
                .spanCollector(HttpSpanCollector
                        .create("http://127.0.0.1:9411", spanConfig, new EmptySpanCollectorMetricsHandler())));

        return braveTracer;
    }

}
