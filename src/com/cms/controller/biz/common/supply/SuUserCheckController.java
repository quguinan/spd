package com.cms.controller.biz.common.supply;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.biz.supply.ISuUserService;

import my.web.BaseController;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/cms/common")
public class SuUserCheckController extends BaseController{
	@Autowired
	private ISuUserService suUserService;
	/**
	 * 操作前检查SuUser是否设置对应机构
	 */
	@ResponseBody
	@RequestMapping("checkSuUserOrg")
	public JSONObject checkSuUserOrg(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suUserService.doCheckSuUserOrg();
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 操作前检查SuUser是否设置对应供应商 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkSuUserOrgSupply")
	public JSONObject checkSuUserOrgSupply(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suUserService.doCheckSuUserSupply();
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 操作前检查SuUser是否设置对应供应商和证照信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkSuUserOrgSupplyLicense")
	public JSONObject checkSuUserOrgSupplyLicense(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=suUserService.doCheckSuUserSupplyLicense();
		return JSONObject.fromObject(result);
	}
}
