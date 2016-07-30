package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;

import java.util.Date;
import java.util.List;
import java.util.Map;

@MapClass("com.hongrui.survey.core.entity.Task")
public class TaskDetailModel {
	
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

	public List<ReportModel> reports;
	public List<AudioModel> audios;
	public Map<String,List<PhotoModel>> photos;
		
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
		
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
		
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}

	public List<AudioModel> getAudios() {
		return audios;
	}

	public void setAudios(List<AudioModel> audios) {
		this.audios = audios;
	}

	public Map<String, List<PhotoModel>> getPhotos() {
		return photos;
	}

	public void setPhotos(Map<String, List<PhotoModel>> photos) {
		this.photos = photos;
	}

	public List<ReportModel> getReports() {
		return reports;
	}

	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}
}