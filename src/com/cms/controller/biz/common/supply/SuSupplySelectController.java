package com.cms.controller.biz.common.supply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictSupply;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.biz.dict.impl.DictSupplyServiceImpl;
import com.cms.service.biz.supply.ISuComSupplyService;
import com.cms.service.biz.supply.ISuSupplyService;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：选择平台上供应商数据
 */
@Controller
@RequestMapping("/cms/common")
public class SuSupplySelectController extends BaseController{
	
	@Autowired
	private ISuSupplyService suSupplyService;
	
	@RequestMapping("suSupplySelect")
	public String supplySelect(Model m) {
		return "cms/biz/common/suSupplySelect";
	}
	@ResponseBody
	@RequestMapping("suSupplySelect/datagrid")
	public GridDataModel<SuSupply> datagrid(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		String supplyname=param("supplynameselect","");
		String supplyspell=param("supplyspellselect","");
		GridDataModel<SuSupply> gridDataModel=suSupplyService.getGridDataBySupplynameSpell(supplyname, supplyspell);
		return gridDataModel;
	}
}
