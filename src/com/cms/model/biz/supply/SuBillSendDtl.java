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

@Table("SU_BILL_SEND_DTL")
@View("SU_BILL_SEND_DTL_V")
@PK({ "DTLID" })
public class SuBillSendDtl extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final SuBillSendDtl INSTANCE = new SuBillSendDtl();

	@Id
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private String dtlid;

	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid;

	@Column(value = "sortno", type = ColumnType.NUMBER)
	private String sortno;

	@Column(value = "sugoodsid", type = ColumnType.NUMBER)
	private String sugoodsid;

	@Column(value = "goodsid", type = ColumnType.NUMBER)
	private String goodsid;

	@Column(value = "sendqty", type = ColumnType.NUMBER)
	private String sendqty;

	@Column(value = "recieveqty", type = ColumnType.NUMBER)
	private String recieveqty;

	@Column(value = "unit", type = ColumnType.STRING)
	private String unit;

	@Column(value = "unitprice", type = ColumnType.STRING)
	private String unitprice;

	@Column(value = "total", type = ColumnType.STRING)
	private String total;

	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;

	@Column(value = "batchno", type = ColumnType.STRING)
	private String batchno;
	@DateFormat("yyyy-MM-dd")
	@Column(value = "proddate", type = ColumnType.DATE)
	private String proddate;
	@DateFormat("yyyy-MM-dd")
	@Column(value = "expirydate", type = ColumnType.DATE)
	private String expirydate;

	@Column(value = "dtlstatus", type = ColumnType.NUMBER)
	private String dtlstatus;

	@Column(value = "orderdtlid", type = ColumnType.STRING)
	private String orderdtlid;

	@Column(value = "senddtlid", type = ColumnType.STRING)
	private String senddtlid;

	@ReadOnly
	@Column(value = "sugoodsname", type = ColumnType.STRING)
	private String sugoodsname;

	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname;

	@ReadOnly
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid;

	@ReadOnly
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid;

	public String getDtlid() {
		return dtlid;
	}

	public String getProddate() {
		return proddate;
	}

	public void setProddate(String proddate) {
		this.proddate = proddate;
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

	public String getSendqty() {
		return sendqty;
	}

	public void setSendqty(String sendqty) {
		this.sendqty = sendqty;
	}

	public String getRecieveqty() {
		return recieveqty;
	}

	public void setRecieveqty(String recieveqty) {
		this.recieveqty = recieveqty;
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

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getDtlstatus() {
		return dtlstatus;
	}

	public void setDtlstatus(String dtlstatus) {
		this.dtlstatus = dtlstatus;
	}

	public String getOrderdtlid() {
		return orderdtlid;
	}

	public void setOrderdtlid(String orderdtlid) {
		this.orderdtlid = orderdtlid;
	}

	public String getSenddtlid() {
		return senddtlid;
	}

	public void setSenddtlid(String senddtlid) {
		this.senddtlid = senddtlid;
	}

	public String getSugoodsname() {
		return sugoodsname;
	}

	public void setSugoodsname(String sugoodsname) {
		this.sugoodsname = sugoodsname;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
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
