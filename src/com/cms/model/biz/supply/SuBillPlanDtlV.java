/**
** create by code gen .
**/
package com.cms.model.biz.supply;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;

@Table("ZX_BMS_SU_PLAN@DL_ZMYY")
@View("SU_BILL_PLAN_DTL_V")
@PK({})
public class SuBillPlanDtlV extends BasePO {

	/**
	* 
	*/
	private static final Long serialVersionUID = 1L;

	public static final SuBillPlanDtlV INSTANCE = new SuBillPlanDtlV();

	@Id
	@Column(value = "planid", type = ColumnType.STRING)
	private String planid;

	@ReadOnly
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid;

	@ReadOnly
	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	@ReadOnly
	@Column(value = "supplyerid", type = ColumnType.STRING)
	private String supplyerid;

	@ReadOnly
	@Column(value = "planqty", type = ColumnType.STRING)
	private String planqty;

	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "plandate", type = ColumnType.DATE)
	private String plandate;

	@ReadOnly
	@Column(value = "goodsqty", type = ColumnType.STRING)
	private String goodsqty;

	@ReadOnly
	@Column(value = "unitprice", type = ColumnType.STRING)
	private String unitprice;

	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "arrivedate", type = ColumnType.DATE)
	private String arrivedate;
	@ReadOnly
	@Column(value = "paylimit", type = ColumnType.STRING)
	private String paylimit;

	@ReadOnly
	@Column(value = "clause", type = ColumnType.STRING)
	private String clause;
	@ReadOnly
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;
	@ReadOnly
	@Column(value = "usestatus", type = ColumnType.STRING)
	private String usestatus;
	@ReadOnly
	@Column(value = "sucondtlid", type = ColumnType.STRING)
	private String sucondtlid;
	@ReadOnly
	@Column(value = "goodsuseqty", type = ColumnType.STRING)
	private String goodsuseqty;
	@ReadOnly
	@Column(value = "empid", type = ColumnType.STRING)
	private String empid;
	@ReadOnly
	@Column(value = "protocaldtlid", type = ColumnType.STRING)
	private String protocaldtlid;
	@ReadOnly
	@Column(value = "agentid", type = ColumnType.STRING)
	private String agentid;
	@ReadOnly
	@Column(value = "plandocid", type = ColumnType.STRING)
	private String plandocid;
	@ReadOnly
	@Column(value = "invalidmanid", type = ColumnType.STRING)
	private String invalidmanid;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "invaliddate", type = ColumnType.DATE)
	private String invaliddate;
	@ReadOnly
	@Column(value = "askpriceid", type = ColumnType.STRING)
	private String askpriceid;
	@ReadOnly
	@Column(value = "sourceid", type = ColumnType.STRING)
	private String sourceid;
	@ReadOnly
	@Column(value = "comfromsupply", type = ColumnType.STRING)
	private String comfromsupply;
	@ReadOnly
	@Column(value = "highestpirce", type = ColumnType.STRING)
	private String highestpirce;
	@ReadOnly
	@Column(value = "overhighestprice", type = ColumnType.STRING)
	private String overhighestprice;
	@ReadOnly
	@Column(value = "overhighestreason", type = ColumnType.STRING)
	private String overhighestreason;
	@ReadOnly
	@Column(value = "overhigheststatus", type = ColumnType.STRING)
	private String overhigheststatus;
	@ReadOnly
	@Column(value = "checkmanid", type = ColumnType.STRING)
	private String checkmanid;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate;
	@ReadOnly
	@Column(value = "createordermanid", type = ColumnType.STRING)
	private String createordermanid;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "createorderdate", type = ColumnType.DATE)
	private String createorderdate;
	@ReadOnly
	@Column(value = "paymethod", type = ColumnType.STRING)
	private String paymethod;
	@ReadOnly
	@Column(value = "supplyerdeptid", type = ColumnType.STRING)
	private String supplyerdeptid;
	@ReadOnly
	@Column(value = "approvemsg", type = ColumnType.STRING)
	private String approvemsg;
	@ReadOnly
	@Column(value = "ordertype", type = ColumnType.STRING)
	private String ordertype;
	@ReadOnly
	@Column(value = "orderstatus", type = ColumnType.STRING)
	private String orderstatus;
	@ReadOnly
	@Column(value = "ordercheckmanid", type = ColumnType.STRING)
	private String ordercheckmanid;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "ordercheckdate", type = ColumnType.DATE)
	private String ordercheckdate;
	@ReadOnly
	@Column(value = "orderapprovemsg", type = ColumnType.STRING)
	private String orderapprovemsg;
	@ReadOnly
	@Column(value = "hisinterflag", type = ColumnType.STRING)
	private String hisinterflag;
	@ReadOnly
	@Column(value = "lastsuprice", type = ColumnType.STRING)
	private String lastsuprice;
	@ReadOnly
	@Column(value = "confirmmanid", type = ColumnType.STRING)
	private String confirmmanid;
	@ReadOnly
	@Column(value = "stopreason", type = ColumnType.STRING)
	private String stopreason;
	@ReadOnly
	@Column(value = "zx_planqty", type = ColumnType.STRING)
	private String zx_planqty;
	@ReadOnly
	@Column(value = "risepricereason", type = ColumnType.STRING)
	private String risepricereason;
	@ReadOnly
	@Column(value = "stopmanid", type = ColumnType.STRING)
	private String stopmanid;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "stopdate", type = ColumnType.DATE)
	private String stopdate;
	@ReadOnly
	@Column(value = "modifysrn", type = ColumnType.STRING)
	private String modifysrn;
	@ReadOnly
	@Column(value = "areaprice", type = ColumnType.NUMBER)
	private String areaprice;
	@ReadOnly
	@Column(value = "usedepart", type = ColumnType.STRING)
	private String usedepart;
	@ReadOnly
	@Column(value = "regnumber", type = ColumnType.STRING)
	private String regnumber;
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "usedate", type = ColumnType.DATE)
	private String usedate;
	@ReadOnly
	@Column(value = "username", type = ColumnType.STRING)
	private String username;
	
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "regdate", type = ColumnType.DATE)
	private String regdate;
	
	@ReadOnly
	@Column(value = "saleprice", type = ColumnType.STRING)
	private String saleprice;

	@Column(value = "backqty", type = ColumnType.STRING)
	private String backqty;

	@Column(value = "backprice", type = ColumnType.STRING)
	private String backprice;

	@Column(value = "backflag", type = ColumnType.STRING)
	private String backflag;

	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "backdate", type = ColumnType.DATE)
	private String backdate;

	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname;

	@ReadOnly
	@Column(value = "goodstype", type = ColumnType.STRING)
	private String goodstype;

	@ReadOnly
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname;

	@ReadOnly
	@Column(value = "opcode", type = ColumnType.STRING)
	private String opcode;

	@ReadOnly
	@Column(value = "goodsunit", type = ColumnType.STRING)
	private String goodsunit;

	@ReadOnly
	@Column(value = "supgoodsid", type = ColumnType.NUMBER)
	private String supgoodsid;

	@ReadOnly
	@Column(value = "supgoodsname", type = ColumnType.STRING)
	private String supgoodsname;

	@ReadOnly
	@Column(value = "supgoodstype", type = ColumnType.STRING)
	private String supgoodstype;

	@ReadOnly
	@Column(value = "supfactory", type = ColumnType.STRING)
	private String supfactory;

	@ReadOnly
	@Column(value = "supgoodsunit", type = ColumnType.STRING)
	private String supgoodsunit;

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getSupplyerid() {
		return supplyerid;
	}

	public void setSupplyerid(String supplyerid) {
		this.supplyerid = supplyerid;
	}

	public String getPlanqty() {
		return planqty;
	}

	public void setPlanqty(String planqty) {
		this.planqty = planqty;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getGoodsqty() {
		return goodsqty;
	}

	public void setGoodsqty(String goodsqty) {
		this.goodsqty = goodsqty;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getArrivedate() {
		return arrivedate;
	}

	public void setArrivedate(String arrivedate) {
		this.arrivedate = arrivedate;
	}

	public String getPaylimit() {
		return paylimit;
	}

	public void setPaylimit(String paylimit) {
		this.paylimit = paylimit;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUsestatus() {
		return usestatus;
	}

	public void setUsestatus(String usestatus) {
		this.usestatus = usestatus;
	}

	public String getSucondtlid() {
		return sucondtlid;
	}

	public void setSucondtlid(String sucondtlid) {
		this.sucondtlid = sucondtlid;
	}

	public String getGoodsuseqty() {
		return goodsuseqty;
	}

	public void setGoodsuseqty(String goodsuseqty) {
		this.goodsuseqty = goodsuseqty;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getProtocaldtlid() {
		return protocaldtlid;
	}

	public void setProtocaldtlid(String protocaldtlid) {
		this.protocaldtlid = protocaldtlid;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getPlandocid() {
		return plandocid;
	}

	public void setPlandocid(String plandocid) {
		this.plandocid = plandocid;
	}

	public String getInvalidmanid() {
		return invalidmanid;
	}

	public void setInvalidmanid(String invalidmanid) {
		this.invalidmanid = invalidmanid;
	}

	public String getInvaliddate() {
		return invaliddate;
	}

	public void setInvaliddate(String invaliddate) {
		this.invaliddate = invaliddate;
	}

	public String getAskpriceid() {
		return askpriceid;
	}

	public void setAskpriceid(String askpriceid) {
		this.askpriceid = askpriceid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getComfromsupply() {
		return comfromsupply;
	}

	public void setComfromsupply(String comfromsupply) {
		this.comfromsupply = comfromsupply;
	}

	public String getHighestpirce() {
		return highestpirce;
	}

	public void setHighestpirce(String highestpirce) {
		this.highestpirce = highestpirce;
	}

	public String getOverhighestprice() {
		return overhighestprice;
	}

	public void setOverhighestprice(String overhighestprice) {
		this.overhighestprice = overhighestprice;
	}

	public String getOverhighestreason() {
		return overhighestreason;
	}

	public void setOverhighestreason(String overhighestreason) {
		this.overhighestreason = overhighestreason;
	}

	public String getOverhigheststatus() {
		return overhigheststatus;
	}

	public void setOverhigheststatus(String overhigheststatus) {
		this.overhigheststatus = overhigheststatus;
	}

	public String getCheckmanid() {
		return checkmanid;
	}

	public void setCheckmanid(String checkmanid) {
		this.checkmanid = checkmanid;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getCreateordermanid() {
		return createordermanid;
	}

	public void setCreateordermanid(String createordermanid) {
		this.createordermanid = createordermanid;
	}

	public String getCreateorderdate() {
		return createorderdate;
	}

	public void setCreateorderdate(String createorderdate) {
		this.createorderdate = createorderdate;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getSupplyerdeptid() {
		return supplyerdeptid;
	}

	public void setSupplyerdeptid(String supplyerdeptid) {
		this.supplyerdeptid = supplyerdeptid;
	}

	public String getApprovemsg() {
		return approvemsg;
	}

	public void setApprovemsg(String approvemsg) {
		this.approvemsg = approvemsg;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getOrdercheckmanid() {
		return ordercheckmanid;
	}

	public void setOrdercheckmanid(String ordercheckmanid) {
		this.ordercheckmanid = ordercheckmanid;
	}

	public String getOrdercheckdate() {
		return ordercheckdate;
	}

	public void setOrdercheckdate(String ordercheckdate) {
		this.ordercheckdate = ordercheckdate;
	}

	public String getOrderapprovemsg() {
		return orderapprovemsg;
	}

	public void setOrderapprovemsg(String orderapprovemsg) {
		this.orderapprovemsg = orderapprovemsg;
	}

	public String getHisinterflag() {
		return hisinterflag;
	}

	public void setHisinterflag(String hisinterflag) {
		this.hisinterflag = hisinterflag;
	}

	public String getLastsuprice() {
		return lastsuprice;
	}

	public void setLastsuprice(String lastsuprice) {
		this.lastsuprice = lastsuprice;
	}

	public String getConfirmmanid() {
		return confirmmanid;
	}

	public void setConfirmmanid(String confirmmanid) {
		this.confirmmanid = confirmmanid;
	}

	public String getStopreason() {
		return stopreason;
	}

	public void setStopreason(String stopreason) {
		this.stopreason = stopreason;
	}

	public String getZx_planqty() {
		return zx_planqty;
	}

	public void setZx_planqty(String zx_planqty) {
		this.zx_planqty = zx_planqty;
	}

	public String getRisepricereason() {
		return risepricereason;
	}

	public void setRisepricereason(String risepricereason) {
		this.risepricereason = risepricereason;
	}

	public String getStopmanid() {
		return stopmanid;
	}

	public void setStopmanid(String stopmanid) {
		this.stopmanid = stopmanid;
	}

	public String getStopdate() {
		return stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	public String getModifysrn() {
		return modifysrn;
	}

	public void setModifysrn(String modifysrn) {
		this.modifysrn = modifysrn;
	}

	public String getAreaprice() {
		return areaprice;
	}

	public void setAreaprice(String areaprice) {
		this.areaprice = areaprice;
	}

	public String getUsedepart() {
		return usedepart;
	}

	public void setUsedepart(String usedepart) {
		this.usedepart = usedepart;
	}

	public String getRegnumber() {
		return regnumber;
	}

	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}

	public String getUsedate() {
		return usedate;
	}

	public void setUsedate(String usedate) {
		this.usedate = usedate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getBackqty() {
		return backqty;
	}

	public void setBackqty(String backqty) {
		this.backqty = backqty;
	}

	public String getBackprice() {
		return backprice;
	}

	public void setBackprice(String backprice) {
		this.backprice = backprice;
	}

	public String getBackflag() {
		return backflag;
	}

	public void setBackflag(String backflag) {
		this.backflag = backflag;
	}

	public String getBackdate() {
		return backdate;
	}

	public void setBackdate(String backdate) {
		this.backdate = backdate;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getGoodsunit() {
		return goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	public String getSupgoodsid() {
		return supgoodsid;
	}

	public void setSupgoodsid(String supgoodsid) {
		this.supgoodsid = supgoodsid;
	}

	public String getSupgoodsname() {
		return supgoodsname;
	}

	public void setSupgoodsname(String supgoodsname) {
		this.supgoodsname = supgoodsname;
	}

	public String getSupgoodstype() {
		return supgoodstype;
	}

	public void setSupgoodstype(String supgoodstype) {
		this.supgoodstype = supgoodstype;
	}

	public String getSupfactory() {
		return supfactory;
	}

	public void setSupfactory(String supfactory) {
		this.supfactory = supfactory;
	}

	public String getSupgoodsunit() {
		return supgoodsunit;
	}

	public void setSupgoodsunit(String supgoodsunit) {
		this.supgoodsunit = supgoodsunit;
	}

}
