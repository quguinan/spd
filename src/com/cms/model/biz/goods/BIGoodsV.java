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

@Table("BI_GOODS_V")
@View("BI_GOODS_V")
@PK({})
public class BIGoodsV extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final BIGoodsV INSTANCE = new BIGoodsV();

	@Column(value = "goodsid", type = ColumnType.NUMBER)
	private Long goodsid;

	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname;

	@Column(value = "goodstype", type = ColumnType.STRING)
	private String goodstype;

	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname;

	@Column(value = "goodsunit", type = ColumnType.STRING)
	private String goodsunit;

	@Column(value = "approvedocno", type = ColumnType.STRING)
	private String approvedocno;

	@Column(value = "registdocno", type = ColumnType.STRING)
	private String registdocno;

	@Column(value = "category", type = ColumnType.STRING)
	private String category;

	@Column(value = "goodspinyin", type = ColumnType.STRING)
	private String goodspinyin;

	public String getGoodspinyin() {
		return goodspinyin;
	}

	public void setGoodspinyin(String goodspinyin) {
		this.goodspinyin = goodspinyin;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getGoodsunit() {
		return goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	public String getApprovedocno() {
		return approvedocno;
	}

	public void setApprovedocno(String approvedocno) {
		this.approvedocno = approvedocno;
	}

	public String getRegistdocno() {
		return registdocno;
	}

	public void setRegistdocno(String registdocno) {
		this.registdocno = registdocno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
