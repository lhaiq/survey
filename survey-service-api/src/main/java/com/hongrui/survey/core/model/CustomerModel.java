package com.hongrui.survey.core.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hongrui.survey.core.entity.Customer")
public class CustomerModel{
	
	private Long id;
	private String name;
	private Long syndicId;
	private String company;
	private String address;
	private String idCard;
	private String mobileNumber;
	private String telephoneNumber;
	private Integer status;
	private String syndicName;
	private String point;
		
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
		
	public void setSyndicId(Long syndicId){
		this.syndicId = syndicId;
	}
	
	public Long getSyndicId(){
		return this.syndicId;
	}
		
	public void setCompany(String company){
		this.company = company;
	}
	
	public String getCompany(){
		return this.company;
	}
		
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
		
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	
	public String getIdCard(){
		return this.idCard;
	}
		
	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}
	
	public String getMobileNumber(){
		return this.mobileNumber;
	}
		
	public void setTelephoneNumber(String telephoneNumber){
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getTelephoneNumber(){
		return this.telephoneNumber;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}

	public void setSyndicName(String syndicName) {
		this.syndicName = syndicName;
	}

	public String getSyndicName() {
		return syndicName;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPoint() {
		return point;
	}
}