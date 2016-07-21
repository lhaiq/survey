package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.entity.Sign")
public class SignModel{
	
	private Long id;
	private Long taskId;
	private Double signLatitude;
	private Double signLongitude;
	private Double actualLatitude;
	private Double actualLongitude;
	private Date createTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setTaskId(Long taskId){
		this.taskId = taskId;
	}
	
	public Long getTaskId(){
		return this.taskId;
	}
		
	public void setSignLatitude(Double signLatitude){
		this.signLatitude = signLatitude;
	}
	
	public Double getSignLatitude(){
		return this.signLatitude;
	}
		
	public void setSignLongitude(Double signLongitude){
		this.signLongitude = signLongitude;
	}
	
	public Double getSignLongitude(){
		return this.signLongitude;
	}
		
	public void setActualLatitude(Double actualLatitude){
		this.actualLatitude = actualLatitude;
	}
	
	public Double getActualLatitude(){
		return this.actualLatitude;
	}
		
	public void setActualLongitude(Double actualLongitude){
		this.actualLongitude = actualLongitude;
	}
	
	public Double getActualLongitude(){
		return this.actualLongitude;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
		
}