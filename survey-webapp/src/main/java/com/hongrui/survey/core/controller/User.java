package com.hongrui.survey.core.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户模型
 * 
 * @author fcj
 *
 **/
@JsonSerialize
public class User {
	
	private String id;
	private String userGroupId;
	private String username;
    private String password;
    private String deleteFlag;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private int    version;
	private Date lastSignTime;
    private String userPosition;
    private String chinaName;
	private String userSex;
    private BigDecimal userAge;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String remarks;

	public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getLastSignTime() {
		return lastSignTime;
	}

	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	
    public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}
	
	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}



	public BigDecimal getUserAge() {
		return userAge;
	}

	public void setUserAge(BigDecimal userAge) {
		this.userAge = userAge;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
    public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userGroupId=" + userGroupId
				+ ", username=" + username + ", password=" + password
				+ ", deleteFlag=" + deleteFlag + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", version=" + version
				+ ", lastSignTime=" + lastSignTime + ", userPosition="
				+ userPosition + ", chinaName=" + chinaName + ", userSex="
				+ userSex + ", userAge=" + userAge + ", userPhone=" + userPhone
				+ ", userEmail=" + userEmail + ", userAddress=" + userAddress
				+ ", remarks=" + remarks + "]";
	}

}