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



@Table("SU_COM_GOODS_V")
@View("SU_COM_GOODS_V")
@PK({ "ORGID","GOODSID" })
public class SuComGoodsV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuComGoodsV INSTANCE = new SuComGoodsV();
	
	@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid ;
	
	@Column(value = "goodsid", type = ColumnType.NUMBER)
	private String goodsid ;
	
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "goodstype", type = ColumnType.STRING)
	private String goodstype ;
	
	@Column(value = "classcode", type = ColumnType.NUMBER)
	private String classcode ;
	
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@Column(value = "unit", type = ColumnType.STRING)
	private String unit ;
	
	@Column(value = "factory", type = ColumnType.STRING)
	private String factory ;
	
	@Column(value = "prodarea", type = ColumnType.STRING)
	private String prodarea ;
	


	public String getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (String orgid){
		this.orgid=orgid;
	}

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getGoodsname (){
		return goodsname;
	}
		 
	public void setGoodsname (String goodsname){
		this.goodsname=goodsname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getGoodstype (){
		return goodstype;
	}
		 
	public void setGoodstype (String goodstype){
		this.goodstype=goodstype;
	}

	public String getClasscode (){
		return classcode;
	}
		 
	public void setClasscode (String classcode){
		this.classcode=classcode;
	}

	public String getClassname (){
		return classname;
	}
		 
	public void setClassname (String classname){
		this.classname=classname;
	}

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
	}

	public String getUnit (){
		return unit;
	}
		 
	public void setUnit (String unit){
		this.unit=unit;
	}

	public String getFactory (){
		return factory;
	}
		 
	public void setFactory (String factory){
		this.factory=factory;
	}

	public String getProdarea (){
		return prodarea;
	}
		 
	public void setProdarea (String prodarea){
		this.prodarea=prodarea;
	}

}
