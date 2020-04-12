package com.cms.controller.biz.goods.supply.org;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuSupplyOrg;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuLicenseTypeService;
import com.cms.service.biz.supply.ISuOrgService;
import com.cms.service.biz.supply.ISuSupplyOrgService;

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
public class SuOrgController extends BaseController {

	@Autowired
	private ISuOrgService suOrgService;
	@Autowired
	private ISuSupplyOrgService suSupplyOrgService;

	@RequestMapping("org")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/org/org";
	}

	@ResponseBody
	@RequestMapping("org/gridDataOrg")
	public GridDataModel<SuOrg> gridData(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		GridDataModel<SuOrg> gridDataModel = null;
		if ("-1".equals(user.getUserid()) || "suadmin".equals(user.getUsername())) {
			gridDataModel = suOrgService.gridDataOrg();
		} else {
			SuUser suUser = (SuUser) session.getAttribute("suUser");
			gridDataModel = suOrgService.gridDataOrg(suUser.getGroupid());
		}
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("org/gridDataSupplyOrg")
	public GridDataModel<SuSupplyOrg> gridDataSupplyOrg(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		String orgid = param("orgid", "");
		GridDataModel<SuSupplyOrg> gridDataModel = suSupplyOrgService.getGridDataByOrgid(orgid);
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("org/findAlljson")
	public JSONArray findAll(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		JSONArray ja = new JSONArray();
		List<SuOrg> gridDataModel = suOrgService.findAll();
		for (SuOrg suOrg : gridDataModel) {
			ja.add(JSONObject.fromObject(suOrg));
		}
		return ja;
	}

	@RequestMapping("org/orderlist")
	public String index() {
		return "/spd/supply/platform/order/orgOrderBill";
	}
}
