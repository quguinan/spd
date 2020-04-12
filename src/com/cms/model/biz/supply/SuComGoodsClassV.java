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



@Table("SU_COM_GOODS_CLASS_V")
@View("SU_COM_GOODS_CLASS_V")
@PK({  })
public class SuComGoodsClassV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SuComGoodsClassV INSTANCE = new SuComGoodsClassV();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "classcode", type = ColumnType.STRING)
	private String classcode ;
	
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	


	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public String getClasscode (){
		return classcode;
	}
		 
	public void setClasscode (String classcode){
		this.classcode=classcode;
	}

	public String getClassname (){
		return classname;
	}
		 
	public void setClassname (String classname){
		this.classname=classname;
	}

}
