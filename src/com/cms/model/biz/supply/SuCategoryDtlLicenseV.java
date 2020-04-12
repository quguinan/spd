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



@Table("SU_CATEGORY_DTL_LICENSE_V")
@View("SU_CATEGORY_DTL_LICENSE_V")
@PK({  })
public class SuCategoryDtlLicenseV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuCategoryDtlLicenseV INSTANCE = new SuCategoryDtlLicenseV();
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private String dtlid ;
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
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
	
	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private String supplyid ;
	
	@Column(value = "licenseid", type = ColumnType.NUMBER)
	private String licenseid ;
	
	@Column(value = "iconflag", type = ColumnType.STRING)
	private String iconflag ;
	



	public String getIconflag() {
		return iconflag;
	}

	public void setIconflag(String iconflag) {
		this.iconflag = iconflag;
	}

	public String getDtlid (){
		return dtlid;
	}
		 
	public void setDtlid (String dtlid){
		this.dtlid=dtlid;
	}

	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getLicensetypeid (){
		return licensetypeid;
	}
		 
	public void setLicensetypeid (String licensetypeid){
		this.licensetypeid=licensetypeid;
	}

	public String getLicensetypename (){
		return licensetypename;
	}
		 
	public void setLicensetypename (String licensetypename){
		this.licensetypename=licensetypename;
	}

	public String getCustomflag (){
		return customflag;
	}
		 
	public void setCustomflag (String customflag){
		this.customflag=customflag;
	}

	public String getGoodsflag (){
		return goodsflag;
	}
		 
	public void setGoodsflag (String goodsflag){
		this.goodsflag=goodsflag;
	}

	public String getSupplierflag (){
		return supplierflag;
	}
		 
	public void setSupplierflag (String supplierflag){
		this.supplierflag=supplierflag;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getEarlywarndays (){
		return earlywarndays;
	}
		 
	public void setEarlywarndays (String earlywarndays){
		this.earlywarndays=earlywarndays;
	}

	public String getSuperdays (){
		return superdays;
	}
		 
	public void setSuperdays (String superdays){
		this.superdays=superdays;
	}

	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
	}

	public String getLicenseid (){
		return licenseid;
	}
		 
	public void setLicenseid (String licenseid){
		this.licenseid=licenseid;
	}

}
