package com.hongrui.survey.core.vo;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;
import java.math.BigDecimal;

@MapClass("com.hongrui.survey.core.model.OrderModel")
public class OrderVO{
	
	private Integer ordersid;
	private Integer userid;
	private Integer storeid;
	private Integer deliverid;
	private String orderid;
	private String shipno;
	private String shipName;
	private String storeAddress;
	private String storePhone;
	private Date catchtime;
	private Integer sid;
	private BigDecimal totalAmount;
	private BigDecimal ordertotal;
	private BigDecimal pointsFare;
	private Byte invoice;
	private BigDecimal invoiceFare;
	private Integer osid;
	private Byte afterSaleStatus;
	private String sendway;
	private Byte smid;
	private BigDecimal fare;
	private Date sendtime;
	private Date gettime;
	private String orderUrl;
	private Integer rolesid;
	private Integer retailid;
	private Integer scid;
	private Byte userDel;
	private Byte storeDel;
	private Byte isReview;
	private Date lastTime;
	private Date payTime;
	private Byte addVersion;
	private Byte isReturngoods;
	private Integer postponeShip;
	private Integer postponeCountUser;
	private Integer postponeCountStore;
	private String remark;
		
	public void setOrdersid(Integer ordersid){
		this.ordersid = ordersid;
	}
	
	public Integer getOrdersid(){
		return this.ordersid;
	}
		
	public void setUserid(Integer userid){
		this.userid = userid;
	}
	
	public Integer getUserid(){
		return this.userid;
	}
		
	public void setStoreid(Integer storeid){
		this.storeid = storeid;
	}
	
	public Integer getStoreid(){
		return this.storeid;
	}
		
	public void setDeliverid(Integer deliverid){
		this.deliverid = deliverid;
	}
	
	public Integer getDeliverid(){
		return this.deliverid;
	}
		
	public void setOrderid(String orderid){
		this.orderid = orderid;
	}
	
	public String getOrderid(){
		return this.orderid;
	}
		
	public void setShipno(String shipno){
		this.shipno = shipno;
	}
	
	public String getShipno(){
		return this.shipno;
	}
		
	public void setShipName(String shipName){
		this.shipName = shipName;
	}
	
	public String getShipName(){
		return this.shipName;
	}
		
	public void setStoreAddress(String storeAddress){
		this.storeAddress = storeAddress;
	}
	
	public String getStoreAddress(){
		return this.storeAddress;
	}
		
	public void setStorePhone(String storePhone){
		this.storePhone = storePhone;
	}
	
	public String getStorePhone(){
		return this.storePhone;
	}
		
	public void setCatchtime(Date catchtime){
		this.catchtime = catchtime;
	}
	
	public Date getCatchtime(){
		return this.catchtime;
	}
		
	public void setSid(Integer sid){
		this.sid = sid;
	}
	
	public Integer getSid(){
		return this.sid;
	}
		
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getTotalAmount(){
		return this.totalAmount;
	}
		
	public void setOrdertotal(BigDecimal ordertotal){
		this.ordertotal = ordertotal;
	}
	
	public BigDecimal getOrdertotal(){
		return this.ordertotal;
	}
		
	public void setPointsFare(BigDecimal pointsFare){
		this.pointsFare = pointsFare;
	}
	
	public BigDecimal getPointsFare(){
		return this.pointsFare;
	}
		
	public void setInvoice(Byte invoice){
		this.invoice = invoice;
	}
	
	public Byte getInvoice(){
		return this.invoice;
	}
		
	public void setInvoiceFare(BigDecimal invoiceFare){
		this.invoiceFare = invoiceFare;
	}
	
	public BigDecimal getInvoiceFare(){
		return this.invoiceFare;
	}
		
	public void setOsid(Integer osid){
		this.osid = osid;
	}
	
	public Integer getOsid(){
		return this.osid;
	}
		
	public void setAfterSaleStatus(Byte afterSaleStatus){
		this.afterSaleStatus = afterSaleStatus;
	}
	
	public Byte getAfterSaleStatus(){
		return this.afterSaleStatus;
	}
		
	public void setSendway(String sendway){
		this.sendway = sendway;
	}
	
	public String getSendway(){
		return this.sendway;
	}
		
	public void setSmid(Byte smid){
		this.smid = smid;
	}
	
	public Byte getSmid(){
		return this.smid;
	}
		
	public void setFare(BigDecimal fare){
		this.fare = fare;
	}
	
	public BigDecimal getFare(){
		return this.fare;
	}
		
	public void setSendtime(Date sendtime){
		this.sendtime = sendtime;
	}
	
	public Date getSendtime(){
		return this.sendtime;
	}
		
	public void setGettime(Date gettime){
		this.gettime = gettime;
	}
	
	public Date getGettime(){
		return this.gettime;
	}
		
	public void setOrderUrl(String orderUrl){
		this.orderUrl = orderUrl;
	}
	
	public String getOrderUrl(){
		return this.orderUrl;
	}
		
	public void setRolesid(Integer rolesid){
		this.rolesid = rolesid;
	}
	
	public Integer getRolesid(){
		return this.rolesid;
	}
		
	public void setRetailid(Integer retailid){
		this.retailid = retailid;
	}
	
	public Integer getRetailid(){
		return this.retailid;
	}
		
	public void setScid(Integer scid){
		this.scid = scid;
	}
	
	public Integer getScid(){
		return this.scid;
	}
		
	public void setUserDel(Byte userDel){
		this.userDel = userDel;
	}
	
	public Byte getUserDel(){
		return this.userDel;
	}
		
	public void setStoreDel(Byte storeDel){
		this.storeDel = storeDel;
	}
	
	public Byte getStoreDel(){
		return this.storeDel;
	}
		
	public void setIsReview(Byte isReview){
		this.isReview = isReview;
	}
	
	public Byte getIsReview(){
		return this.isReview;
	}
		
	public void setLastTime(Date lastTime){
		this.lastTime = lastTime;
	}
	
	public Date getLastTime(){
		return this.lastTime;
	}
		
	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}
	
	public Date getPayTime(){
		return this.payTime;
	}
		
	public void setAddVersion(Byte addVersion){
		this.addVersion = addVersion;
	}
	
	public Byte getAddVersion(){
		return this.addVersion;
	}
		
	public void setIsReturngoods(Byte isReturngoods){
		this.isReturngoods = isReturngoods;
	}
	
	public Byte getIsReturngoods(){
		return this.isReturngoods;
	}
		
	public void setPostponeShip(Integer postponeShip){
		this.postponeShip = postponeShip;
	}
	
	public Integer getPostponeShip(){
		return this.postponeShip;
	}
		
	public void setPostponeCountUser(Integer postponeCountUser){
		this.postponeCountUser = postponeCountUser;
	}
	
	public Integer getPostponeCountUser(){
		return this.postponeCountUser;
	}
		
	public void setPostponeCountStore(Integer postponeCountStore){
		this.postponeCountStore = postponeCountStore;
	}
	
	public Integer getPostponeCountStore(){
		return this.postponeCountStore;
	}
		
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return this.remark;
	}
		
		
}