package com.weituitu.ac.et;

/**
 * @描述: 应用状态枚举
 * @作者:liuguozhu
 * @创建:2017/2/27-下午8:47
 * @版本:v1.0
 */
public enum ApplicationEnum {
    ;

    /**
     * 应用id
     */
    private int appId;
    /**
     * 应用名称
     */
    private String desc;

    ApplicationEnum(int appId, String desc) {
        this.appId = appId;
        this.desc = desc;
    }

    public int getAppId() {
        return appId;
    }

    public String getDesc() {
        return desc;
    }
}
