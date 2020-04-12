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
import com.cms.model.biz.supply.SuOrgGroupDoc;
import com.cms.model.biz.supply.SuOrgGroupDtl;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuLicenseTypeService;
import com.cms.service.biz.supply.ISuOrgService;

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
public class SuOrgGroupController extends BaseController {
	@Autowired
	private ISuOrgService suOrgService;
	
	@RequestMapping("orgGroup")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/org/orgGroup";
	}
	
	@ResponseBody
	@RequestMapping("orgGroup/findAlljson")
	public JSONArray findAlljson(HttpSession session) {
		return suOrgService.findAlljson();
	}
	
	@ResponseBody
	@RequestMapping("orgGroup/gridDataOrgGroupDoc")
	public GridDataModel<SuOrgGroupDoc> gridDataOrgGroupDoc(HttpSession session) {
		SysUser user=(SysUser) session.getAttribute("user");
		GridDataModel<SuOrgGroupDoc> gridDataModel=suOrgService.gridDataOrgGroupDoc();
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("orgGroup/gridDataOrgGroupDtl")
	public GridDataModel<SuOrgGroupDtl> gridDataOrgGroupDtl(HttpSession session) {
		SysUser user=(SysUser) session.getAttribute("user");
		String groupid=param("groupid","");
		GridDataModel<SuOrgGroupDtl> gridDataModel=suOrgService.gridDataOrgGroupDtl(groupid);
		return gridDataModel;
	}
	
}
