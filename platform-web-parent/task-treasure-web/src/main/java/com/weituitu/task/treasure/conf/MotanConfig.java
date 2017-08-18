package com.weituitu.task.treasure.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @描述: motan可能需要动态修改的配置
 * @作者:liuguozhu
 * @创建:2017/8/17-上午11:23
 * @版本:v1.0
 */
@ConfigurationProperties(prefix = "motan")
public class MotanConfig {

    //注册中心协议
    private String regProtocol;

    //注册中心地址
    private String regAddress;

    // if throw exception when call failure，the default value is ture
    protected Boolean throwException;

    // 请求超时时间
    protected Integer requestTimeout;

    // 是否记录访问日志，true记录，false不记录
    protected String accessLog;

    // 是否进行check，如果为true，则在监测失败后抛异常
    protected String check;

    // 重试次数
    protected Integer retries;

    public String getRegProtocol() {
        return regProtocol;
    }

    public void setRegProtocol(String regProtocol) {
        this.regProtocol = regProtocol;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public Boolean getThrowException() {
        return throwException;
    }

    public void setThrowException(Boolean throwException) {
        this.throwException = throwException;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getAccessLog() {
        return accessLog;
    }

    public void setAccessLog(String accessLog) {
        this.accessLog = accessLog;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }
}
