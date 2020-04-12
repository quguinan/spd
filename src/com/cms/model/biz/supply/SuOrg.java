/**
** create by code gen .
**/
package com.cms.model.biz.supply;


import my.dao.annotation.Column;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;



@Table("SU_ORG")
@View("SU_ORG_V")
@PK({ "ORGID" })
public class SuOrg extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuOrg INSTANCE = new SuOrg();
	
	@Name
	@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid ;
	
	@Column(value = "orgname", type = ColumnType.STRING)
	private String orgname ;

	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "logname", type = ColumnType.STRING)
	private String logname ;
	
	@Column(value = "password", type = ColumnType.STRING)
	private String password ;
	
	
	
	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	


}
