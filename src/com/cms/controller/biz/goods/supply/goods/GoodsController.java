package com.cms.controller.biz.goods.supply.goods;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.BIGoodsV;
import com.cms.service.biz.supply.IGoodsService;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 平台数据控制器 add by wlm 2020年3月19日
 * 
 * @author xx
 *
 */

@Controller
@RequestMapping("/cms/biz/supply")
public class GoodsController extends BaseController {
	@Autowired
	private IGoodsService goodsservice;

	@RequestMapping("/goods/index")
	public String goodsindex() {
		return "spd/processing/platform/goods/goodslist";
	}

	@RequestMapping("/goods/list")
	@ResponseBody
	public GridDataModel<BIGoodsV> goodslist(HttpSession session) {
		String spell = param("spell", "");
		String goodsname = param("goodsname", "");
		String goodsid = param("goodsid", "");
		String fenlei = param("fenlei", "");
		if (fenlei.equals("全部")) {
			fenlei = "";
		}

		GridDataModel<BIGoodsV> model = goodsservice.getGoods(spell, goodsname, fenlei);
		return model;
	}

}
