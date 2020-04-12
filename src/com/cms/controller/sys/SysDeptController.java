package com.cms.controller.sys;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.SysMenuForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUser;
import com.cms.model.sys.SysUserButtonRoleV;
import com.cms.model.sys.SysUserMenuRoleV;
import com.cms.service.sys.SysDeptService;
import com.cms.service.sys.SysMenuService;
import com.cms.util.common.CutSubString;

import my.dao.pool.DBManager;
import my.dao.utils.Record;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysDeptController extends BaseController{
	@Autowired
	private SysDeptService sysDeptService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public SysMenuForm getForm(){
		return new SysMenuForm();
	}
	@RequestMapping("dept/list")
	public String desktop(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/sys/dept/dept";
	}
	/**
	 * 返回科室列表(全部) treegridData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("dept/treegridData")
	public JSONArray treegridData() {
		JSONArray ja1=sysDeptService.findAllJSONArray();
		return ja1;
	}
}
