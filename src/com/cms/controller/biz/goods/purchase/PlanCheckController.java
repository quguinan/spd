package com.cms.controller.biz.goods.purchase;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.model.sys.SysUser;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年5月10日 下午5:08:55 
 * 
 *@descriptions 类说明：计划审批
 */
@Controller
@RequestMapping("/cms/goods/purchase")
public class PlanCheckController extends BaseController{

	@RequestMapping("plan_check")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/purchase/plan_check";
	}
}
