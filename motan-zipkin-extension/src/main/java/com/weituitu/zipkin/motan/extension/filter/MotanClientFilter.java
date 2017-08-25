package com.weituitu.zipkin.motan.extension.filter;

import com.weibo.api.motan.core.extension.Activation;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.filter.Filter;
import com.weibo.api.motan.rpc.Caller;
import com.weibo.api.motan.rpc.Request;
import com.weibo.api.motan.rpc.Response;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.trace.DefaultTracer;
import org.springframework.context.ApplicationContextAware;

/**
 * @描述: motan zipkin客户端扩展,该类主要是想利用springcloud封装的一套接口带来的便利，继续使用它。
 * TODO  异步处理获得当前的request参数方法用InheritableThreadLocal，后期支持扩展异步处理获得tracer
 * @作者:liuguozhu
 * @创建:2017/8/25-上午11:54
 * @版本:v1.0
 */
@SpiMeta(name = "clientopentracing")
@Activation(sequence = 10)
public class MotanClientFilter implements Filter {

    public static DefaultTracer tracer = null;

    @Override
    public Response filter(Caller<?> caller, Request request) {
        System.out.println("client opentracing");
        System.out.println("add client paramaters");
        tracer = (DefaultTracer) TracerContextAware.getApplicationContext().getBean("sleuthTracer");
        if (null != tracer) {
            Span span = tracer.getCurrentSpan();
            if (null != span) {
                long traceId = span.getTraceId();
                long spanId = span.getSpanId();
                String spanName = span.getName();
                System.out.println(span);
                request.getAttachments().put(OpentracingMotankeys.SAMPLED, "1");
                request.getAttachments().put(OpentracingMotankeys.TRACEID, String.valueOf(traceId));
                request.getAttachments().put(OpentracingMotankeys.SPANID, String.valueOf(spanId));
            }
            return caller.call(request);
        }
        return caller.call(request);

    }


}
