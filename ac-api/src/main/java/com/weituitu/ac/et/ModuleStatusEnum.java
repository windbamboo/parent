package com.weituitu.ac.et;

/**
 * @描述: 模块状态枚举
 * @作者:liuguozhu
 * @创建:2017/2/27-下午11:18
 * @版本:v1.0
 */
public enum ModuleStatusEnum {
    ACTIVE(1, "active"), EXPIRED(2, "expired");

    /**
     * 模块状态
     */
    private int status;

    /**
     * 模块名称
     */
    private String desc;

    ModuleStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
