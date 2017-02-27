package com.weituitu.ac.et;

/**
 * @描述: 模块类型描述
 * @作者:liuguozhu
 * @创建:2017/2/27-下午11:29
 * @版本:v1.0
 */
public enum ModuleTypeEnum {
    APP_ROOT((byte) 1, "root"),
    URI((byte) 2, "uri"),
    FUNCTION((byte) 3, "function");

    /**
     * 模块类型
     */
    private byte moduleType;
    /**
     * 模块描述
     */
    private String desc;

    ModuleTypeEnum(byte moduleType, String desc) {
        this.moduleType = moduleType;
        this.desc = desc;
    }

    public byte getModuleType() {
        return moduleType;
    }

    public String getDesc() {
        return desc;
    }
}
