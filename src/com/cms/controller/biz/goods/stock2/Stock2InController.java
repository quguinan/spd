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
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.BillStock2ApplyService;
import com.cms.util.biz.SessionHelpUtils;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月28日 下午2:52:25 
 * 
 *@descriptions 类说明：科室入库
 */
@Controller
@RequestMapping("/cms/goods/stock2")
public class Stock2InController  extends BaseController{
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
	
	@RequestMapping("stock2_in")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock2/stock2_in";
	}
	/**
	 * 获取Dtl的datagrid 通过docid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_in/gridDataDtl")
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
	@RequestMapping("stock2_in/gridDataDocByid")
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
	@RequestMapping("stock2_in/gridDataDocBycredate")
	public GridDataModel<GoodsBillApply> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillApply> gridDataModel=goodsBillStock2ApplyService.gridDataDocBycredateO(credate,"KSSQ");
		return gridDataModel;
	}
	/**
	 * 保存
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_in/instock")
	public JSONObject instock() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		int rtn=goodsBillStock2ApplyService.billInstock2(docid);
		if(rtn==1){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else if(rtn==-1){
			result.put("msg", "操作失败！");
			result.put("success", false);
		}else if(rtn==-2){
			result.put("msg", "入库数量不能小于或等于0,操作失败！");
			result.put("success", false);
		}else if(rtn==-9){
			result.put("msg", "单据状态不是待入库,不能入库,操作失败！");
			result.put("success", false);
		}
		
		return JSONObject.fromObject(result);
	}
}