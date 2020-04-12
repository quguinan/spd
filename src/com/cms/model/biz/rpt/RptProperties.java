/**
** create by code gen .
**/
package com.cms.model.biz.rpt;

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



@Table("RPT_PROPERTIES")
@View("RPT_PROPERTIES_V")
@PK({ "PROID" })
public class RptProperties extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptProperties INSTANCE = new RptProperties();
	
	@Column(value = "proid", type = ColumnType.NUMBER)
	private String proid ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "valuetype", type = ColumnType.STRING)
	private String valuetype ;
	
	@Column(value = "valuedefault", type = ColumnType.STRING)
	private String valuedefault ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	


	public String getProid (){
		return proid;
	}
		 
	public void setProid (String proid){
		this.proid=proid;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getValuetype (){
		return valuetype;
	}
		 
	public void setValuetype (String valuetype){
		this.valuetype=valuetype;
	}

	public String getValuedefault (){
		return valuedefault;
	}
		 
	public void setValuedefault (String valuedefault){
		this.valuedefault=valuedefault;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

}
