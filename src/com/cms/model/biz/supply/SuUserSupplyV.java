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



@Table("SU_USER")
@View("SU_USER_SUPPLY_V")
@PK({ "USERID" })
public class SuUserSupplyV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuUserSupplyV INSTANCE = new SuUserSupplyV();
	
	@Id
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@Column(value = "usertype", type = ColumnType.NUMBER)
	private String usertype ;
	
	@Column(value = "groupid", type = ColumnType.NUMBER)
	private String groupid ;
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@ReadOnly
	@Column(value = "realname", type = ColumnType.STRING)
	private String realname ;
	
	@ReadOnly
	@Column(value = "username", type = ColumnType.STRING)
	private String username ;
	
	@ReadOnly
	@Column(value = "stopped", type = ColumnType.NUMBER)
	private String stopped ;
	
	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;
	


	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getUsertype (){
		return usertype;
	}
		 
	public void setUsertype (String usertype){
		this.usertype=usertype;
	}

	public String getGroupid (){
		return groupid;
	}
		 
	public void setGroupid (String groupid){
		this.groupid=groupid;
	}

	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
	}

	public String getRealname (){
		return realname;
	}
		 
	public void setRealname (String realname){
		this.realname=realname;
	}

	public String getUsername (){
		return username;
	}
		 
	public void setUsername (String username){
		this.username=username;
	}

	public String getStopped (){
		return stopped;
	}
		 
	public void setStopped (String stopped){
		this.stopped=stopped;
	}

	public String getSupplyname (){
		return supplyname;
	}
		 
	public void setSupplyname (String supplyname){
		this.supplyname=supplyname;
	}

}
