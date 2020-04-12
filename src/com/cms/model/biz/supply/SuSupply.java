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



@Table("SU_SUPPLY")
@View("SU_SUPPLY_V")
@PK({ "SUPPLYID" })
public class SuSupply extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuSupply INSTANCE = new SuSupply();
	
	@Id
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@Column(value = "supplycode", type = ColumnType.STRING)
	private String supplycode ;
	
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "categoryid", type = ColumnType.NUMBER)
	private String categoryid ;
	
	@Column(value = "status", type = ColumnType.NUMBER)
	private String status ;
	
	@ReadOnly
	@Column(value = "categoryname", type = ColumnType.STRING)
	private String categoryname ;
	


	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
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

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getCategoryname (){
		return categoryname;
	}
		 
	public void setCategoryname (String categoryname){
		this.categoryname=categoryname;
	}

}
