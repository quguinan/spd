package com.cms.controller.biz.goods.supply.supply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuLicenseTypeService;

import my.web.BaseController;
/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SuLicenseTypeController extends BaseController {

	@Autowired
	private ISuLicenseTypeService suLicenseTypeService;
	
	@RequestMapping("gspLicenseType")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/supply/suLicenseType";
	}
	
	@ResponseBody
	@RequestMapping("gspLicenseType/gridData")
	public GridDataModel<SuLicenseType> gridData(HttpSession session) {
		GridDataModel<SuLicenseType> gridDataModel=suLicenseTypeService.getGridData();
		return gridDataModel;
	}
}
