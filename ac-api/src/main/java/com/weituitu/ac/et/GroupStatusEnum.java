package com.weituitu.ac.et;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/2/27-下午11:25
 * @版本:v1.0
 */
public enum GroupStatusEnum {
    ACTIVE(1, "active"), EXPIRED(2, "expired");

    /**
     * 模块状态
     */
    private int status;

    /**
     * 模块名称
     */
    private String desc;


    GroupStatusEnum(int status, String desc) {
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
