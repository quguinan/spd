package com.cms.controller.biz.goods.supply.bill;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SupplySendBillController extends BaseController {
	@Autowired
	private ISuBillSendService suBillSendService;
	
	@RequestMapping("supplySendBill")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/bill/supplySendBill";
	}
	
	@ResponseBody
	@RequestMapping("supplySendBill/gridDataDoc")
	public GridDataModel<SuBillSendDoc> gridDataDoc(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String orgid=param("orgid", "");
		String credate1= param("credate1", "");
		String credate2= param("credate2", "");
		String supplyid= suUser.getSupplyid();
		GridDataModel<SuBillSendDoc> gridDataModel=suBillSendService
				.getGridDataDocByGroupidSupplyidCredateStatusIntype(suUser.getGroupid(), supplyid, credate1, credate2, "","1");//只看上传单据
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("supplySendBill/gridDataDtl")
	public GridDataModel<SuBillSendDtl> gridDataDtl(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String docid= param("docid", "");
		GridDataModel<SuBillSendDtl> gridDataModel=suBillSendService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplySendBill/setStatus")
	public JSONObject setStatus(HttpSession session) {
		String status= param("status", "");
		String docid= param("docid", "");
		HashMap<String, Object> result = suBillSendService.setStatus(docid, status);
		return JSONObject.fromObject(result);
	}
}
