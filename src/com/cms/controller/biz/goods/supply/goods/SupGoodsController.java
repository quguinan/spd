package com.cms.controller.biz.goods.supply.goods;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SupGoods;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISupGoodsServcie;

import my.web.BaseController;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms/biz/supgoods")
public class SupGoodsController extends BaseController {

	@Autowired
	private ISupGoodsServcie service;

	@RequestMapping("index")
	public String index() {
		return "/spd/supply/platform/contrast/list";
	}

	@RequestMapping("importExl")
	@ResponseBody
	public JSONObject importExcel(@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,
			HttpSession session) {
		SysUser suUser = (SysUser) session.getAttribute("user");
		String supplyid = suUser.getSupplyid();
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = service.ImportGoods(file_upload, supplyid);
		return JSONObject.fromObject(result);
	}

	@RequestMapping("supgoodslist")
	@ResponseBody
	public GridDataModel<SupGoods> getGoods(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		String supplyid = user.getSupplyid();
		String goodsname = param("goodsname", "");
		String spell = param("spell", "");
		String goodsid = param("goodsid", "");
		return service.getSupGoos(goodsname, spell, supplyid, goodsid);
	}

	@RequestMapping("supgoodssave")
	@ResponseBody
	public JSONObject save(HttpSession session) {
		String json = param("json", "");
		HashMap<String, Object> result = service.save(json);
		return JSONObject.fromObject(result);

	}
}
