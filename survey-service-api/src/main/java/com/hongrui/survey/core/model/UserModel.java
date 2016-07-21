package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.entity.User")
public class UserModel{
	
	private Long id;
	private String account;
	private String password;
	private Date createTime;
	private Integer role;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getAccount(){
		return this.account;
	}
		
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setRole(Integer role){
		this.role = role;
	}
	
	public Integer getRole(){
		return this.role;
	}
		
		
}