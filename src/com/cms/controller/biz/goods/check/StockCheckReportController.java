package com.cms.controller.biz.goods.check;

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
import com.cms.form.GoodsBillInDocForm;
import com.cms.form.DictStoreForm;
import com.cms.model.biz.goods.GoodsBillIn;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.stockCheck.StockCheckServiceImpl;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月21日 下午10:13:31 
 * 
 *@descriptions 类说明：盘点统计
 */
@Controller
@RequestMapping("/cms/goods/check")
public class StockCheckReportController extends BaseController{
	
	@RequestMapping("stock_check_report")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/check/stockCheckReport";
	}
	
	
}
