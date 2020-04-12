package com.cms.controller.biz.goods.stock2;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.form.GoodsBillApplyDocForm;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.biz.goods.GoodsStock2;
import com.cms.model.biz.goods.GoodsStock2Io;
import com.cms.model.biz.goods.GoodsStock2SumV;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.BillStock2ApplyService;
import com.cms.service.biz.goods.stock2.Stock2Service;
import com.cms.service.biz.goods.stock2.Stock2ServiceImpl;
import com.cms.util.biz.SessionHelpUtils;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月28日 下午2:52:25 
 * 
 *@descriptions 类说明：科室查询
 */
@Controller
@RequestMapping("/cms/goods/stock2")
public class Stock2QueryController  extends BaseController{
	@Autowired
	private Stock2Service goodsStock2Service;
	
	@RequestMapping("stock2_query")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock2/stock2_query";
	}
	
	@ResponseBody
	@RequestMapping("stock2_query/gridData1")
	public GridDataModel<GoodsStock2SumV> gridData1() {
		//String docid = param("docid", "");
		GridDataModel<GoodsStock2SumV> gridDataModel=goodsStock2Service.getGridDataSum(); 
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("stock2_query/gridData2")
	public GridDataModel<GoodsStock2> gridData2() {
		//String docid = param("docid", "");
		GridDataModel<GoodsStock2> gridDataModel=goodsStock2Service.getGridDataDtl();
		return gridDataModel;
	}
	
	/**
	 * 根据货品ID 查找IO流水
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_query/gridDataIOByGoodsid")
	public GridDataModel<GoodsStock2Io> gridData11ByGoodsid() {
		String goodsid = param("goodsid", "");
		String goodsdtlid = param("goodsdtlid", "");
		GridDataModel<GoodsStock2Io> gridDataModel=goodsStock2Service.getGridDataStockIOByGoodsid(goodsid, goodsdtlid); 
		return gridDataModel;
	}
	/**
	 * 根据货品批次号 查找IO流水
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_query/gridDataIOBylotid")
	public GridDataModel<GoodsStock2Io> gridData22Bylotid() {
		String lotid = param("lotid", "");
		GridDataModel<GoodsStock2Io> gridDataModel=goodsStock2Service.getGridDataStockIOByLotid(lotid); 
		return gridDataModel;
	}
}