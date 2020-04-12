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



@Table("SU_SUPPLY_LICENSE")
@View("SU_SUPPLY_LICENSE_V")
@PK({ "LICENSEID" })
public class SuSupplyLicense extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuSupplyLicense INSTANCE = new SuSupplyLicense();
	
	@Column(value = "licenseid", type = ColumnType.NUMBER)
	private String licenseid ;
	
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;
	
	@ReadOnly
	@Column(value = "licensetypename", type = ColumnType.STRING)
	private String licensetypename ;
	
	@Column(value = "licensetypeid", type = ColumnType.NUMBER)
	private String licensetypeid ;
	
	@Column(value = "licensename", type = ColumnType.STRING)
	private String licensename ;
	
	@Column(value = "licensecode", type = ColumnType.STRING)
	private String licensecode ;
	
	@Column(value = "relatecompany", type = ColumnType.STRING)
	private String relatecompany ;
	
	@Column(value = "relategoods", type = ColumnType.STRING)
	private String relategoods ;
	
	@Column(value = "signdeptment", type = ColumnType.STRING)
	private String signdeptment ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "signdate", type = ColumnType.DATE)
	private String signdate ;
	
	@Column(value = "busiscopeids", type = ColumnType.STRING)
	private String busiscopeids ;
	
	@Column(value = "maincontent", type = ColumnType.STRING)
	private String maincontent ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "startdate", type = ColumnType.DATE)
	private String startdate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "enddate", type = ColumnType.DATE)
	private String enddate ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate ;
	
	@Column(value = "picurl", type = ColumnType.STRING)
	private String picurl ;
	
	@Column(value = "viewurl", type = ColumnType.STRING)
	private String viewurl ;
	

	
	
	public String getViewurl() {
		return viewurl;
	}

	public void setViewurl(String viewurl) {
		this.viewurl = viewurl;
	}

	public SuSupplyLicense(String licenseid,  String supplyid, String licensetypeid) {
		super();
		this.licenseid = licenseid;
		this.supplyid = supplyid;
		this.licensetypeid = licensetypeid;
	}

	public SuSupplyLicense() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getSupplyname() {
		return supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}

	public String getLicensetypename() {
		return licensetypename;
	}

	public void setLicensetypename(String licensetypename) {
		this.licensetypename = licensetypename;
	}

	public String getLicenseid (){
		return licenseid;
	}
		 
	public void setLicenseid (String licenseid){
		this.licenseid=licenseid;
	}

	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
	}

	public String getLicensetypeid (){
		return licensetypeid;
	}
		 
	public void setLicensetypeid (String licensetypeid){
		this.licensetypeid=licensetypeid;
	}

	public String getLicensename (){
		return licensename;
	}
		 
	public void setLicensename (String licensename){
		this.licensename=licensename;
	}

	public String getLicensecode (){
		return licensecode;
	}
		 
	public void setLicensecode (String licensecode){
		this.licensecode=licensecode;
	}

	public String getRelatecompany (){
		return relatecompany;
	}
		 
	public void setRelatecompany (String relatecompany){
		this.relatecompany=relatecompany;
	}

	public String getRelategoods (){
		return relategoods;
	}
		 
	public void setRelategoods (String relategoods){
		this.relategoods=relategoods;
	}

	public String getSigndeptment (){
		return signdeptment;
	}
		 
	public void setSigndeptment (String signdeptment){
		this.signdeptment=signdeptment;
	}

	public String getSigndate (){
		return signdate;
	}
		 
	public void setSigndate (String signdate){
		this.signdate=signdate;
	}

	public String getBusiscopeids (){
		return busiscopeids;
	}
		 
	public void setBusiscopeids (String busiscopeids){
		this.busiscopeids=busiscopeids;
	}

	public String getMaincontent (){
		return maincontent;
	}
		 
	public void setMaincontent (String maincontent){
		this.maincontent=maincontent;
	}

	public String getStartdate (){
		return startdate;
	}
		 
	public void setStartdate (String startdate){
		this.startdate=startdate;
	}

	public String getEnddate (){
		return enddate;
	}
		 
	public void setEnddate (String enddate){
		this.enddate=enddate;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getCredate (){
		return credate;
	}
		 
	public void setCredate (String credate){
		this.credate=credate;
	}

}
