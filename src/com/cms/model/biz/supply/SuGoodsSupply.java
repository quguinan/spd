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



@Table("SU_GOODS_SUPPLY")
@View("SU_GOODS_SUPPLY_V")
@PK({ "ORGID","SUGOODSID","SUPPLYID" })
public class SuGoodsSupply extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuGoodsSupply INSTANCE = new SuGoodsSupply();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@Column(value = "sugoodsid", type = ColumnType.STRING)
	private String sugoodsid ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "sugoodsname", type = ColumnType.STRING)
	private String sugoodsname ;
	
	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@Column(value = "unit", type = ColumnType.STRING)
	private String unit ;
	
	@Column(value = "classcode", type = ColumnType.STRING)
	private String classcode ;
	
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@Column(value = "factory", type = ColumnType.STRING)
	private String factory ;
	
	@Column(value = "prodarea", type = ColumnType.STRING)
	private String prodarea ;
	


	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
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

	public String getSugoodsid (){
		return sugoodsid;
	}
		 
	public void setSugoodsid (String sugoodsid){
		this.sugoodsid=sugoodsid;
	}

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getSugoodsname (){
		return sugoodsname;
	}
		 
	public void setSugoodsname (String sugoodsname){
		this.sugoodsname=sugoodsname;
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
