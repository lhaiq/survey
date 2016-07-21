package com.hongrui.survey.core.entity;

import java.util.Date;

public class Sign {
    private Long id;

    private Long taskId;

    private Double signLatitude;

    private Double signLongitude;

    private Double actualLatitude;

    private Double actualLongitude;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Double getSignLatitude() {
        return signLatitude;
    }

    public void setSignLatitude(Double signLatitude) {
        this.signLatitude = signLatitude;
    }

    public Double getSignLongitude() {
        return signLongitude;
    }

    public void setSignLongitude(Double signLongitude) {
        this.signLongitude = signLongitude;
    }

    public Double getActualLatitude() {
        return actualLatitude;
    }

    public void setActualLatitude(Double actualLatitude) {
        this.actualLatitude = actualLatitude;
    }

    public Double getActualLongitude() {
        return actualLongitude;
    }

    public void setActualLongitude(Double actualLongitude) {
        this.actualLongitude = actualLongitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}