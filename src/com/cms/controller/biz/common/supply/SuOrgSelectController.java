package com.cms.controller.biz.common.supply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.dict.impl.DictSupplyServiceImpl;
import com.cms.service.biz.supply.ISuOrgService;

import my.web.BaseController;
import net.sf.json.JSONArray;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class SuOrgSelectController extends BaseController{
	

	@Autowired
	private ISuOrgService suOrgService;
	
	
	@ResponseBody
	@RequestMapping("suSelectOrg")
	public JSONArray suSelectOrg(HttpSession session) {
		SysUser sysUser=(SysUser) session.getAttribute("user");
		SuUser suUser=(SuUser) session.getAttribute("suUser");
		JSONArray ja=new JSONArray();
		if(suUser!=null){
			ja=suOrgService.findOrgByGroupid(suUser.getGroupid());
		}else if("-1".equals(sysUser.getUserid())||"suadmin".equals(sysUser.getUsername())){
			ja=suOrgService.findOrgAll();
		}else{
			ja=null;
		}
		
		return ja;
	}
	
}
