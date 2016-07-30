package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.model.AudioModel")
public class AudioVO{
	
	private Long id;
	private String name;
	private Long taskId;
	private String path;
	private Date createTime;
	private String contentType;
	private Integer type;
	private Integer sequence;
		
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
		
	public void setContentType(String contentType){
		this.contentType = contentType;
	}
	
	public String getContentType(){
		return this.contentType;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setSequence(Integer sequence){
		this.sequence = sequence;
	}
	
	public Integer getSequence(){
		return this.sequence;
	}
		
		
}