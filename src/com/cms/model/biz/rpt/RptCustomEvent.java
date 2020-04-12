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



@Table("RPT_CUSTOM_EVENT")
@View("RPT_CUSTOM_EVENT_V")
@PK({  })
public class RptCustomEvent extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptCustomEvent INSTANCE = new RptCustomEvent();
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private BigDecimal dtlid ;
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private BigDecimal docid ;
	
	@Column(value = "eventname", type = ColumnType.STRING)
	private String eventname ;
	
	@Column(value = "eventparam", type = ColumnType.STRING)
	private String eventparam ;
	
	@Column(value = "eventvalue", type = ColumnType.STRING)
	private String eventvalue ;
	


	public RptCustomEvent(String eventname, String eventparam, String eventvalue) {
		super();
		this.eventname = eventname;
		this.eventparam = eventparam;
		this.eventvalue = eventvalue;
	}

	public RptCustomEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getEventname (){
		return eventname;
	}
		 
	public void setEventname (String eventname){
		this.eventname=eventname;
	}

	public String getEventparam (){
		return eventparam;
	}
		 
	public void setEventparam (String eventparam){
		this.eventparam=eventparam;
	}

	public String getEventvalue (){
		return eventvalue;
	}
		 
	public void setEventvalue (String eventvalue){
		this.eventvalue=eventvalue;
	}

}
