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



@Table("RPT_EVENTS")
@View("RPT_EVENTS_V")
@PK({ "EVENTID" })
public class RptEvents extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptEvents INSTANCE = new RptEvents();
	
	@Column(value = "eventid", type = ColumnType.NUMBER)
	private BigDecimal eventid ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "eventparam", type = ColumnType.STRING)
	private String eventparam ;
	
	@Column(value = "eventvale", type = ColumnType.STRING)
	private String eventvale ;
	
	@Column(value = "eventmemo", type = ColumnType.STRING)
	private String eventmemo ;
	


	

	public BigDecimal getEventid (){
		return eventid;
	}
		 
	public void setEventid (BigDecimal eventid){
		this.eventid=eventid;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getEventparam (){
		return eventparam;
	}
		 
	public void setEventparam (String eventparam){
		this.eventparam=eventparam;
	}

	public String getEventvale (){
		return eventvale;
	}
		 
	public void setEventvale (String eventvale){
		this.eventvale=eventvale;
	}

	public String getEventmemo (){
		return eventmemo;
	}
		 
	public void setEventmemo (String eventmemo){
		this.eventmemo=eventmemo;
	}

}
