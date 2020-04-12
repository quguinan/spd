package com.cms.controller.biz.goods.supply.bill;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuBillOrderDoc;
import com.cms.model.biz.supply.SuBillOrderDtl;
import com.cms.model.biz.supply.SuBillSendDoc;
import com.cms.model.biz.supply.SuBillSendDtl;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.biz.supply.ISuBillOrderService;
import com.cms.service.biz.supply.ISuBillSendService;
import com.cms.service.biz.supply.ISuSupplyService;

import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 供应商手工录入送货单
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SupplySendBillManualController extends BaseController {
	@Autowired
	private ISuBillSendService suBillSendService;
	@Autowired
	private ISuBillOrderService suBillOrderService;
	
	@RequestMapping("supplySendBillManual")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/bill/supplySendBillManual";
	}

	@ResponseBody
	@RequestMapping("supplySendBillManual/gridDataDoc")
	public GridDataModel<SuBillSendDoc> gridDataDoc(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String orgid=param("orgid", "");
		String credate1= param("credate1", "");
		String credate2= param("credate2", "");
		String supplyid= suUser.getSupplyid();
		GridDataModel<SuBillSendDoc> gridDataModel=suBillSendService
				.getGridDataDocByGroupidSupplyidCredateStatusIntype(suUser.getGroupid(), supplyid, credate1, credate2, "","3");//只看手工录入单据
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("supplySendBillManual/gridDataDtl")
	public GridDataModel<SuBillSendDtl> gridDataDtl(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String docid= param("docid", "");
		GridDataModel<SuBillSendDtl> gridDataModel=suBillSendService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplySendBillManual/setStatus")
	public JSONObject setStatus(HttpSession session) {
		String status= param("status", "");
		String docid= param("docid", "");
		HashMap<String, Object> result = suBillSendService.setStatus(docid, status);
		return JSONObject.fromObject(result);
	}
	
	@ResponseBody
	@RequestMapping("supplySendBillManual/createSendbillByOrderbill")
	public JSONObject gridSave() {
		String OrgbillDocid=param("docid","");//订单的docid
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suBillSendService.createSendbillByOrderbill(OrgbillDocid);
		return JSONObject.fromObject(result);	
	}
	
	@ResponseBody
	@RequestMapping("supplySendBillManual/gridDataOrderBillDoc")
	public GridDataModel<SuBillOrderDoc> gridDataOrderBillDoc(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String credate1= param("credate11", "");
		String credate2= param("credate22", "");
		String supplyid= suUser.getSupplyid();
		GridDataModel<SuBillOrderDoc> gridDataModel=suBillOrderService
				.getGridDataDocBySupplyidCredateStatus(supplyid, credate1, credate2, "1");//只能选择-已审核订单
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplySendBillManual/gridDataOrderBillDtl")
	public GridDataModel<SuBillOrderDtl> gridDataOrderBillDtl(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String docid= param("docid", "");
		GridDataModel<SuBillOrderDtl> gridDataModel=suBillOrderService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	
	@ResponseBody
	@RequestMapping("supplySendBillManual/saveDtl")
	public JSONObject saveDtl(final @RequestParam("jsonDtl") String jsonDtl) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suBillSendService.saveSuBillSendDtl(jsonDtl);
		return JSONObject.fromObject(result);	
	}
}
