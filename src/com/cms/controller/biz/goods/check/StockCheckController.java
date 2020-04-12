package com.cms.controller.biz.goods.check;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.form.DictStoreForm;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.stockCheck.StockCheckService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月21日 下午10:13:31 
 * 
 *@descriptions 类说明：生成盘点
 */
@Controller
@RequestMapping("/cms/goods/check")
public class StockCheckController extends BaseController{
	@Autowired
	private StockCheckService stockCheckService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public DictStoreForm getForm(){
		return new DictStoreForm();
	}
	
	@RequestMapping("stock_check")
	public String list(@RequestParam String menuid, Model m,HttpSession session) {
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/check/stock_check";
	}
	
	/**
	 * 开始盘点
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/startCheck")
	public JSONObject startCheck(@RequestParam String storeid,Model m,HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		/*
		 * 先判断是否存在未完成的盘点记录,如果存在,直接调用盘点记录
		 */
		GoodsStockCheck goodsStockCheck=stockCheckService.getUnfinishedDocid(storeid);
		if(goodsStockCheck!=null){
			String docid=goodsStockCheck.getDocid();
			String docno=goodsStockCheck.getDocno();
			String status=goodsStockCheck.getStatus();
			result.put("docid", docid);
			result.put("docno", docno);
			result.put("status", status);
			result.put("msg", "调取盘点单成功！");
			result.put("success", true);
			return JSONObject.fromObject(result);
		}
		/*
		 * 如果不存在未完成的盘点记录,开始生成盘点单,并且调用盘点记录
		 */
		if(stockCheckService.checkStart(storeid)<0){
			result.put("msg", "操作失败！");
			result.put("success", false);
		}else{
			GoodsStockCheck goodsStockCheckUnfinished=stockCheckService.getUnfinishedDocid(storeid);
			String docid_new=goodsStockCheckUnfinished.getDocid();
			String docno=goodsStockCheckUnfinished.getDocno();
			String status=goodsStockCheckUnfinished.getStatus();
			result.put("docid", docid_new);
			result.put("docno", docno);
			result.put("status", status);
			result.put("msg", "生成新盘点单成功！");
			result.put("success", true);
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 载入当前盘点的sum数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/gridDataStockCheckSum")
	public GridDataModel<GoodsStockCheckSum> gridDataStockCheckSum(@RequestParam String docid) {
		return stockCheckService.gridDataSumByDocid(docid);
	}
	/**
	 * 载入当前盘点的dtl数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/gridDataStockCheckDtl")
	public GridDataModel<GoodsStockCheckDtl> gridDataStockCheckDtl(
			@RequestParam String goodsid,
			@RequestParam String goodsdtlid,
			@RequestParam String docid,
			HttpSession session) {
		return stockCheckService.gridDataDtlByDocid(goodsid,goodsdtlid,docid);
	}
	
	
	/**
	 * 保存盘点
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/saveCheck")
	public JSONObject saveCheck(@RequestParam String dataSum) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel modelSum = new GridSaveModel();
			modelSum = JSON.parseObject(dataSum, GridSaveModel.class);
		
		if(stockCheckService.checkSave(modelSum)>0){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", true);
		}
		
		return JSONObject.fromObject(result);
	}
	/**
	 * 取消盘点
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/cancelCheck")
	public JSONObject cancelCheck(@RequestParam String docid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int rtn=stockCheckService.cancelByDocid(docid);//1:成功  , 0:条件不满足 , -1:异常失败 
		if(rtn==1){
			result.put("success", true);
			result.put("msg", "操作成功！");
		}else{
			result.put("success", false);
			result.put("msg", "操作失败！");
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 审核盘点
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/reviewCheck")
	public JSONObject reviewCheck(@RequestParam String docid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int rtn=stockCheckService.reviewByDocid(docid);//1:成功  , 0:条件不满足 , -1:异常失败 
		if(rtn==1){
			result.put("status", "C");
			result.put("success", true);
			result.put("msg", "操作成功！");
		}else{
			result.put("success", false);
			result.put("msg", "操作失败！");
		}
		return JSONObject.fromObject(result);
	}
}
