package com.cms.controller.biz.goods.stock;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockIo;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.stock.StockService;
import com.cms.service.biz.goods.stock.StockServiceImpl;

import my.web.BaseController;
/**
 * 
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 上午11:41:50 
 * 
 *@descriptions 类说明：一级库库存查询
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class StockQueryController extends BaseController{

	@Autowired
	private StockService goodsStockService;
	@RequestMapping("stock_query")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_query";
	}
	
	@ResponseBody
	@RequestMapping("stock_query/gridData1")
	public GridDataModel<GoodsStockSumV> gridData1() {
		String storeid1 = param("storeid1", "");
		String goodsname1 = param("goodsname1", "");
		GridDataModel<GoodsStockSumV> gridDataModel=goodsStockService.getGridDataSum(storeid1); 
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("stock_query/gridData2")
	public GridDataModel<GoodsStock> gridData2() {
		String storeid2 = param("storeid2", "");
		String goodsname2 = param("goodsname2", "");
		GridDataModel<GoodsStock> gridDataModel=goodsStockService.getGridDataDtl(storeid2);
		return gridDataModel;
	}
	
	/**
	 * 根据货品ID 查找IO流水
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_query/gridDataIOByGoodsid")
	public GridDataModel<GoodsStockIo> gridData11ByGoodsid() {
		String storeid1 = param("storeid1", "");
		String goodsid = param("goodsid", "");
		String goodsdtlid = param("goodsdtlid", "");
		GridDataModel<GoodsStockIo> gridDataModel=goodsStockService.getGridDataStockIOByGoodsid(storeid1,goodsid, goodsdtlid); 
		return gridDataModel;
	}
	/**
	 * 根据货品批次号 查找IO流水
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_query/gridDataIOBylotid")
	public GridDataModel<GoodsStockIo> gridData22Bylotid() {
		String storeid2 = param("storeid2", "");
		String lotid = param("lotid", "");
		GridDataModel<GoodsStockIo> gridDataModel=goodsStockService.getGridDataStockIOByLotid(storeid2,lotid); 
		return gridDataModel;
	}
	
	
	
	
	
	
	
	
	
}