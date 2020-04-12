package com.cms.controller.biz.goods.supply.bill;


import java.util.HashMap;
import java.util.List;

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

import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class OrgSendBillController extends BaseController {
	
	@Autowired
	private ISuBillSendService suBillSendService;
	
	@RequestMapping("orgSendBill")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/bill/orgSendBill";
	}
	
	@ResponseBody
	@RequestMapping("orgSendBill/gridDataDoc")
	public GridDataModel<SuBillSendDoc> gridDataDoc(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String credate1= param("credate1", "");
		String credate2= param("credate2", "");
		String supplyid= param("supplyid", "");
		GridDataModel<SuBillSendDoc> gridDataModel1=suBillSendService
				.getGridDataDocByGroupidSupplyidCredateStatusIntype(suUser.getGroupid(), supplyid, credate1,credate2, "1","");//已审核
		GridDataModel<SuBillSendDoc> gridDataModel2=suBillSendService
				.getGridDataDocByGroupidSupplyidCredateStatusIntype(suUser.getGroupid(), supplyid, credate1,credate2, "2","");//已传输
		GridDataModel<SuBillSendDoc> gridDataModel=new GridDataModel<SuBillSendDoc>();
		
		gridDataModel.setTotal(gridDataModel1.getTotal()+gridDataModel2.getTotal());
		List<SuBillSendDoc> list=gridDataModel1.getRows();
		list.addAll(gridDataModel2.getRows());
		gridDataModel.setRows(list);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("orgSendBill/gridDataDtl")
	public GridDataModel<SuBillSendDtl> gridDataDtl(HttpSession session) {
		String docid= param("docid", "");
		GridDataModel<SuBillSendDtl> gridDataModel=suBillSendService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("orgSendBill/saveDataDtl")
	public JSONObject saveDataDtl(HttpSession session) {
		String json=param("json", "");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suBillSendService.saveSuBillSendDtl(json);
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
	
	@ResponseBody
	@RequestMapping("orgSendBill/setStatus")
	public JSONObject setStatus(HttpSession session) {
		String status= param("status", "");
		String docid= param("docid", "");
		HashMap<String, Object> result = suBillSendService.setStatus(docid, status);
		return JSONObject.fromObject(result);
	}
}
