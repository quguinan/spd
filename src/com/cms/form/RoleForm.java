package com.cms.form;

import com.cms.model.sys.SysRole;

public class RoleForm {
	private String roleid;
	private String rolepid;
	private String rolename;
	
	
	
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
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public SysRole getEntityUpdate(){
		SysRole role=new SysRole(roleid,rolepid, rolename);
		return role;
	}
	public SysRole getEntityAdd(){
		SysRole role=new SysRole(rolepid,rolename);
		return role;
	}
}
