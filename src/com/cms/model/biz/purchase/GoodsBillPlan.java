/**
** create by code gen .
**/
package com.cms.model.biz.purchase;

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



@Table("GOODS_BILL_PLAN")
@View("GOODS_BILL_PLAN_V")
@PK({ "DOCID" })
public class GoodsBillPlan extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBillPlan INSTANCE = new GoodsBillPlan();
	
	@Name
	@Column(value = "docid", type = ColumnType.STRING)
	private String docid ;
	
	@Column(value = "docno", type = ColumnType.STRING)
	private String docno ;
	
	@Column(value = "createrid", type = ColumnType.STRING)
	private String createrid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "createdate", type = ColumnType.DATE)
	private String createdate ;
	
	@Column(value = "checkerid", type = ColumnType.STRING)
	private String checkerid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	


	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getDocno (){
		return docno;
	}
		 
	public void setDocno (String docno){
		this.docno=docno;
	}

	public String getCreaterid (){
		return createrid;
	}
		 
	public void setCreaterid (String createrid){
		this.createrid=createrid;
	}

	public String getCreatedate (){
		return createdate;
	}
		 
	public void setCreatedate (String createdate){
		this.createdate=createdate;
	}

	public String getCheckerid (){
		return checkerid;
	}
		 
	public void setCheckerid (String checkerid){
		this.checkerid=checkerid;
	}

	public String getCheckdate (){
		return checkdate;
	}
		 
	public void setCheckdate (String checkdate){
		this.checkdate=checkdate;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

}
