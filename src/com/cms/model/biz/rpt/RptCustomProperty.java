/**
** create by code gen .
**/
package com.cms.model.biz.rpt;

import java.util.Date;

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



@Table("RPT_CUSTOM_PROPERTY")
@View("RPT_CUSTOM_PROPERTY_V")
@PK({ "DTLID" })
public class RptCustomProperty extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptCustomProperty INSTANCE = new RptCustomProperty();
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private String dtlid ;
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
	@Column(value = "proname", type = ColumnType.STRING)
	private String proname ;
	
	@Column(value = "provalue", type = ColumnType.STRING)
	private String provalue ;
	


	public RptCustomProperty(String proname, String provalue) {
		super();
		this.proname = proname;
		this.provalue = provalue;
	}

	public RptCustomProperty(String proname) {
		super();
		this.proname = proname;
	}

	public RptCustomProperty() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getProname (){
		return proname;
	}
		 
	public void setProname (String proname){
		this.proname=proname;
	}

	public String getProvalue (){
		return provalue;
	}
		 
	public void setProvalue (String provalue){
		this.provalue=provalue;
	}

}
