/**
** create by code gen .
**/
package com.cms.model.sys;


import my.dao.annotation.Column;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;



@Table("SYS_DEPT")
@View("SYS_DEPT_V")
@PK({ "DEPTID" })
public class SysDept extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysDept INSTANCE = new SysDept();
	
	@Name
	@Column(value = "deptid", type = ColumnType.STRING)
	private String deptid ;
	
	@Column(value = "deptpid", type = ColumnType.STRING)
	private String deptpid ;
	
	@Column(value = "deptname", type = ColumnType.STRING)
	private String deptname ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	


	public String getDeptid (){
		return deptid;
	}
		 
	public void setDeptid (String deptid){
		this.deptid=deptid;
	}

	public String getDeptpid (){
		return deptpid;
	}
		 
	public void setDeptpid (String deptpid){
		this.deptpid=deptpid;
	}

	public String getDeptname (){
		return deptname;
	}
		 
	public void setDeptname (String deptname){
		this.deptname=deptname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

}
