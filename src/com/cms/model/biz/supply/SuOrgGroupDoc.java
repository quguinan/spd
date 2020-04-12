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



@Table("SU_ORG_GROUP_DOC")
@View("SU_ORG_GROUP_DOC_V")
@PK({ "GROUPID" })
public class SuOrgGroupDoc extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuOrgGroupDoc INSTANCE = new SuOrgGroupDoc();
	
	@Id
	@Column(value = "groupid", type = ColumnType.NUMBER)
	private String groupid ;
	
	@Column(value = "groupname", type = ColumnType.STRING)
	private String groupname ;
	


	public String getGroupid (){
		return groupid;
	}
		 
	public void setGroupid (String groupid){
		this.groupid=groupid;
	}

	public String getGroupname (){
		return groupname;
	}
		 
	public void setGroupname (String groupname){
		this.groupname=groupname;
	}

}
