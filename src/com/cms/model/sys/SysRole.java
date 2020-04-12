/**
** create by code gen .
**/
package com.cms.model.sys;

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



@Table("SYS_ROLE")
@View("SYS_ROLE_V")
@PK({ "ROLEID" })
public class SysRole extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysRole INSTANCE = new SysRole();
	
	
	@Column(value = "roleid", type = ColumnType.STRING)
	private String roleid ;
	
	@Column(value = "rolepid", type = ColumnType.STRING)
	private String rolepid ;
	
	@Column(value = "rolename", type = ColumnType.STRING)
	private String rolename ;
	
	

	
	
	
	


	public String getRolepid() {
		return rolepid;
	}

	public void setRolepid(String rolepid) {
		this.rolepid = rolepid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename (){
		return rolename;
	}
		 
	public void setRolename (String rolename){
		this.rolename=rolename;
	}


	public SysRole(String rolename) {
		super();
		this.rolename = rolename;
	}

	public SysRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*public Integer getNextRoleId(){
		SysRole sysRole=SysRole.INSTANCE.queryOne(" roleid in (select max(roleid) from sys_role)");
		if (sysRole==null ){
			return 1; //无记录,则从1开始
		}else{
			return sysRole.getRoleid()+1;
		}
	}*/

	public SysRole(String roleid, String rolepid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolepid = rolepid;
		this.rolename = rolename;
	}

	public SysRole(String rolepid, String rolename) {
		super();
		this.rolepid = rolepid;
		this.rolename = rolename;
	}

	

	
}
