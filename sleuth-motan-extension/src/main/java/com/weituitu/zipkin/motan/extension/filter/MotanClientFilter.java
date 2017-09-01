package com.weituitu.zipkin.motan.extension.filter;

import com.github.kristofa.brave.*;
import com.github.kristofa.brave.internal.Nullable;
import com.twitter.zipkin.gen.Endpoint;
import com.weibo.api.motan.core.extension.Activation;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.filter.Filter;
import com.weibo.api.motan.rpc.Caller;
import com.weibo.api.motan.rpc.Request;
import com.weibo.api.motan.rpc.Response;
import com.weibo.api.motan.rpc.RpcContext;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/25-上午11:54
 * @版本:v1.0
 */
@SpiMeta(name = "sleuthfilter")
@Activation(sequence = 10)
public class MotanClientFilter implements Filter {

    public static final String BRAVE_ZIPKIN_BEAN_NAME = "spring-boot-brave-of-zipkin";


    private ClientRequestInterceptor clientRequestInterceptor;
    private ClientResponseInterceptor clientResponseInterceptor;
    private ClientSpanThreadBinder clientSpanThreadBinder;


    private Brave brave;

    @Override
    public Response filter(Caller<?> caller, Request request) {
        if (TracerContextAware.getApplicationContext().containsBean(BRAVE_ZIPKIN_BEAN_NAME)) {
            brave = (Brave) TracerContextAware.getApplicationContext().getBean(BRAVE_ZIPKIN_BEAN_NAME);
            this.clientRequestInterceptor = brave.clientRequestInterceptor();
            this.clientResponseInterceptor = brave.clientResponseInterceptor();
            this.clientSpanThreadBinder = brave.clientSpanThreadBinder();
            RpcContext context = RpcContext.getContext();
            com.twitter.zipkin.gen.Span currentClientSpan = null;
            Response response = null;

            System.out.println("client end#" + caller.getInterface() + "#" + request.getMethodName());
            try {
                clientRequestInterceptor.handle(new MotanClientRequestAdapter(caller, request, context));
                currentClientSpan = clientSpanThreadBinder.getCurrentClientSpan();
                response = caller.call(request);
                context.putAttribute(MotanZipkinConstants.MOTAN_STATUS_CODE, MotanStatus.success.name());
                clientSpanThreadBinder.setCurrentSpan(currentClientSpan);
                clientResponseInterceptor.handle(new MotanClientResponseAdapter(response, context));
                return response;
            } catch (Exception e) {
                context.putAttribute(MotanZipkinConstants.MOTAN_STATUS_CODE, MotanStatus.exception.name());
                clientSpanThreadBinder.setCurrentSpan(currentClientSpan);
                clientResponseInterceptor.handle(new MotanClientResponseAdapter(response, context));
                return caller.call(request);
            } finally {
                if (response != null) {
                    if (context.getAttribute(MotanZipkinConstants.MOTAN_STATUS_CODE).equals(MotanStatus.success)) {
                        clientSpanThreadBinder.setCurrentSpan(null);
                    }
                }
            }


        }
        return caller.call(request);
    }


    /**
     * Client request Adapter for Zipkin
     */
    static final class MotanClientRequestAdapter implements ClientRequestAdapter {

        private final Caller caller;
        private final Request request;
        private final RpcContext context;

        public MotanClientRequestAdapter(Caller caller, Request request, RpcContext context) {
            this.caller = checkNotNull(caller);
            this.request = checkNotNull(request);
            this.context = checkNotNull(context);
        }

        @Override
        public String getSpanName() {
            return "client call  " + request.getMethodName() + "()";
        }

        @Override
        public void addSpanIdToRequest(@Nullable SpanId spanId) {
            Map<String, String> at = this.request.getAttachments();
            if (spanId == null) {
                at.put(MotanZipkinConstants.SAMPLED_NAME, "0");
            } else {
                at.put(MotanZipkinConstants.SAMPLED_NAME, "1");
                at.put(MotanZipkinConstants.TRACE_ID_NAME, IdConversion.convertToString(spanId.traceId));
                at.put(MotanZipkinConstants.SPAN_ID_NAME, IdConversion.convertToString(spanId.spanId));
                if (spanId.nullableParentId() != null) {
                    at.put(MotanZipkinConstants.PARENT_ID_NAME, IdConversion.convertToString(spanId.parentId));
                }
            }
            System.out.println("客户端初始化tracer数据，traceId=" + spanId.traceId + "，SpanId=" + spanId.spanId);
        }

        @Override
        public Collection<KeyValueAnnotation> requestAnnotations() {
            return Collections.emptyList();
        }

        @Override
        public Endpoint serverAddress() {
            return null;
        }
    }

    /**
     * Client Response Adapter for Zipkin
     */
    static final class MotanClientResponseAdapter implements ClientResponseAdapter {

        private final Response response;
        private final RpcContext context;

        public MotanClientResponseAdapter(Response response, RpcContext context) {
            this.response = checkNotNull(response);
            this.context = checkNotNull(context);
        }

        /**
         * submit KeyValue Annotation data
         */
        @Override
        public Collection<KeyValueAnnotation> responseAnnotations() {
            MotanStatus stats = MotanStatus.parse(context.getAttribute(MotanZipkinConstants.MOTAN_STATUS_CODE));
            return Collections.singletonList(KeyValueAnnotation.create(MotanZipkinConstants.MOTAN_STATUS_CODE, stats.name()));
        }
    }


}
