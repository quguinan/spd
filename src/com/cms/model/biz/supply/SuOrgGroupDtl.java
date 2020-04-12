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



@Table("SU_ORG_GROUP_DTL")
@View("SU_ORG_GROUP_DTL_V")
@PK({ "GROUPID","ORGID" })
public class SuOrgGroupDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuOrgGroupDtl INSTANCE = new SuOrgGroupDtl();
	
	@Column(value = "groupid", type = ColumnType.NUMBER)
	private String groupid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	
	@ReadOnly
	@Column(value = "groupname", type = ColumnType.STRING)
	private String groupname ;
	
	@ReadOnly
	@Column(value = "orgname", type = ColumnType.STRING)
	private String orgname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@ReadOnly
	@Column(value = "logname", type = ColumnType.STRING)
	private String logname ;
	
	@ReadOnly
	@Column(value = "password", type = ColumnType.STRING)
	private String password ;
	


	public String getGroupid (){
		return groupid;
	}
		 
	public void setGroupid (String groupid){
		this.groupid=groupid;
	}

	public String getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (String orgid){
		this.orgid=orgid;
	}

	public String getGroupname (){
		return groupname;
	}
		 
	public void setGroupname (String groupname){
		this.groupname=groupname;
	}

	public String getOrgname (){
		return orgname;
	}
		 
	public void setOrgname (String orgname){
		this.orgname=orgname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getLogname (){
		return logname;
	}
		 
	public void setLogname (String logname){
		this.logname=logname;
	}

	public String getPassword (){
		return password;
	}
		 
	public void setPassword (String password){
		this.password=password;
	}

}
