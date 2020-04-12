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



@Table("SU_SUPPLY_ORG")
@View("SU_SUPPLY_ORG_V")
@PK({ "ORGID","SUPPLYID" })
public class SuSupplyOrg extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuSupplyOrg INSTANCE = new SuSupplyOrg();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@Column(value = "logname", type = ColumnType.STRING)
	private String logname ;
	
	@Column(value = "password", type = ColumnType.STRING)
	private String password ;
	
	@ReadOnly
	@Column(value = "orgname", type = ColumnType.STRING)
	private String orgname ;
	
	@ReadOnly
	@Column(value = "supplycode", type = ColumnType.STRING)
	private String supplycode ;
	
	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@ReadOnly
	@Column(value = "categoryid", type = ColumnType.NUMBER)
	private String categoryid ;
	
	@ReadOnly
	@Column(value = "categoryname", type = ColumnType.STRING)
	private String categoryname ;
	
	@ReadOnly
	@Column(value = "status", type = ColumnType.NUMBER)
	private String status ;
	


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

	public String getLogname (){
		return logname;
	}
		 
	public void setLogname (String logname){
		this.logname=logname;
	}

	public String getPassword (){
		return password;
	}
		 
	public void setPassword (String password){
		this.password=password;
	}

	public String getOrgname (){
		return orgname;
	}
		 
	public void setOrgname (String orgname){
		this.orgname=orgname;
	}

	public String getSupplycode (){
		return supplycode;
	}
		 
	public void setSupplycode (String supplycode){
		this.supplycode=supplycode;
	}

	public String getSupplyname (){
		return supplyname;
	}
		 
	public void setSupplyname (String supplyname){
		this.supplyname=supplyname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getCategoryid (){
		return categoryid;
	}
		 
	public void setCategoryid (String categoryid){
		this.categoryid=categoryid;
	}

	public String getCategoryname (){
		return categoryname;
	}
		 
	public void setCategoryname (String categoryname){
		this.categoryname=categoryname;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

}
