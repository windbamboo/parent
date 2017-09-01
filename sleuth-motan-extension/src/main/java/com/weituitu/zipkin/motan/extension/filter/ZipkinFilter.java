package com.weituitu.zipkin.motan.extension.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/29-下午6:14
 * @版本:v1.0
 */
public class ZipkinFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter begin");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter end");
    }

    @Override
    public void destroy() {

    }
}
