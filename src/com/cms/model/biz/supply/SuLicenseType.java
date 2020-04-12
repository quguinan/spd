/**
** create by code gen .
**/
package com.cms.model.biz.supply;


import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;



@Table("SU_LICENSE_TYPE")
@View("SU_LICENSE_TYPE_V")
@PK({ "licensetypeid" })
public class SuLicenseType extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuLicenseType INSTANCE = new SuLicenseType();
	
	@Column(value = "licensetypeid", type = ColumnType.NUMBER)
	private String licensetypeid ;
	
	@Column(value = "licensetypename", type = ColumnType.STRING)
	private String licensetypename ;
	
	@Column(value = "customflag", type = ColumnType.NUMBER)
	private String customflag ;
	
	@Column(value = "goodsflag", type = ColumnType.NUMBER)
	private String goodsflag ;
	
	@Column(value = "supplierflag", type = ColumnType.NUMBER)
	private String supplierflag ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "earlywarndays", type = ColumnType.NUMBER)
	private String earlywarndays ;
	
	@Column(value = "superdays", type = ColumnType.NUMBER)
	private String superdays ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate ;

	public String getLicensetypeid() {
		return licensetypeid;
	}

	public void setLicensetypeid(String licensetypeid) {
		this.licensetypeid = licensetypeid;
	}

	public String getLicensetypename() {
		return licensetypename;
	}

	public void setLicensetypename(String licensetypename) {
		this.licensetypename = licensetypename;
	}

	public String getCustomflag() {
		return customflag;
	}

	public void setCustomflag(String customflag) {
		this.customflag = customflag;
	}

	public String getGoodsflag() {
		return goodsflag;
	}

	public void setGoodsflag(String goodsflag) {
		this.goodsflag = goodsflag;
	}

	public String getSupplierflag() {
		return supplierflag;
	}

	public void setSupplierflag(String supplierflag) {
		this.supplierflag = supplierflag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEarlywarndays() {
		return earlywarndays;
	}

	public void setEarlywarndays(String earlywarndays) {
		this.earlywarndays = earlywarndays;
	}

	public String getSuperdays() {
		return superdays;
	}

	public void setSuperdays(String superdays) {
		this.superdays = superdays;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}
	


	

}
