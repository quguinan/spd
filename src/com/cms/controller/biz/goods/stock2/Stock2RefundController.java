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
import com.cms.form.GoodsBillInDocForm;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.biz.goods.GoodsBillIn;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStock2;
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
 *@descriptions 类说明：科室退库
 */
@Controller
@RequestMapping("/cms/goods/stock2")
public class Stock2RefundController  extends BaseController{
	
	@Autowired
	private Stock2Service goodsStock2Service;
	@Autowired
	private BillStock2ApplyService goodsBillStock2ApplyService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillApplyDocForm getForm(){
		return new GoodsBillApplyDocForm();
	}
	@RequestMapping("stock2_refund")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock2/stock2_refund";
	}
	/**
	 * 获取Dtl的datagrid 通过docid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/gridDataDtl")
	public GridDataModel<GoodsBillApplyDtl> gridDataDtl() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillApplyDtl> gridDataModel=goodsBillStock2ApplyService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	/**
	 * 获取Doc的datagrid 通过docid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/gridDataDocByid")
	public JSONObject gridDataDocByid() {
		String docid = param("docid", "");
		GoodsBillApply goodsBillApply=goodsBillStock2ApplyService.getBillGoodsInByDocid(docid);
		return JSONObject.fromObject(goodsBillApply);
	}
	/**
	 * 获取Dtl的datagrid 通过credate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/gridDataDocBycredate")
	public GridDataModel<GoodsBillApply> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillApply> gridDataModel=goodsBillStock2ApplyService.gridDataDocBycredateBeforeS(credate,"KSTK");
 		return gridDataModel;
	}
	
	/**
	 * 二级库退货选择货品 （与一级库存退货不同）
	 * 需要选择单品种库存汇总信息
	 * 返回值只包含货品库存数量 ， 没有lotid ， stockid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/getGoodsStock2Select")
	public JSONObject getGoodsSelect() {
		HashMap<String, Object> result = new HashMap<String, Object>();
//		String stockid=param("stockid","");
		String deptid=param("deptid","");
		String goodsid=param("goodsid","");
		String goodsdtlid=param("goodsdtlid","");
		
		
		GoodsStock2SumV goodsStock2SumV=goodsStock2Service.findOneSum(deptid,goodsid,goodsdtlid);
		JSONObject jo=JSONObject.fromObject(goodsStock2SumV);
		jo.put("deptid", deptid);
		jo.put("stockQty", jo.get("qty"));
		jo.put("qty", Double.parseDouble(jo.get("qty").toString())*-1);
		
		result.put("data", jo);
		result.put("msg", "操作成功！");
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 科室退库
	 * 保存
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/save")
	public JSONObject save(@ModelAttribute("form") GoodsBillApplyDocForm form) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String dataDtl=param("p1","");
		
		
		if(form.getDocid().equals("")){
			//*********新增(总单和细单)*********
			/* 总单 */
			GoodsBillApply billGoodsApply=new GoodsBillApply();
			try {
				BeanUtils.copyProperties(billGoodsApply, form);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			/* 细单 */
			GridSaveModel model = JSON.parseObject(dataDtl, GridSaveModel.class);
			/* 保存(插入) */
			if(goodsBillStock2ApplyService.addKSTK(billGoodsApply,model)>0){
				JSONObject jo=JSONObject.fromObject(goodsBillStock2ApplyService.getBillGoodsInByDocid(billGoodsApply.getDocid()));
				result.put("docData",jo);
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败！"); 
				result.put("success", false);
			}
			
		}else{
			//*********更新(总单和细单)*********
			/* 总单 */
			GoodsBillApply goodsBillApply=goodsBillStock2ApplyService.getBillGoodsInByDocid(form.getDocid());
			goodsBillApply.setStoreid(form.getStoreid());
			goodsBillApply.setDeptid(form.getDeptid());
			goodsBillApply.setMemo(form.getMemo());
			/* 细单 */
			GridSaveModel model = JSON.parseObject(dataDtl, GridSaveModel.class);
			/* 保存(更新) */
			if(goodsBillStock2ApplyService.updateKSTK(goodsBillApply, model)>0){
				JSONObject jo=JSONObject.fromObject(goodsBillStock2ApplyService.getBillGoodsInByDocid(goodsBillApply.getDocid()));
				result.put("docData",jo);
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败！");
				result.put("success", false);
			}
		}
		return JSONObject.fromObject(result);
	}
	/**
	 *  设置主单状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/setStatus")
	public JSONObject setStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");//原status
		String value=param("value","");
		
		if (goodsBillStock2ApplyService.setBillGoodsApplyDocStatus(docid, value)>0){
			JSONObject jo=JSONObject.fromObject(goodsBillStock2ApplyService.getBillGoodsInByDocid(docid));
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
	 *  退库
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_refund/refund")
	public JSONObject refund() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");
		/*
		 * 验证
		 * 1.状态
		 * 2.出库数量是否满足
		 */
		
		/*
		 * 开始二级库出库
		 */
		if(goodsBillStock2ApplyService.billOutStock2(docid)>0){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", false);
		}
		return JSONObject.fromObject(result);
	}
}



