package com.cms.controller.biz.goods.supply.goods;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.model.biz.supply.SuGoodsSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuGoodsOrgService;
import com.cms.service.biz.supply.ISuGoodsSupplyService;

import my.web.BaseController;
import my.web.IUser;
import net.sf.json.JSONObject;
/**
 * 
 * @author 作者：qgn
 *
 * @version 创建时间：
 * 
 * @descriptions 类说明：供应商耗材目录
 */
@Controller
@RequestMapping("/cms/biz")
public class SupplyGoodsController extends BaseController {

	@Autowired 
	private ISuGoodsSupplyService suGoodsSupply;
	@Autowired 
	private ISuGoodsOrgService suGoodsOrg;
	
	@RequestMapping("supply/supplyGoods")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/goods/supplyGoods";
	}
	
	
	
	@ResponseBody
	@RequestMapping("supply/supplyGoods/gridDataSupplyByNameSpell")
	public GridDataModel<SuGoodsSupply> gridDataSupplyByNameSpell(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String orgid= param("orgid", "");
		String sugoodsid= param("sugoodsid", "");
		String sugoodsname= param("sugoodsname", "");
		String suspell= param("suspell", "");
		GridDataModel<SuGoodsSupply> gridDataModel=suGoodsSupply.gridDataSupplyByNameSpell(orgid,suUser.getSupplyid(),sugoodsid,sugoodsname, suspell);
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("supply/supplyGoods/gridDataOrgByClassidNameSpell")
	public GridDataModel<SuGoodsOrg> gridDataOrgByClassidNameSpell(HttpSession session) {
		SuUser user=(SuUser) session.getAttribute("suUser");
		String orgid=param("orgid", "");
		String classcode= param("classcode", "");
		String goodsid= param("goodsid", "");
		String goodsname= param("goodsname", "");
		String spell= param("spell", "");
		GridDataModel<SuGoodsOrg> gridDataModel=suGoodsOrg.gridDataOrgByClassidNameIdSpell(orgid, classcode, goodsname, goodsid, spell);
		return gridDataModel;
	}

	
	@ResponseBody
	@RequestMapping("supply/supplyGoods/undoMap")
	public JSONObject undoMap() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String orgid= param("orgid", "");
		String supplyid= param("supplyid", "");
		String sugoodsid= param("sugoodsid", "");
		int rs=suGoodsSupply.unMapData(orgid, supplyid, sugoodsid);
		if (rs>0) {
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", true);
		}
		return JSONObject.fromObject(result);
	}
	@ResponseBody
	@RequestMapping("supply/supplyGoods/doMap")
	public JSONObject doMap() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String orgid= param("orgid", "");
		String supplyid= param("supplyid", "");
		String sugoodsid= param("sugoodsid", "");
		String goodsid= param("goodsid", "");
		Integer checkRs=suGoodsSupply.checkBeforeMapData(orgid, supplyid, sugoodsid, goodsid);
		if(checkRs>0){
			result.put("msg", "操作失败,重复对照！已被对照[供应商货品ID:"+checkRs+"],如要继续,需先取消对照!");
			result.put("success", false);
		}else{
			int rs=suGoodsSupply.mapData(orgid, supplyid, sugoodsid, goodsid);
			if (rs>0) {
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败！");
				result.put("success", false);
			}
		}

		return JSONObject.fromObject(result);
	}
	
}
