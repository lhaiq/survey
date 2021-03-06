package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.model.ReportModel")
public class ReportVO{
	
	private Long id;
	private String name;
	private Long taskId;
	private String content;
	private Date createTime;
	private Long templateId;
		
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
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setTemplateId(Long templateId){
		this.templateId = templateId;
	}
	
	public Long getTemplateId(){
		return this.templateId;
	}
		
		
}