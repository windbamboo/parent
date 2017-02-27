package com.weituitu.ac.et;

/**
 * @描述:账号状态枚举
 * @作者:liuguozhu
 * @创建:2017/2/27-下午11:13
 * @版本:v1.0
 */
public enum AccountStatusEnum {
    ACTIVE((byte) 1, "active"),
    FREEZE((byte) 2, "freeze"),
    EXPIRED((byte) 3, "expired");
    /**
     * 状态
     */
    private byte status;
    /**
     * 描述
     */
    private String desc;

    AccountStatusEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
