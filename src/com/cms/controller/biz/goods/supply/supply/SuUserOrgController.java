package com.cms.controller.biz.goods.supply.supply;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.biz.supply.SuUserOrgV;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuLicenseTypeService;
import com.cms.service.biz.supply.ISuUserService;

import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SuUserOrgController extends BaseController {

	@Autowired
	private ISuUserService suUserService;
	
	@RequestMapping("userOrg")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/supply/suUserOrg";
	}
	
	@ResponseBody
	@RequestMapping("userOrg/gridDataUser")
	public GridDataModel<SuUserOrgV> gridDataUser(HttpSession session) {
		GridDataModel<SuUserOrgV> gridDataModel=suUserService.gridDataSuUserOrg();
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("userOrg/saveUser")
	public JSONObject gridSave(HttpSession session) {
		String json = param("json", "");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suUserService.save(json);
		return JSONObject.fromObject(result);	
		
	}
}
