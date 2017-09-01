package com.weituitu.email.conf;

import org.springframework.beans.factory.annotation.Value;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/28-下午9:01
 * @版本:v1.0
 */
public class ZipkinConfig {

    /**
     * 地址
     */
    @Value("${zipkin.server.host}")
    private String host;

    /**
     * 端口
     */
    @Value("${zipkin.server.port}")
    private String port = "9411";

    /**
     * 应用名称
     */
    @Value("${zipkin.server.application}")
    private String application;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
