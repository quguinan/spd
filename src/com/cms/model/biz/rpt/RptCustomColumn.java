/**
** create by code gen .
**/
package com.cms.model.biz.rpt;


import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;



@Table("RPT_CUSTOM_COLUMN")
@View("RPT_CUSTOM_COLUMN_V")
@PK({ "DTLID" })
public class RptCustomColumn extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RptCustomColumn INSTANCE = new RptCustomColumn();
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private String dtlid ;
	
	@Column(value = "docid", type = ColumnType.NUMBER)
	private String docid ;
	
	@Column(value = "field", type = ColumnType.STRING)
	private String field ;
	
	@Column(value = "title", type = ColumnType.STRING)
	private String title ;
	
	@Column(value = "align", type = ColumnType.STRING)
	private String align ;
	
	@Column(value = "width", type = ColumnType.STRING)
	private String width ;
	
//	@Column(value = "hidden", type = ColumnType.STRING)
//	private String hidden ;
	


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

	public String getField (){
		return field;
	}
		 
	public void setField (String field){
		this.field=field;
	}

	public String getTitle (){
		return title;
	}
		 
	public void setTitle (String title){
		this.title=title;
	}

	public String getAlign (){
		return align;
	}
		 
	public void setAlign (String align){
		this.align=align;
	}

	public String getWidth (){
		return width;
	}
		 
	public void setWidth (String width){
		this.width=width;
	}

//	public String getHidden (){
//		return hidden;
//	}
//		 
//	public void setHidden (String hidden){
//		this.hidden=hidden;
//	}

}
