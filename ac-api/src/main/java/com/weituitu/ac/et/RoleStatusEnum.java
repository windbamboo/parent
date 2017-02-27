package com.weituitu.ac.et;

/**
 * @描述: 角色状态枚举类型
 * @作者:liuguozhu
 * @创建:2017/2/27-下午11:18
 * @版本:v1.0
 */
public enum RoleStatusEnum {
    ACTIVE(1, "active"), EXPIRED(2, "expired");

    /**
     * 角色状态
     */
    private int status;

    /**
     * 描述
     */
    private String desc;

    RoleStatusEnum(int status, String desc) {
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
