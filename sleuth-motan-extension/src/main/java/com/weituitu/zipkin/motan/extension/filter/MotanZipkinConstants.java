package com.weituitu.zipkin.motan.extension.filter;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/28-下午8:02
 * @版本:v1.0
 */
public class MotanZipkinConstants {
    public static final String SAMPLED_NAME = "X-B3-Sampled";

    public static final String PROCESS_ID_NAME = "X-Process-Id";

    public static final String PARENT_ID_NAME = "X-B3-ParentSpanId";

    public static final String TRACE_ID_NAME = "X-B3-TraceId";

    public static final String SPAN_NAME_NAME = "X-Span-Name";

    public static final String SPAN_ID_NAME = "X-B3-SpanId";

    public static final String SPAN_EXPORT_NAME = "X-Span-Export";

    public static final String SPAN_FLAGS = "X-B3-Flags";

    public static final String CLIENT_ADDR = "motan.remote_addr";

    public static final String MOTAN_STATUS_CODE = "motan.status_code";


}
