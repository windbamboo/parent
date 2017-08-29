package com.weituitu.zipkin.motan.extension.filter;

import com.weibo.api.motan.core.extension.Activation;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.filter.Filter;
import com.weibo.api.motan.rpc.Caller;
import com.weibo.api.motan.rpc.Request;
import com.weibo.api.motan.rpc.Response;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.trace.DefaultTracer;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/25-上午11:54
 * @版本:v1.0
 */
@SpiMeta(name = "sleuthfilter")
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
                System.out.println(span);
                request.getAttachments().put(Span.TRACE_ID_NAME, Span.idToHex(span.getTraceId()));
                request.getAttachments().put(Span.SPAN_NAME_NAME, span.getName());
                request.getAttachments().put(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
                request.getAttachments().put(Span.SAMPLED_NAME, span.isExportable() ?
                        Span.SPAN_SAMPLED : Span.SPAN_NOT_SAMPLED);
                request.getAttachments().put(Span.PARENT_ID_NAME, getParentIdIfPresent(span));
                System.out.println("sleuth filter traceId=" + Span.idToHex(span.getTraceId()));
            }
            return caller.call(request);
        }
        return caller.call(request);

    }


    private String getParentIdIfPresent(Span span) {
        return span.getParents().isEmpty() ? "null" : Span.idToHex(span.getParents().get(0));
    }

}
