/**
** create by code gen .
**/
package com.cms.model.biz.goods;

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



@Table("GOODS_STOCK2")
@View("GOODS_STOCK2_V")
@PK({ "STOCKID" })
public class GoodsStock2 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStock2 INSTANCE = new GoodsStock2();
	
	@Name
	@Column(value = "stockid", type = ColumnType.STRING)
	private String stockid ;
	
	@Column(value = "deptid", type = ColumnType.STRING)
	private String deptid ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "lotid", type = ColumnType.STRING)
	private String lotid ;
	
	@Column(value = "posid", type = ColumnType.STRING)
	private String posid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@ReadOnly
	@Column(value = "deptname", type = ColumnType.STRING)
	private String deptname ;
	
	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@ReadOnly
	@Column(value = "customname", type = ColumnType.STRING)
	private String customname ;
	
	@ReadOnly
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;
	
	@ReadOnly
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@ReadOnly
	@Column(value = "goodsstatus", type = ColumnType.STRING)
	private String goodsstatus ;
	
	@ReadOnly
	@Column(value = "factoryid", type = ColumnType.STRING)
	private String factoryid ;
	
	@ReadOnly
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname ;
	
	@ReadOnly
	@Column(value = "prodareaid", type = ColumnType.STRING)
	private String prodareaid ;
	
	@ReadOnly
	@Column(value = "prodareaname", type = ColumnType.STRING)
	private String prodareaname ;
	
	@ReadOnly
	@Column(value = "unitid", type = ColumnType.STRING)
	private String unitid ;
	
	@ReadOnly
	@Column(value = "unitname", type = ColumnType.STRING)
	private String unitname ;
	
	@ReadOnly
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@ReadOnly
	@Column(value = "ratio", type = ColumnType.NUMBER)
	private BigDecimal ratio ;
	
	@ReadOnly
	@Column(value = "batchno", type = ColumnType.STRING)
	private String batchno ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@ReadOnly
	@Column(value = "prodate", type = ColumnType.DATE)
	private String prodate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@ReadOnly
	@Column(value = "expiredate", type = ColumnType.DATE)
	private String expiredate ;
	


	public String getStockid (){
		return stockid;
	}
		 
	public void setStockid (String stockid){
		this.stockid=stockid;
	}

	public String getDeptid (){
		return deptid;
	}
		 
	public void setDeptid (String deptid){
		this.deptid=deptid;
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

	public String getLotid (){
		return lotid;
	}
		 
	public void setLotid (String lotid){
		this.lotid=lotid;
	}

	public String getPosid (){
		return posid;
	}
		 
	public void setPosid (String posid){
		this.posid=posid;
	}

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getDeptname (){
		return deptname;
	}
		 
	public void setDeptname (String deptname){
		this.deptname=deptname;
	}

	public String getGoodsname (){
		return goodsname;
	}
		 
	public void setGoodsname (String goodsname){
		this.goodsname=goodsname;
	}

	public String getCustomname (){
		return customname;
	}
		 
	public void setCustomname (String customname){
		this.customname=customname;
	}

	public String getClassid (){
		return classid;
	}
		 
	public void setClassid (String classid){
		this.classid=classid;
	}

	public String getClassname (){
		return classname;
	}
		 
	public void setClassname (String classname){
		this.classname=classname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getGoodsstatus (){
		return goodsstatus;
	}
		 
	public void setGoodsstatus (String goodsstatus){
		this.goodsstatus=goodsstatus;
	}

	public String getFactoryid (){
		return factoryid;
	}
		 
	public void setFactoryid (String factoryid){
		this.factoryid=factoryid;
	}

	public String getFactoryname (){
		return factoryname;
	}
		 
	public void setFactoryname (String factoryname){
		this.factoryname=factoryname;
	}

	public String getProdareaid (){
		return prodareaid;
	}
		 
	public void setProdareaid (String prodareaid){
		this.prodareaid=prodareaid;
	}

	public String getProdareaname (){
		return prodareaname;
	}
		 
	public void setProdareaname (String prodareaname){
		this.prodareaname=prodareaname;
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

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
	}

	public BigDecimal getRatio (){
		return ratio;
	}
		 
	public void setRatio (BigDecimal ratio){
		this.ratio=ratio;
	}

	public String getBatchno (){
		return batchno;
	}
		 
	public void setBatchno (String batchno){
		this.batchno=batchno;
	}

	public String getProdate (){
		return prodate;
	}
		 
	public void setProdate (String prodate){
		this.prodate=prodate;
	}

	public String getExpiredate (){
		return expiredate;
	}
		 
	public void setExpiredate (String expiredate){
		this.expiredate=expiredate;
	}

}
