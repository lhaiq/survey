package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hongrui.survey.core.model.CoverZoneModel")
public class CoverZoneVO{
	
	private Long id;
	private String nation;
	private String district;
	private Long cdnid;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setNation(String nation){
		this.nation = nation;
	}
	
	public String getNation(){
		return this.nation;
	}
		
	public void setDistrict(String district){
		this.district = district;
	}
	
	public String getDistrict(){
		return this.district;
	}
		
	public void setCdnid(Long cdnid){
		this.cdnid = cdnid;
	}
	
	public Long getCdnid(){
		return this.cdnid;
	}
		
		
}