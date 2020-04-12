package com.cms.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysUser;
import com.cms.service.sys.SysDeptService;
import com.cms.service.sys.SysRoleMenuService;
import com.cms.service.sys.SysUserService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysDeptService sysDeptService;

	@RequestMapping("user/list")
	public String desktop(Model m, HttpSession session) {
		String menuid = param("menuid", "");
		m.addAttribute("menuid", menuid);
		SysUser user = (SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/sys/user/user";
	}

	/**
	 * 平台用户管理 add by wlm 2020年3月9日
	 * 
	 * @return
	 */
	@RequestMapping("spd/user/list")
	public String UserList(Model m, HttpSession session) {
		String menuid = param("menuid", "");
		m.addAttribute("menuid", menuid);
		SysUser user = (SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "spd/processing/platform/user/list";
	}

	@ResponseBody
	@RequestMapping("user/gridData")
	public GridDataModel<SysUser> gridData() {
		String realname = param("realname", "");
		String roleid = param("roleid", "");
		GridDataModel<SysUser> gridDataModel = sysUserService.getGridData(realname, roleid);
		return gridDataModel;
	}

	@ResponseBody
	@RequestMapping("user/gridSave")
	public JSONObject gridSave(final @RequestParam("json") String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = sysUserService.save(json);
		return JSONObject.fromObject(result);

	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("user/getRoleList") public JSONArray getRoleList() {
	 * List<SysRole> list=sysRoleMenuService.findAllRolesList(); JSONArray
	 * jsonData=JSONArray.fromObject(list); return jsonData; }
	 */

	@ResponseBody
	@RequestMapping("user/getRoleTreedata")
	public JSONArray getRoleTreedata() {
		JSONArray ja = sysRoleMenuService.findAllJSONArray();
		return JSONArray.fromObject(ja.toString().replace("roleid", "id").replace("rolename", "text"));
	}

	@ResponseBody
	@RequestMapping("user/getDeptTreedata")
	public JSONArray getDeptTreedata() {
		JSONArray ja = sysDeptService.findAllJSONArray();
		return JSONArray.fromObject(ja.toString().replace("deptid", "id").replace("deptname", "text"));
	}

	@ResponseBody
	@RequestMapping("user/getOrgTreedata")
	public JSONArray getOrgList() {
		JSONArray ja = new JSONArray();
		List<SuOrg> list = SuOrg.INSTANCE.query("");
		for (SuOrg dictOrg : list) {
			ja.add(JSONObject.fromObject(dictOrg).toString().replace("orgid", "id").replace("orgname", "text"));
		}
		return ja;
	}
}
