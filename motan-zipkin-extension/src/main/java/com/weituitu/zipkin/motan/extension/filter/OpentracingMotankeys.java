package com.weituitu.zipkin.motan.extension.filter;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/25-下午5:32
 * @版本:v1.0
 */
public class OpentracingMotankeys {

    public static String PARENTSPANID = OpenTracingMotanHeaders.ParentSpanId.getName();

    public static String SPANID = OpenTracingMotanHeaders.SpanId.getName();

    public static String TRACEID = OpenTracingMotanHeaders.TraceId.getName();

    public static String SAMPLED = OpenTracingMotanHeaders.Sampled.getName();

}
