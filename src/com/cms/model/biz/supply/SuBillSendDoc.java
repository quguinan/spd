/**
** create by code gen .
**/
package com.cms.model.biz.supply;

import java.util.Date;
import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("SU_BILL_SEND_DOC")
@View("SU_BILL_SEND_DOC_V")
@PK({ "DOCID" })
public class SuBillSendDoc extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuBillSendDoc INSTANCE = new SuBillSendDoc();
	
	@Id
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
	@Column(value = "docno", type = ColumnType.STRING)
	private String docno ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@Column(value = "intype", type = ColumnType.NUMBER)
	private String intype ;
	
	@Column(value = "sendtype", type = ColumnType.NUMBER)
	private String sendtype ;
	
	@Column(value = "checkuserid", type = ColumnType.NUMBER)
	private String checkuserid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate ;
	
	@Column(value = "sendaddress", type = ColumnType.STRING)
	private String sendaddress ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "senddatetime", type = ColumnType.DATE)
	private String senddatetime ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "status", type = ColumnType.NUMBER)
	private String status ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@Column(value = "storerid", type = ColumnType.NUMBER)
	private String storerid ;
	
	@Column(value = "orderid", type = ColumnType.STRING)
	private String orderid ;
	
	@Column(value = "sourceid", type = ColumnType.STRING)
	private String sourceid ;
	
	@Column(value = "sendid", type = ColumnType.STRING)
	private String sendid ;
	
	@ReadOnly
	@Column(value = "orgname", type = ColumnType.STRING)
	private String orgname ;
	
	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;
	
	@ReadOnly
	@Column(value = "checkusername", type = ColumnType.STRING)
	private String checkusername ;
	


	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getDocno (){
		return docno;
	}
		 
	public void setDocno (String docno){
		this.docno=docno;
	}

	public String getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (String orgid){
		this.orgid=orgid;
	}

	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
	}

	public String getIntype (){
		return intype;
	}
		 
	public void setIntype (String intype){
		this.intype=intype;
	}

	public String getSendtype (){
		return sendtype;
	}
		 
	public void setSendtype (String sendtype){
		this.sendtype=sendtype;
	}

	public String getCheckuserid (){
		return checkuserid;
	}
		 
	public void setCheckuserid (String checkuserid){
		this.checkuserid=checkuserid;
	}

	public String getCheckdate (){
		return checkdate;
	}
		 
	public void setCheckdate (String checkdate){
		this.checkdate=checkdate;
	}

	public String getSendaddress (){
		return sendaddress;
	}
		 
	public void setSendaddress (String sendaddress){
		this.sendaddress=sendaddress;
	}

	public String getSenddatetime (){
		return senddatetime;
	}
		 
	public void setSenddatetime (String senddatetime){
		this.senddatetime=senddatetime;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getCredate (){
		return credate;
	}
		 
	public void setCredate (String credate){
		this.credate=credate;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getStorerid (){
		return storerid;
	}
		 
	public void setStorerid (String storerid){
		this.storerid=storerid;
	}

	public String getOrderid (){
		return orderid;
	}
		 
	public void setOrderid (String orderid){
		this.orderid=orderid;
	}

	public String getSourceid (){
		return sourceid;
	}
		 
	public void setSourceid (String sourceid){
		this.sourceid=sourceid;
	}

	public String getSendid (){
		return sendid;
	}
		 
	public void setSendid (String sendid){
		this.sendid=sendid;
	}

	public String getOrgname (){
		return orgname;
	}
		 
	public void setOrgname (String orgname){
		this.orgname=orgname;
	}

	public String getSupplyname (){
		return supplyname;
	}
		 
	public void setSupplyname (String supplyname){
		this.supplyname=supplyname;
	}

	public String getCheckusername (){
		return checkusername;
	}
		 
	public void setCheckusername (String checkusername){
		this.checkusername=checkusername;
	}

}
