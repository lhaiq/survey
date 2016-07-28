package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.entity.Photo")
public class PhotoModel{
	
	private Long id;
	private String name;
	private Long taskId;
	private Long photoType;
	private String path;
	private Date createTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setTaskId(Long taskId){
		this.taskId = taskId;
	}
	
	public Long getTaskId(){
		return this.taskId;
	}
		
	public void setPhotoType(Long photoType){
		this.photoType = photoType;
	}
	
	public Long getPhotoType(){
		return this.photoType;
	}
		
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return this.path;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
		
}