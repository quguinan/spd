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

@Table("SUP_GOODS")
@View("SUP_GOODS_V")
@PK({})
public class SupGoods extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final SupGoods INSTANCE = new SupGoods();

	@Column(value = "orgid", type = ColumnType.STRING)
	@Id
	private String orgid;

	@Column(value = "supgoodsid", type = ColumnType.STRING)
	private String supgoodsid;

	@Column(value = "supgoodsname", type = ColumnType.STRING)
	private String supgoodsname;

	@Column(value = "supgoodstype", type = ColumnType.STRING)
	private String supgoodstype;

	@Column(value = "supfactory", type = ColumnType.STRING)
	private String supfactory;

	@Column(value = "supapprovedocno", type = ColumnType.STRING)
	private String supapprovedocno;

	@Column(value = "supregistdocno", type = ColumnType.STRING)
	private String supregistdocno;

	@Column(value = "orggoodsid", type = ColumnType.STRING)
	private String orggoodsid;

	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	@Column(value = "gspcategory", type = ColumnType.STRING)
	private String gspcategory;

	@Column(value = "supgoodsunit", type = ColumnType.STRING)
	private String supgoodsunit;

	@Column(value = "goodspinyin", type = ColumnType.STRING)
	private String goodspinyin;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSupgoodsid() {
		return supgoodsid;
	}

	public void setSupgoodsid(String supgoodsid) {
		this.supgoodsid = supgoodsid;
	}

	public String getSupgoodsname() {
		return supgoodsname;
	}

	public void setSupgoodsname(String supgoodsname) {
		this.supgoodsname = supgoodsname;
	}

	public String getSupgoodstype() {
		return supgoodstype;
	}

	public void setSupgoodstype(String supgoodstype) {
		this.supgoodstype = supgoodstype;
	}

	public String getSupfactory() {
		return supfactory;
	}

	public void setSupfactory(String supfactory) {
		this.supfactory = supfactory;
	}

	public String getSupapprovedocno() {
		return supapprovedocno;
	}

	public void setSupapprovedocno(String supapprovedocno) {
		this.supapprovedocno = supapprovedocno;
	}

	public String getSupregistdocno() {
		return supregistdocno;
	}

	public void setSupregistdocno(String supregistdocno) {
		this.supregistdocno = supregistdocno;
	}

	public String getOrggoodsid() {
		return orggoodsid;
	}

	public void setOrggoodsid(String orggoodsid) {
		this.orggoodsid = orggoodsid;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getGspcategory() {
		return gspcategory;
	}

	public void setGspcategory(String gspcategory) {
		this.gspcategory = gspcategory;
	}

	public String getSupgoodsunit() {
		return supgoodsunit;
	}

	public void setSupgoodsunit(String supgoodsunit) {
		this.supgoodsunit = supgoodsunit;
	}

	public String getGoodspinyin() {
		return goodspinyin;
	}

	public void setGoodspinyin(String goodspinyin) {
		this.goodspinyin = goodspinyin;
	}

}
