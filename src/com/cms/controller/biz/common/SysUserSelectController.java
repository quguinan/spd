package com.cms.controller.biz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.sys.SysUser;
import com.cms.service.sys.SysUserService;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class SysUserSelectController extends BaseController{
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping("sysUserSelect")
	public String sysUserSelect(Model m) {
		return "cms/biz/common/sysUserSelect";
	}
	@ResponseBody
	@RequestMapping("sysUserSelect/datagrid")
	public GridDataModel<SysUser> datagrid() {
		String userid=param("userid","");
		String username=param("sysUsernameselect","");
		String realname=param("sysRealnameselect","");
		GridDataModel<SysUser> gridDataModel=sysUserService.getGridData(userid, username, realname);
		return gridDataModel;
	}
}
