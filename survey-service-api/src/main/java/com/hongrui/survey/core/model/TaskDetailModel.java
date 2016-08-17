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
	private String summary;

	private CustomerModel customer;
	private Map<String, ReportConfModel> reports;
	private List<AudioModel> audios;
	private Map<String,PhotoConfModel> photos;
		
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

	public void setPhotos(Map<String, PhotoConfModel> photos) {
		this.photos = photos;
	}


	public Map<String, PhotoConfModel> getPhotos() {
		return photos;
	}

	public void setReports(Map<String, ReportConfModel> reports) {
		this.reports = reports;
	}

	public Map<String, ReportConfModel> getReports() {
		return reports;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}
}