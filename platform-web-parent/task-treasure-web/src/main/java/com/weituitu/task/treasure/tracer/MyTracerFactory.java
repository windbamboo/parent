package com.weituitu.task.treasure.tracer;


import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.HttpSpanCollector;
import io.opentracing.Tracer;

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
    HttpSpanCollector.Config spanConfig = HttpSpanCollector.Config.builder().compressionEnabled(false)//默认false，span在transport之前是否会被gzipped。
            .connectTimeout(5000)//5s，默认10s
            .flushInterval(1)//1s
            .readTimeout(6000)//5s，默认60s
            .build();

    final Tracer braveTracer = new BraveTracer((new Brave.Builder("service1"))
            .spanCollector(HttpSpanCollector
                    .create("http://127.0.0.1:9411", spanConfig, new EmptySpanCollectorMetricsHandler())));

    @Override
    public Tracer getTracer() {
        return braveTracer;
    }

}
