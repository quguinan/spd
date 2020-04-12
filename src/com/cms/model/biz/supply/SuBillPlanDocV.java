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

@Table("SU_BILL_PLAN_DOC_V")
@View("SU_BILL_PLAN_DOC_V")
@PK({})
public class SuBillPlanDocV extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final SuBillPlanDocV INSTANCE = new SuBillPlanDocV();

	@Column(value = "plandocid", type = ColumnType.NUMBER)
	private Long plandocid;

	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate;

	@Column(value = "entryname", type = ColumnType.STRING)
	private String entryname;

	@Column(value = "supplyid", type = ColumnType.NUMBER)
	private Long supplyid;

	public Long getPlandocid() {
		return plandocid;
	}

	public void setPlandocid(Long plandocid) {
		this.plandocid = plandocid;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	public String getEntryname() {
		return entryname;
	}

	public void setEntryname(String entryname) {
		this.entryname = entryname;
	}

	public Long getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(Long supplyid) {
		this.supplyid = supplyid;
	}

}
