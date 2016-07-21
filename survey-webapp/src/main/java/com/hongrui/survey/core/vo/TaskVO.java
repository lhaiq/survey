package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hongrui.survey.core.model.TaskModel")
public class TaskVO{
	
	private Long id;
	private Long surveyorId;
	private Long customerId;
	private Date createTime;
	private Date checkTime;
	private String comment;
	private String point;
	private String type;
	private Integer status;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setSurveyorId(Long surveyorId){
		this.surveyorId = surveyorId;
	}
	
	public Long getSurveyorId(){
		return this.surveyorId;
	}
		
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	
	public Long getCustomerId(){
		return this.customerId;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCheckTime(Date checkTime){
		this.checkTime = checkTime;
	}
	
	public Date getCheckTime(){
		return this.checkTime;
	}
		
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return this.comment;
	}
		
	public void setPoint(String point){
		this.point = point;
	}
	
	public String getPoint(){
		return this.point;
	}
		
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}