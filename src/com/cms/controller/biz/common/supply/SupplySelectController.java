package com.cms.controller.biz.common.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictSupply;
import com.cms.service.biz.dict.impl.DictSupplyServiceImpl;

import my.web.BaseController;
import net.sf.json.JSONArray;

/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月16日 上午9:55:20
 * 
 * @descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class SupplySelectController extends BaseController {
	@Autowired
	private DictSupplyServiceImpl dictSupplyService;

	@RequestMapping("supplySelect")
	public String supplySelect(Model m) {
		return "cms/biz/common/supplySelect";
	}

	@ResponseBody
	@RequestMapping("supplySelect/datagrid")
	public GridDataModel<DictSupply> datagrid() {
		String supplyname = param("supplynameselect", "");
		String supplyspell = param("supplyspellselect", "");
		String supplytype = param("supplytype", "");
		GridDataModel<DictSupply> gridDataModel = dictSupplyService.getGridData(supplyname, supplyspell);
		return gridDataModel;
	}

	@RequestMapping("supplylist")
	public String index() {
		return "spd/processing/platform/mechanism/list";
	}

	@RequestMapping("sutype")
	@ResponseBody
	public JSONArray getSuType() {
		return JSONArray.fromObject(dictSupplyService.getGridByType());
	}
}
