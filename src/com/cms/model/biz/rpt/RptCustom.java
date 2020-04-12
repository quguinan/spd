/**
** create by code gen .
**/
package com.cms.model.biz.rpt;

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



@Table("RPT_CUSTOM")
@View("RPT_CUSTOM_V")
@PK({ "DOCID" })
public class RptCustom extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptCustom INSTANCE = new RptCustom();
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
	@Column(value = "rptname", type = ColumnType.STRING)
	private String rptname ;
	
	@Column(value = "sqltext", type = ColumnType.STRING)
	private String sqltext ;
	


	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getRptname (){
		return rptname;
	}
		 
	public void setRptname (String rptname){
		this.rptname=rptname;
	}

	public String getSqltext (){
		return sqltext;
	}
		 
	public void setSqltext (String sqltext){
		this.sqltext=sqltext;
	}

}
