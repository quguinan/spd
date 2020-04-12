package com.cms.controller.biz.goods.supply.goods;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComGoodsClassV;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuGoodsOrgService;
import com.cms.util.biz.model.MsgModel;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author 作者：qgn
 *
 * @version 创建时间：
 * 
 * @descriptions 类说明：平台耗材目录
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class OrgGoodsController extends BaseController {

	@Autowired
	private ISuGoodsOrgService suGoodsOrg;

	@RequestMapping("orgGoods")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("suUser", session.getAttribute("suUser"));
		return "cms/biz/goods/supplier/goods/orgGoods";
	}

	@RequestMapping("orgGoodsList")
	public String list1(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("suUser", session.getAttribute("suUser"));
		return "spd/supply/platform/goods/list";
	}

	/**
	 * 同步前检查SuUser是否设置对应机构
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orgGoods/importData")
	public JSONObject importData(Model model, HttpSession session) {
		SuUser suUser = (SuUser) session.getAttribute("suUser");
		String orgid = param("orgid", "");
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (suUser == null) {
			result.put("msg", "当前用户没有设置对应机构,无法进行同步操作!");
		} else {
			MsgModel msg = suGoodsOrg.importData(orgid);
			result.put("msg", msg.getMsg() + msg.getContent());
		}
		result.put("success", true);
		return JSONObject.fromObject(result);

	}

	@ResponseBody
	@RequestMapping("orgGoods/gridDataOrgByClassidNameGoodsidSpell")
	public GridDataModel<SuGoodsOrg> gridDataOrgByClassidNameGoodsidSpell(HttpSession session) {
//		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String orgid = param("orgid", "");
		String classcode = param("classcode", "");
		String goodsname = param("goodsname", "");
		String goodsid = param("goodsid", "");
		String spell = param("spell", "");
		GridDataModel<SuGoodsOrg> gridDataModel = suGoodsOrg.gridDataOrgByClassidNameIdSpell(orgid, classcode,
				goodsname, goodsid, spell);
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("orgGoods/getClasslist")
	public JSONArray getClasslist(HttpSession session) {
		SuUser suUser = (SuUser) session.getAttribute("suUser");
		String orgid = param("orgid", "");
		JSONArray ja = new JSONArray();
		List<SuComGoodsClassV> list = suGoodsOrg.getSuComGoodsClass(orgid);
		for (SuComGoodsClassV suComGoodsClassV : list) {
			ja.add(JSONObject.fromObject(suComGoodsClassV));
		}
		return ja;
	}

}
