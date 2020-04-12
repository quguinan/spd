package com.cms.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysUser;
import com.cms.service.BaseService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.dao.pool.DBManager;
import my.util.MD5Util;

@Service
public class SysUserService extends BaseService {

	public GridDataModel<SysUser> getGridData(String realname, String roleid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!"-1".equals(super.getCurrentUser().getUserid())) {
			filter.append(" and userid<>'-1'");
		}
		if (realname.length() > 0) {
			filter.append(" and realname like ? ");
			params.add(realname + "%");
		}
		if (roleid.length() > 0) {
			filter.append(" and roleid = ? ");
			params.add(roleid);
		}

		GridDataModel<SysUser> gridDataModel = PageFactoryEasyUI.newPage(SysUser.class, filter.toString(),
				" order by userid ", params.toArray());
		return gridDataModel;
	}

	public GridDataModel<SysUser> getGridData(String userid, String username, String realname) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		filter.append(" and userid<>'-1'");
		if (userid.length() > 0) {
			filter.append(" and userid = ? ");
			params.add(userid);
		}
		if (realname.length() > 0) {
			filter.append(" and realname like ? ");
			params.add(realname + "%");
		}
		if (username.length() > 0) {
			filter.append(" and username like ? ");
			params.add(username + "%");
		}

		GridDataModel<SysUser> gridDataModel = PageFactoryEasyUI.newPage(SysUser.class, filter.toString(),
				" order by userid ", params.toArray());
		return gridDataModel;
	}

	public HashMap<String, Object> save(String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SysUser> insert = model.inserts(SysUser.class);
		List<SysUser> delete = model.deletes(SysUser.class);
		List<SysUser> update = model.updates(SysUser.class);
		try {
			for (SysUser comp : delete) {
				comp.delete();
			}
			for (SysUser comp : update) {
				/**
				 * 比较如果一致说明密码没有修改,暂时这么处理，正常人不会将密码设置能32位数
				 * 
				 * update by wlm 2020年3月9日 判断修改的数据中密码有没有修改
				 */
				String newpassword = comp.getPassword();

				if (newpassword.length() == 32) {
					comp.setPassword(newpassword);
				} else {
					comp.setPassword((MD5Util.string2MD5(newpassword)));
				}
				comp.update();
			}
			for (SysUser comp : insert) {

				comp.setPassword((MD5Util.string2MD5(comp.getPassword())));
				// comp.setUserid(comp.newId()+"");//oracle可以自动生成id 这里不能这么写
				comp.setUserid(comp.newIdForOracle());
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);

		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		} finally {

		}

		return result;
	}
}
