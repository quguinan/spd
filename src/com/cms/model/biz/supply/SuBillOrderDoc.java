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

@Table("SU_BILL_ORDER_DOC")
@View("SU_BILL_ORDER_DOC_V")
@PK({ "DOCID" })
public class SuBillOrderDoc extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final SuBillOrderDoc INSTANCE = new SuBillOrderDoc();

	@Id
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid;

	@Column(value = "docno", type = ColumnType.STRING)
	private String docno;

	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid;

	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid;

	@Column(value = "ordtype", type = ColumnType.NUMBER)
	private String ordtype;

	@Column(value = "intype", type = ColumnType.NUMBER)
	private String intype;

	@Column(value = "checkuserid", type = ColumnType.NUMBER)
	private String checkuserid;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "signdate", type = ColumnType.DATE)
	private String signdate;

	@Column(value = "signaddress", type = ColumnType.STRING)
	private String signaddress;

	@Column(value = "signman", type = ColumnType.STRING)
	private String signman;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "validbegdate", type = ColumnType.DATE)
	private String validbegdate;

	@DateFormat("yyyy-MM-dd")
	@Column(value = "validenddate", type = ColumnType.DATE)
	private String validenddate;

	@Column(value = "settletype", type = ColumnType.NUMBER)
	private String settletype;

	@Column(value = "prepay", type = ColumnType.NUMBER)
	private BigDecimal prepay;

	@Column(value = "total", type = ColumnType.NUMBER)
	private BigDecimal total;

	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;

	@Column(value = "status", type = ColumnType.STRING)
	private String status;
	@Column(value = "entryid", type = ColumnType.STRING)
	
	private String entryid;
	@Column(value = "entryname", type = ColumnType.STRING)
	private String entryname;

	public String getEntryid() {
		return entryid;
	}

	public void setEntryid(String entryid) {
		this.entryid = entryid;
	}

	public String getEntryname() {
		return entryname;
	}

	public void setEntryname(String entryname) {
		this.entryname = entryname;
	}

	@DateFormat("yyyy-MM-dd")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate;

	@Column(value = "userid", type = ColumnType.STRING)
	private String userid;

	@Column(value = "storerid", type = ColumnType.STRING)
	private String storerid;

	@Column(value = "sourceid", type = ColumnType.STRING)
	private String sourceid;

	@Column(value = "orderid", type = ColumnType.STRING)
	private String orderid;

	@ReadOnly
	@Column(value = "orgname", type = ColumnType.STRING)
	private String orgname;

	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname;

	@ReadOnly
	@Column(value = "checkusername", type = ColumnType.STRING)
	private String checkusername;

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
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

	public String getOrdtype() {
		return ordtype;
	}

	public void setOrdtype(String ordtype) {
		this.ordtype = ordtype;
	}

	public String getIntype() {
		return intype;
	}

	public void setIntype(String intype) {
		this.intype = intype;
	}

	public String getCheckuserid() {
		return checkuserid;
	}

	public void setCheckuserid(String checkuserid) {
		this.checkuserid = checkuserid;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getSigndate() {
		return signdate;
	}

	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}

	public String getSignaddress() {
		return signaddress;
	}

	public void setSignaddress(String signaddress) {
		this.signaddress = signaddress;
	}

	public String getSignman() {
		return signman;
	}

	public void setSignman(String signman) {
		this.signman = signman;
	}

	public String getValidbegdate() {
		return validbegdate;
	}

	public void setValidbegdate(String validbegdate) {
		this.validbegdate = validbegdate;
	}

	public String getValidenddate() {
		return validenddate;
	}

	public void setValidenddate(String validenddate) {
		this.validenddate = validenddate;
	}

	public String getSettletype() {
		return settletype;
	}

	public void setSettletype(String settletype) {
		this.settletype = settletype;
	}

	public BigDecimal getPrepay() {
		return prepay;
	}

	public void setPrepay(BigDecimal prepay) {
		this.prepay = prepay;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStorerid() {
		return storerid;
	}

	public void setStorerid(String storerid) {
		this.storerid = storerid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getSupplyname() {
		return supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}

	public String getCheckusername() {
		return checkusername;
	}

	public void setCheckusername(String checkusername) {
		this.checkusername = checkusername;
	}

}
