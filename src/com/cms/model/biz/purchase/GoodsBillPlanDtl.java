/**
** create by code gen .
**/
package com.cms.model.biz.purchase;

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



@Table("GOODS_BILL_PLAN_DTL")
@View("GOODS_BILL_PLAN_DTL_V")
@PK({ "DTLID" })
public class GoodsBillPlanDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBillPlanDtl INSTANCE = new GoodsBillPlanDtl();
	
	@Name
	@Column(value = "dtlid", type = ColumnType.STRING)
	private String dtlid ;
	
	@Column(value = "docid", type = ColumnType.STRING)
	private String docid ;
	
	@Column(value = "rowno", type = ColumnType.STRING)
	private String rowno ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@ReadOnly
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@ReadOnly
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;
	
	@ReadOnly
	@Column(value = "unitid", type = ColumnType.STRING)
	private String unitid ;
	
	@ReadOnly
	@Column(value = "unitname", type = ColumnType.STRING)
	private String unitname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@ReadOnly
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@ReadOnly
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname ;
	


	public String getDtlid (){
		return dtlid;
	}
		 
	public void setDtlid (String dtlid){
		this.dtlid=dtlid;
	}

	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getRowno (){
		return rowno;
	}
		 
	public void setRowno (String rowno){
		this.rowno=rowno;
	}

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getGoodsdtlid (){
		return goodsdtlid;
	}
		 
	public void setGoodsdtlid (String goodsdtlid){
		this.goodsdtlid=goodsdtlid;
	}

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getGoodsname (){
		return goodsname;
	}
		 
	public void setGoodsname (String goodsname){
		this.goodsname=goodsname;
	}

	public String getClassname (){
		return classname;
	}
		 
	public void setClassname (String classname){
		this.classname=classname;
	}

	public String getClassid (){
		return classid;
	}
		 
	public void setClassid (String classid){
		this.classid=classid;
	}

	public String getUnitid (){
		return unitid;
	}
		 
	public void setUnitid (String unitid){
		this.unitid=unitid;
	}

	public String getUnitname (){
		return unitname;
	}
		 
	public void setUnitname (String unitname){
		this.unitname=unitname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
	}

	public String getFactoryname (){
		return factoryname;
	}
		 
	public void setFactoryname (String factoryname){
		this.factoryname=factoryname;
	}

}
