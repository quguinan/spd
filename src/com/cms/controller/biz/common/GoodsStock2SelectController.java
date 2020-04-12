package com.cms.controller.biz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictGoods;
import com.cms.model.biz.dict.DictGoodsPkg;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStock2;
import com.cms.model.biz.goods.GoodsStock2SumV;
import com.cms.service.biz.dict.impl.DictGoodsPkgService;
import com.cms.service.biz.goods.stock2.Stock2ServiceImpl;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：二级库通用选择
 */
@Controller
@RequestMapping("/cms/common")
public class GoodsStock2SelectController extends BaseController{
	@Autowired
	private Stock2ServiceImpl goodsStock2Service;
	
	
	@RequestMapping("goodsStock2SelectDtl")
	public String goodsStock2SelectDtl(Model m) {
		String deptid=param("deptid","");
		m.addAttribute("deptid", deptid);
		return "cms/biz/common/goodsStock2SelectDtl";
	}
	
	@RequestMapping("goodsStock2SelectSum")
	public String goodsSelect2Sum(Model m) {
		String deptid=param("deptid","");
		m.addAttribute("deptid", deptid);
		return "cms/biz/common/goodsStock2SelectSum";
	}
	
	@ResponseBody
	@RequestMapping("goodsStock2SelectDtl/datagrid")
	public GridDataModel<GoodsStock2> datagridDtl() {
		String deptid=param("deptid","");
		String goodsname=param("goodsnameselect","");
		String goodsspell=param("goodsspellselect","");
		GridDataModel<GoodsStock2> gridDataModel=goodsStock2Service.getGridDataDtl(deptid,goodsname,goodsspell);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("goodsStock2SelectSum/datagrid")
	public GridDataModel<GoodsStock2SumV> datagridSum() {
		String deptid=param("deptid","");
		String goodsname=param("goodsnameselect","");
		String goodsspell=param("goodsspellselect","");
		GridDataModel<GoodsStock2SumV> gridDataModel=goodsStock2Service.getGridDataSum(deptid,goodsname,goodsspell);
		return gridDataModel;
	}
	
	
	
}
