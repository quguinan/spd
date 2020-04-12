package com.cms.controller.biz.goods.supply.supply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuCategoryDoc;
import com.cms.model.biz.supply.SuCategoryDtl;
import com.cms.model.biz.supply.SuCategoryDtlLicenseV;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuCategoryService;

import my.web.BaseController;
/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SuCategoryController extends BaseController {

	@Autowired
	private ISuCategoryService suCategoryService;
	
	@RequestMapping("gspCategory")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/supply/suCategory";
	}
	
	@ResponseBody 
	@RequestMapping("gspCategory/gridDataDoc")
	public GridDataModel<SuCategoryDoc> gridDataDoc(HttpSession session) {
//		SysUser user=(SysUser) session.getAttribute("user");
		GridDataModel<SuCategoryDoc> gridDataModel=suCategoryService.getGridDataDoc();
		return gridDataModel;
	}
	
	
	@ResponseBody
	@RequestMapping("gspCategory/gridDataDtl")
	public GridDataModel<SuCategoryDtl> gridDataDtl(HttpSession session) {
		String docid=param("docid","");
		GridDataModel<SuCategoryDtl> gridDataModel=suCategoryService.getGridDataDtl(docid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("gspCategory/gridDataDtlBySupplyid")
	public GridDataModel<SuCategoryDtlLicenseV> gridDataDtlBySupplyid(HttpSession session) {
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		GridDataModel<SuCategoryDtlLicenseV> gridDataModel=suCategoryService.getGridDataDtlByOrgidSupplyid(suUser.getSupplyid());
		return gridDataModel;
	}
}
