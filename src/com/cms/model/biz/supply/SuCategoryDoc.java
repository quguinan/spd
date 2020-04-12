/**
** create by code gen .
**/
package com.cms.model.biz.supply;


import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;



@Table("SU_CATEGORY_DOC")
@View("SU_CATEGORY_DOC_V")
@PK({ "DOCID" })
public class SuCategoryDoc extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuCategoryDoc INSTANCE = new SuCategoryDoc();
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "ctrlrange", type = ColumnType.STRING)
	private String ctrlrange ;
	
	@Column(value = "ctrltime", type = ColumnType.STRING)
	private String ctrltime ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate ;
	


	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getCtrlrange (){
		return ctrlrange;
	}
		 
	public void setCtrlrange (String ctrlrange){
		this.ctrlrange=ctrlrange;
	}

	public String getCtrltime (){
		return ctrltime;
	}
		 
	public void setCtrltime (String ctrltime){
		this.ctrltime=ctrltime;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
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
