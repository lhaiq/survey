package com.hongrui.survey.core.entity;

import java.util.Date;

public class Task {
    private Long id;

    private Long surveyorId;

    private Long customerId;

    private Date createTime;

    private Date checkTime;

    private String comment;

    private String point;

    private String type;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Date commitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyorId() {
        return surveyorId;
    }

    public void setSurveyorId(Long surveyorId) {
        this.surveyorId = surveyorId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }
}