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
import com.cms.model.biz.supply.SuUser;
import com.cms.service.biz.supply.ISuBillOrderService;
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
public class SupplyOrderBillController extends BaseController {
	@Autowired
	private ISuBillOrderService suBillOrderService;
	
	@RequestMapping("supplyOrderBill")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/bill/supplyOrderBill";
	}
	
	
	@ResponseBody
	@RequestMapping("supplyOrderBill/gridDataDoc")
	public GridDataModel<SuBillOrderDoc> gridDataDoc(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String credate1= param("credate1", "");
		String credate2= param("credate2", "");
		String supplyid= suUser.getSupplyid();
		GridDataModel<SuBillOrderDoc> gridDataModel1=suBillOrderService
				.getGridDataDocBySupplyidCredateStatus( supplyid, credate1,credate2, "1");//已审核
		GridDataModel<SuBillOrderDoc> gridDataModel2=suBillOrderService
				.getGridDataDocBySupplyidCredateStatus( supplyid, credate1,credate2, "2");//已传送
		GridDataModel<SuBillOrderDoc> gridDataModel=new GridDataModel<SuBillOrderDoc>();
		
		gridDataModel.setTotal(gridDataModel1.getTotal()+gridDataModel2.getTotal());
		List<SuBillOrderDoc> list=gridDataModel1.getRows();
		list.addAll(gridDataModel2.getRows());
		gridDataModel.setRows(list);
		
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplyOrderBill/gridDataDtl")
	public GridDataModel<SuBillOrderDtl> gridDataDtl(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String docid= param("docid", "");
		GridDataModel<SuBillOrderDtl> gridDataModel=suBillOrderService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplyOrderBill/setStatus")
	public JSONObject setStatus(HttpSession session) {
		String status= param("status", "");
		String docid= param("docid", "");
		HashMap<String, Object> result = suBillOrderService.setStatus(docid, status);
		return JSONObject.fromObject(result);
	}
	
	
}
