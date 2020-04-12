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
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuBillOrderService;
import com.cms.service.biz.supply.ISuSupplyService;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class OrgOrderBillController extends BaseController {
	@Autowired
	private ISuBillOrderService suBillOrderService;
	@Autowired
	private ISuSupplyService suSupplyService;

	@RequestMapping("orgOrderBill")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/bill/orgOrderBill";
	}

	@ResponseBody
	@RequestMapping("orgOrderBill/gridDataDoc")
	public GridDataModel<SuBillOrderDoc> gridDataDoc(HttpSession session) {
		SuUser suUser = (SuUser) session.getAttribute("suUser");
		String credate1 = param("credate1", "");
		String credate2 = param("credate2", "");
		String supplyid = param("supplyid", "");
		GridDataModel<SuBillOrderDoc> gridDataModel = suBillOrderService
				.getGridDataDocByGroupidSupplyidCredateStatus(suUser.getGroupid(), supplyid, credate1, credate2, "");
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("orgOrderBill/gridDataDtl")
	public GridDataModel<SuBillOrderDtl> gridDataDtl(HttpSession session) {
		SuUser suUser = (SuUser) session.getAttribute("suUser");
		String docid = param("docid", "");
		GridDataModel<SuBillOrderDtl> gridDataModel = suBillOrderService.getGridDataDtl(docid);
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("orgOrderBill/setStatus")
	public JSONObject setStatus(HttpSession session) {
		String status = param("status", "");
		String docid = param("docid", "");
		HashMap<String, Object> result = suBillOrderService.setStatus(docid, status);
		return JSONObject.fromObject(result);
	}

	@ResponseBody
	@RequestMapping("orgOrderBill/getSupplyByOrgid")
	public JSONArray getSupplyByOrgid(HttpSession session) {
		SuUser suUser = (SuUser) session.getAttribute("suUser");
		List<SuSupply> list = suSupplyService.findByGroupid(suUser.getGroupid());
		JSONArray ja = JSONArray.fromObject(list);
		return ja;
	}

	@RequestMapping("orgOrderBill/getOrderDoc")
	@ResponseBody
	public GridDataModel<SuBillOrderDoc> getOrderDoc(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		return suBillOrderService.findByOrgidSupplyid(user.getSupplyid());
	}
}
