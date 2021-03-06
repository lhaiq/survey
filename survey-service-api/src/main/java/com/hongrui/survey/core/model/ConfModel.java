package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hongrui.survey.core.entity.Conf")
public class ConfModel{
	
	private Long id;
	private String name;
	private String photoType;
	private String template;
	private String pixel;
	private Integer type;
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
		
	public void setPixel(String pixel){
		this.pixel = pixel;
	}
	
	public String getPixel(){
		return this.pixel;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		
		
}