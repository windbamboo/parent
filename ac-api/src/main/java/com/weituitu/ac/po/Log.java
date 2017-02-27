package com.weituitu.ac.po;

import java.util.Date;

public class Log {
    private Long logId;

    private Integer userId;

    private String moduleId;

    private Byte actionType;

    private Date actionTime;

    private String actionDetail;

    private String userIp;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public Byte getActionType() {
        return actionType;
    }

    public void setActionType(Byte actionType) {
        this.actionType = actionType;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail == null ? null : actionDetail.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}