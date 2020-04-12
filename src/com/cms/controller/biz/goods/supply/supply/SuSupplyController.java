package com.cms.controller.biz.goods.supply.supply;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.form.DictSupplyForm;
import com.cms.model.biz.dict.DictSupply;
import com.cms.model.biz.supply.SuCategoryDoc;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.dict.IDictOrgService;
import com.cms.service.biz.dict.impl.DictSupplyServiceImpl;
import com.cms.service.biz.supply.ISuCategoryService;
import com.cms.service.biz.supply.ISuSupplyService;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author 作者：wanglimin
 *
 * @version 创建时间：2019年4月23日 上午11:07:39
 * 
 * @descriptions 类说明：平台供应商维护
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SuSupplyController extends BaseController {

	@Autowired
	private ISuSupplyService suSupplyService;

	@Autowired
	private ISuCategoryService suCategoryService;

	@RequestMapping("supply")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/supply/supply";
	}

	@ResponseBody
	@RequestMapping("supply/gridDataSupply")
	public GridDataModel<SuSupply> gridDataSupply(HttpSession session) {
//		SuUser suUser=(SuUser) session.getAttribute("suUser");
		SysUser user = (SysUser) session.getAttribute("user");
		String orgid = param("orgid", "");
		GridDataModel<SuSupply> gridDataModel = null;
		if (!"-1".equals(user.getUserid())) {
			gridDataModel = suSupplyService.getGridDataByOrgid(orgid);
		} else {
			gridDataModel = suSupplyService.getGridData();
		}

		return gridDataModel;
	}

	/**
	 * 选择接口 供应商信息
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("supply/selectComSupply")
	public JSONObject selectComSupply(HttpSession session) {
//		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String supplyid = param("supplyid", "");
		HashMap<String, Object> result = new HashMap<String, Object>();

		SuComSupplyV suComSupplyV = SuComSupplyV.INSTANCE.queryOne(" supplyid=?", supplyid);
		if (suSupplyService.selectComSupply(suComSupplyV) > 0) {
			result.put("msg", "成功!");
		} else {
			result.put("msg", "失败!");
		}
		result.put("success", true);
		return JSONObject.fromObject(result);
	}

	/**
	 * 选择平台 供应商信息
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("supply/selectSupply")
	public JSONObject selectSupply(HttpSession session) {
//		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String orgid = param("orgid", "");
		String supplyid = param("supplyid", "");
		HashMap<String, Object> result = new HashMap<String, Object>();

		if (suSupplyService.selectSupply(orgid, supplyid) > 0) {
			result.put("msg", "成功!");
		} else {
			result.put("msg", "失败!");
		}
		result.put("success", true);
		return JSONObject.fromObject(result);
	}

	@ResponseBody
	@RequestMapping("supply/findAlljson")
	public JSONArray findAll(HttpSession session) {
//		SysUser user=(SysUser) session.getAttribute("user");
		JSONArray ja = JSONArray.fromObject(suSupplyService.findAll());
		return ja;
	}

	@ResponseBody
	@RequestMapping("supply/findAllCategroyjson")
	public JSONArray findAllCategroyjson(HttpSession session) {
//		SysUser user=(SysUser) session.getAttribute("user");
		JSONArray ja = new JSONArray();
		List<SuCategoryDoc> gridDataModel = suCategoryService.findAll();
		for (SuCategoryDoc suOrg : gridDataModel) {
			ja.add(JSONObject.fromObject(suOrg));
		}
		return ja;
	}

	@ResponseBody
	@RequestMapping("supply/findByCategroyjson")
	public JSONArray findByCategroyjson(HttpSession session) {
//		SysUser user=(SysUser) session.getAttribute("user");
		JSONArray ja = new JSONArray();
		List<SuCategoryDoc> gridDataModel = suCategoryService.findByDtlInfo();
		for (SuCategoryDoc suOrg : gridDataModel) {
			ja.add(JSONObject.fromObject(suOrg));
		}
		return ja;
	}

	@ResponseBody
	@RequestMapping("supply/save")
	public JSONObject gridSave(HttpSession session) {
		String json = param("json", "");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = suSupplyService.save(json);
		return JSONObject.fromObject(result);

	}
}
