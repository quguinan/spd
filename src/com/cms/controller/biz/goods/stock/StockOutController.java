package com.cms.controller.biz.goods.stock;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.cms.form.GoodsBillInDocForm;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.sys.SysUser;

import com.cms.service.biz.goods.BillStock2ApplyService;

import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 上午11:41:50 
 * 
 *@descriptions 类说明：科室出库单
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class StockOutController  extends BaseController{
	@Autowired
	 private BillStock2ApplyService goodsbillStock2ApplyService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillApplyDocForm getForm(){
		return new GoodsBillApplyDocForm();
	}
	
	@RequestMapping("stock_out")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_out";
	}
	
	@ResponseBody
	@RequestMapping("stock_out/gridDataDtl")
	public GridDataModel<GoodsBillApplyDtl> gridDataDtlByDocid() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillApplyDtl> gridDataModel=goodsbillStock2ApplyService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_out/gridDataDocBycredate")
	public GridDataModel<GoodsBillApply> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillApply> gridDataModel=goodsbillStock2ApplyService.gridDataDocBycredateAfterS(credate,"KSSQ");
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_out/gridDataDocByid")
	public JSONObject gridDataDocByid() {
		String docid = param("docid", "");
		GoodsBillApply goodsBillApply=goodsbillStock2ApplyService.getBillGoodsInByDocid(docid);
		return JSONObject.fromObject(goodsBillApply);
	}
	/**
	 *  设置主单状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_out/setStatus")
	public JSONObject setStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");//原status
		String value=param("value","");
		
		if (goodsbillStock2ApplyService.setBillGoodsApplyDocStatus(docid, value)>0){
			JSONObject jo=JSONObject.fromObject(goodsbillStock2ApplyService.getBillGoodsInByDocid(docid));
			result.put("docData",jo);
			result.put("msg", "操作成功!");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败!");
			result.put("success", false);
		}
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 保存明细
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_out/saveDtl")
	public JSONObject saveDtl(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String dataDtl=param("dataDtl","");
		String docid=param("docid","");
		GoodsBillApply goodsBillApply=goodsbillStock2ApplyService.getBillGoodsInByDocid(docid);
		GridSaveModel model = JSON.parseObject(dataDtl, GridSaveModel.class); 
		if(goodsbillStock2ApplyService.updateKSRK(goodsBillApply, model)>0){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", false);
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 
	 * 出库操作
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_out/outstock")
	public JSONObject outstock(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		/*
		 * 验证出库数量是否满足
		 */
		result=goodsbillStock2ApplyService.billOutStockValid(docid);
		if(!(boolean) result.get("success")){
			return JSONObject.fromObject(result);
		}
		/*
		 * 开始出库
		 */
		if(goodsbillStock2ApplyService.billOutStock(docid)>0){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", false);
		}
		return JSONObject.fromObject(result);
	}
}
