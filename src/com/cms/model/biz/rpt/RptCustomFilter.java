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



@Table("RPT_CUSTOM_FILTER")
@View("RPT_CUSTOM_FILTER_V")
@PK({ "DTLID" })
public class RptCustomFilter extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptCustomFilter INSTANCE = new RptCustomFilter();
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private BigDecimal dtlid ;
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private BigDecimal docid ;
	


	public BigDecimal getDtlid (){
		return dtlid;
	}
		 
	public void setDtlid (BigDecimal dtlid){
		this.dtlid=dtlid;
	}

	public BigDecimal getDocid (){
		return docid;
	}
		 
	public void setDocid (BigDecimal docid){
		this.docid=docid;
	}

}
