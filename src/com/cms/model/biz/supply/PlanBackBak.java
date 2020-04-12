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

@Table("SU_PLAN_BACK_BAK")
@View("SU_PLAN_BACK_BAK_V")
@PK({ "BAKID" })
public class PlanBackBak extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final PlanBackBak INSTANCE = new PlanBackBak();

	@Column(value = "bakid", type = ColumnType.STRING)
	private String bakid;

	@Column(value = "planid", type = ColumnType.STRING)
	private String planid;

	@Column(value = "plandocid", type = ColumnType.STRING)
	private String plandocid;

	@Column(value = "orggoodsid", type = ColumnType.STRING)
	private String orggoodsid;

	@Column(value = "supgoodsid", type = ColumnType.STRING)
	private String supgoodsid;

	@Column(value = "backqty", type = ColumnType.STRING)
	private String backqty;

	@Column(value = "backprice", type = ColumnType.STRING)
	private String backprice;

	@Column(value = "sugoodsqty", type = ColumnType.STRING)
	private String sugoodsqty;

	@Column(value = "suprice", type = ColumnType.STRING)
	private String suprice;

	@Column(value = "orggoodsname", type = ColumnType.STRING)
	private String orggoodsname;

	@Column(value = "orggoodstype", type = ColumnType.STRING)
	private String orggoodstype;

	@Column(value = "orggoodsfactory", type = ColumnType.STRING)
	private String orggoodsfactory;

	@Column(value = "orggoodsunit", type = ColumnType.STRING)
	private String orggoodsunit;

	@Column(value = "supbackqty", type = ColumnType.STRING)
	private String supbackqty;

	@Column(value = "supbackprice", type = ColumnType.STRING)
	private String supbackprice;

	@Column(value = "createdate", type = ColumnType.STRING)
	private String createdate;

	@ReadOnly
	@Column(value = "supgoodsname", type = ColumnType.STRING)
	private String supgoodsname;

	@ReadOnly
	@Column(value = "supgoodstype", type = ColumnType.STRING)
	private String supgoodstype;

	@ReadOnly
	@Column(value = "supgoodsunit", type = ColumnType.STRING)
	private String supgoodsunit;

	@ReadOnly
	@Column(value = "supfactory", type = ColumnType.STRING)
	private String supfactory;
	
	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getBakid() {
		return bakid;
	}

	public void setBakid(String bakid) {
		this.bakid = bakid;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlandocid() {
		return plandocid;
	}

	public void setPlandocid(String plandocid) {
		this.plandocid = plandocid;
	}

	public String getOrggoodsid() {
		return orggoodsid;
	}

	public void setOrggoodsid(String orggoodsid) {
		this.orggoodsid = orggoodsid;
	}

	public String getSupgoodsid() {
		return supgoodsid;
	}

	public void setSupgoodsid(String supgoodsid) {
		this.supgoodsid = supgoodsid;
	}

	public String getBackqty() {
		return backqty;
	}

	public void setBackqty(String backqty) {
		this.backqty = backqty;
	}

	public String getBackprice() {
		return backprice;
	}

	public void setBackprice(String backprice) {
		this.backprice = backprice;
	}

	public String getSugoodsqty() {
		return sugoodsqty;
	}

	public void setSugoodsqty(String sugoodsqty) {
		this.sugoodsqty = sugoodsqty;
	}

	public String getSuprice() {
		return suprice;
	}

	public void setSuprice(String suprice) {
		this.suprice = suprice;
	}

	public String getOrggoodsname() {
		return orggoodsname;
	}

	public void setOrggoodsname(String orggoodsname) {
		this.orggoodsname = orggoodsname;
	}

	public String getOrggoodstype() {
		return orggoodstype;
	}

	public void setOrggoodstype(String orggoodstype) {
		this.orggoodstype = orggoodstype;
	}

	public String getOrggoodsfactory() {
		return orggoodsfactory;
	}

	public void setOrggoodsfactory(String orggoodsfactory) {
		this.orggoodsfactory = orggoodsfactory;
	}

	public String getOrggoodsunit() {
		return orggoodsunit;
	}

	public void setOrggoodsunit(String orggoodsunit) {
		this.orggoodsunit = orggoodsunit;
	}

	public String getSupbackqty() {
		return supbackqty;
	}

	public void setSupbackqty(String supbackqty) {
		this.supbackqty = supbackqty;
	}

	public String getSupbackprice() {
		return supbackprice;
	}

	public void setSupbackprice(String supbackprice) {
		this.supbackprice = supbackprice;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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

	public String getSupgoodsunit() {
		return supgoodsunit;
	}

	public void setSupgoodsunit(String supgoodsunit) {
		this.supgoodsunit = supgoodsunit;
	}

	public String getSupfactory() {
		return supfactory;
	}

	public void setSupfactory(String supfactory) {
		this.supfactory = supfactory;
	}

}
