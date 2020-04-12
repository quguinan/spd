/**
** create by code gen .
**/
package com.cms.model.sys;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import my.dao.utils.Record;
import my.util.MD5Util;
import my.web.IUser;
import my.web.RequestContext;
import my.base.BasePO;

@Table("SYS_USER")
@View("SYS_USER_V")
@PK({ "USERID" })
public class SysUser extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final SysUser INSTANCE = new SysUser();

	@Name
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid;

	@Column(value = "realname", type = ColumnType.STRING)
	private String realname;

	@Column(value = "username", type = ColumnType.STRING)
	private String username;

	@Column(value = "password", type = ColumnType.STRING)
	private String password;

	@Column(value = "roleid", type = ColumnType.STRING)
	private String roleid;

	@ReadOnly
	@Column(value = "rolename", type = ColumnType.STRING)
	private String rolename;

	@Column(value = "deptid", type = ColumnType.STRING)
	private String deptid;

	@ReadOnly
	@Column(value = "deptname", type = ColumnType.STRING)
	private String deptname;

	@ReadOnly
	@Column(value = "usertype", type = ColumnType.STRING)
	private String usertype;

	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname;

	@Column(value = "gspcatoryid", type = ColumnType.STRING)
	private String gspcatoryid;

	@ReadOnly
	@Column(value = "gspcatoryname", type = ColumnType.STRING)
	private String gspcatoryname;

	public String getGspcatoryname() {
		return gspcatoryname;
	}

	public void setGspcatoryname(String gspcatoryname) {
		this.gspcatoryname = gspcatoryname;
	}

	public String getGspcatoryid() {
		return gspcatoryid;
	}

	public void setGspcatoryid(String gspcatoryid) {
		this.gspcatoryid = gspcatoryid;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getSupplyname() {
		return supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String newIdForMysql() {
		Record record = INSTANCE.viewHelper().queryOne("select ifnull(max(userid),0)+1 as maxId from sys_user");
		return record.getString("maxId");
	}

	public String newIdForOracle() {
		Record record = INSTANCE.viewHelper()
				.queryOne("select decode(max(userid),null,0,max(userid))+1 as maxId from sys_user");
		return record.getString("maxId");
	}

}
