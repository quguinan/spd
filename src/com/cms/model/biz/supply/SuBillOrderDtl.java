/**
** create by code gen .
**/
package com.cms.model.biz.supply;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;

@Table("SU_BILL_ORDER_DTL")
@View("SU_BILL_ORDER_DTL_V")
@PK({ "DTLID" })
public class SuBillOrderDtl extends BasePO {

	/**
	* 
	*/
	private static final Long serialVersionUID = 1L;

	public static final SuBillOrderDtl INSTANCE = new SuBillOrderDtl();

	@Id
	@Column(value = "dtlid", type = ColumnType.STRING)
	private String dtlid;

	@Column(value = "docid", type = ColumnType.STRING)
	private String docid;

	@Column(value = "sortno", type = ColumnType.STRING)
	private String sortno;

	@Column(value = "sugoodsid", type = ColumnType.STRING)
	private String sugoodsid;

	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid;

	@Column(value = "orderqty", type = ColumnType.STRING)
	private String orderqty;

	@Column(value = "unit", type = ColumnType.STRING)
	private String unit;

	@Column(value = "unitprice", type = ColumnType.STRING)
	private String unitprice;

	@Column(value = "total", type = ColumnType.STRING)
	private String total;

	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;

	@Column(value = "orderdtlid", type = ColumnType.STRING)
	private String orderdtlid;

	@Column(value = "lotno", type = ColumnType.STRING)
	private String lotno;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "prodate", type = ColumnType.DATE)
	private String prodate;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "invaliddate", type = ColumnType.DATE)
	private String invaliddate;

	@ReadOnly
	@Column(value = "supgoodsid", type = ColumnType.STRING)
	private String supgoodsid;

	@ReadOnly
	@Column(value = "supgoodsname", type = ColumnType.STRING)
	private String supgoodsname;

	@ReadOnly
	@Column(value = "supgoodstype", type = ColumnType.STRING)
	private String supgoodstype;

	@ReadOnly
	@Column(value = "supfactory", type = ColumnType.STRING)
	private String supfactory;

	@ReadOnly
	@Column(value = "supgoodsunit", type = ColumnType.STRING)
	private String supgoodsunit;

	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname;

	@ReadOnly
	@Column(value = "goodstype", type = ColumnType.STRING)
	private String goodstype;

	@ReadOnly
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname;

	@ReadOnly
	@Column(value = "goodsunit", type = ColumnType.STRING)
	private String goodsunit;

	@ReadOnly
	@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid;

	@ReadOnly
	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	public String getDtlid() {
		return dtlid;
	}

	public void setDtlid(String dtlid) {
		this.dtlid = dtlid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getSortno() {
		return sortno;
	}

	public void setSortno(String sortno) {
		this.sortno = sortno;
	}

	public String getSugoodsid() {
		return sugoodsid;
	}

	public void setSugoodsid(String sugoodsid) {
		this.sugoodsid = sugoodsid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getOrderqty() {
		return orderqty;
	}

	public void setOrderqty(String orderqty) {
		this.orderqty = orderqty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrderdtlid() {
		return orderdtlid;
	}

	public void setOrderdtlid(String orderdtlid) {
		this.orderdtlid = orderdtlid;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getProdate() {
		return prodate;
	}

	public void setProdate(String prodate) {
		this.prodate = prodate;
	}

	public String getInvaliddate() {
		return invaliddate;
	}

	public void setInvaliddate(String invaliddate) {
		this.invaliddate = invaliddate;
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

	public String getSupgoodsunit() {
		return supgoodsunit;
	}

	public void setSupgoodsunit(String supgoodsunit) {
		this.supgoodsunit = supgoodsunit;
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

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

}
