package com.cms.form;

import com.cms.model.biz.dict.DictGoods;

public class DictGoodsForm {
	private String goodsid   ;
	private String goodsname   ;
	private String commonname  ;
	private String classid   ;
	private String spell       ;
	private String ishigh      ;
	private String isstocked    ;
	private String ischarged   ;
	private String status   ;
	private String isaudited   ;
	private String highlimit   ;
	private String lowlimit   ;
	private String memo        ;
	private String productid     ;
	private String sourceid     ;
	
	
	
	public String getHighlimit() {
		return highlimit;
	}
	public void setHighlimit(String highlimit) {
		this.highlimit = highlimit;
	}
	public String getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(String lowlimit) {
		this.lowlimit = lowlimit;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getCommonname() {
		return commonname;
	}
	public void setCommonname(String commonname) {
		this.commonname = commonname;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	
	public String getIshigh() {
		return ishigh;
	}
	public void setIshigh(String ishigh) {
		this.ishigh = ishigh;
	}

	public String getIsstocked() {
		return isstocked;
	}
	public void setIsstocked(String isstocked) {
		this.isstocked = isstocked;
	}
	public String getIscharged() {
		return ischarged;
	}
	public void setIscharged(String ischarged) {
		this.ischarged = ischarged;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsaudited() {
		return isaudited;
	}
	public void setIsaudited(String isaudited) {
		this.isaudited = isaudited;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public DictGoods getEntityUpdate(){
		return new DictGoods(goodsid, goodsname, commonname, classid, spell,  ishigh, isstocked, ischarged, status, isaudited, memo, productid, sourceid);
	}
	public DictGoods getEntityAdd(){
		return new DictGoods(goodsname, commonname, classid, spell,  ishigh, isstocked, ischarged, status, isaudited, memo, productid, sourceid);
	}
}
