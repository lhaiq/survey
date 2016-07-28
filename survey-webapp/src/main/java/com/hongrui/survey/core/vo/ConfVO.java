package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hongrui.survey.core.model.ConfModel")
public class ConfVO{
	
	private Long id;
	private String name;
	private String photoType;
	private String template;
	private String type;
	private String content;
		
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
		
	public void setPhotoType(String photoType){
		this.photoType = photoType;
	}
	
	public String getPhotoType(){
		return this.photoType;
	}
		
	public void setTemplate(String template){
		this.template = template;
	}
	
	public String getTemplate(){
		return this.template;
	}
		
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		

}