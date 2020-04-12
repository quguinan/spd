package com.cms.controller.biz.goods.supply.bill;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.PlanBackBak;
import com.cms.model.biz.supply.SuBillPlanDocV;
import com.cms.model.biz.supply.SuBillPlanDtlV;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuBillPlanService;

import my.web.BaseController;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms/biz/suplan")
public class SupplyPlanBillController extends BaseController {

	@Autowired
	private ISuBillPlanService service;

	@RequestMapping("suplanBill")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "spd/supply/platform/plan/supplyPlanBill";
	}

	@RequestMapping("detail")
	public String plandetail() {
		return "spd/supply/platform/plan/list";
	}

	@RequestMapping("SupplyPlanBill/gridDataDoc")
	@ResponseBody
	public GridDataModel<SuBillPlanDocV> gridDataDoc(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		GridDataModel<SuBillPlanDocV> model = service.findPlanBySupplyid(user.getSupplyid());
		return model;
	}

	@RequestMapping("SupplyPlanBill/gridDataDtl")
	@ResponseBody
	public GridDataModel<SuBillPlanDtlV> gridDataDtl(HttpSession session) {
		String plandocid = param("plandocid", "");
		SysUser user = (SysUser) session.getAttribute("user");
		GridDataModel<SuBillPlanDtlV> model = service.findPlanByPlandocid(plandocid, user.getSupplyid());
		return model;
	}

	@RequestMapping("SupplyPlanBill/dtllist")
	@ResponseBody
	public GridDataModel<PlanBackBak> dtllist(HttpSession session) {
		String goodsname = param("goodsname", "");
		SysUser user = (SysUser) session.getAttribute("user");
		GridDataModel<PlanBackBak> model = service.findAll(user.getSupplyid(), goodsname);
		return model;
	}

	@ResponseBody
	@RequestMapping("SupplyPlanBill/saveDataOrg")
	public JSONObject savePlanDataDtl(HttpSession session) {
		String json = param("json", "");
		HashMap<String, Object> result = new HashMap<String, Object>();
		SysUser user = (SysUser) session.getAttribute("user");
		result = service.savePlanDataDtl(json, user.getSupplyid());
		return JSONObject.fromObject(result);
	}

	@RequestMapping("SupplyPlanBill/importExcel")
	@ResponseBody
	public JSONObject ImpotExcel(@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,
			@RequestParam(value = "dtlrows") String dtlrows, @RequestParam(value = "sheetrows") String sheetrows,
			HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SysUser user = (SysUser) session.getAttribute("user");
		String ids = dtlrows.substring(0, dtlrows.lastIndexOf(","));
		result = service.impExcel(file_upload, ids, sheetrows, user.getSupplyid());
		return JSONObject.fromObject(result);
	}

}
