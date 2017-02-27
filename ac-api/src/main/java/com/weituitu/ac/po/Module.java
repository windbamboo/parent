package com.weituitu.ac.po;

import java.util.Date;

public class Module {
    /**
     * 模块id
     */
    private String moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块父id
     */
    private String moduleFather;

    /**
     * 状态
     */
    private Byte status;

    /**
     * uri
     */
    private String uri;

    /**
     * 模块类型
     */
    private Byte moduleType;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 菜单栏图标
     */
    private String icon;

    /**
     * 是否显示
     */
    private Byte displayMenu;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleFather() {
        return moduleFather;
    }

    public void setModuleFather(String moduleFather) {
        this.moduleFather = moduleFather == null ? null : moduleFather.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Byte getModuleType() {
        return moduleType;
    }

    public void setModuleType(Byte moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Byte getDisplayMenu() {
        return displayMenu;
    }

    public void setDisplayMenu(Byte displayMenu) {
        this.displayMenu = displayMenu;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", moduleFather='" + moduleFather + '\'' +
                ", status=" + status +
                ", uri='" + uri + '\'' +
                ", moduleType=" + moduleType +
                ", appId=" + appId +
                ", icon='" + icon + '\'' +
                ", displayMenu=" + displayMenu +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}