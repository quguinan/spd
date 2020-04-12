package com.cms.controller.biz.goods.supply.supply;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.model.biz.supply.SuSupplyOrg;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.biz.supply.ISuSupplyOrgService;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms/biz/supply")
public class SuSupplyOrgController extends BaseController {

	@Autowired
	ISuSupplyOrgService suSupplyOrgService;
	
	@ResponseBody
	@RequestMapping("supplyOrg/findSupplyOrgBySupplyid")
	public JSONObject findSupplyOrgBySupplyid(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		List<SuSupplyOrg> list=suSupplyOrgService.findBySupplyid(suUser.getSupplyid());

		result.put("orgidCount", list.size());
		result.put("orgs", JSONArray.fromObject(list));
		result.put("success", true);
		
		return JSONObject.fromObject(result);	
	}
	
}
