package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.biz.supply.SuUserOrgV;
import com.cms.model.biz.supply.SuUserSupplyV;
import com.cms.model.sys.SysUser;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuOrgService;
import com.cms.service.biz.supply.ISuUserService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.chart.BaseSeries;
import my.dao.pool.DBManager;
import my.util.MD5Util;
import net.sf.json.JSONObject;
@Service
public class SuUserServiceImpl extends BaseService implements ISuUserService  {
	
	@Override
	public GridDataModel<SuUserOrgV> gridDataSuUserOrg() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuUserOrgV.class, filter.toString(), 
				" order by userid", params.toArray());
	}

	@Override
	public GridDataModel<SuUserSupplyV> gridDataSuUserSupply() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuUserSupplyV.class, filter.toString(), 
				" order by userid", params.toArray());
	}
	@Override
	public GridDataModel<SuUser> gridDataSuUser() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuUser.class, filter.toString(), 
				" order by userid", params.toArray());
	}

	@Override
	public HashMap<String, Object> save(String json){
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuUser> insert = model.inserts(SuUser.class);
		List<SuUser> delete = model.deletes(SuUser.class);
		List<SuUser> update = model.updates(SuUser.class);
		try {
			for (SuUser comp : delete) {
				comp.delete();
			}
			for (SuUser comp : update) {
				comp.update();
			}
			for (SuUser comp : insert) {
				//comp.setUserid(comp.newId()+"");//oracle可以自动生成id 这里不能这么写
//				comp.setUserid(comp.newIdForOracle());
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:"+e.getMessage());
		}finally {
			
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> doCheckSuUser() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SuUser suUser =getCurrentSuUser();
		SysUser sysUser =getCurrentUser();
		if(suUser==null){
			result.put("msg", "当前用["+sysUser.getRealname()+"]户没有在供应商平台维护,无法进行操作!");
			result.put("success", false);
		}else{
			result.put("success", true);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> doCheckSuUserOrg() {
		HashMap<String, Object> result = doCheckSuUser();
		if("false".equals(result.get("success").toString())){
			return result;
		}
		SuUser suUser =getCurrentSuUser();
		SysUser sysUser =getCurrentUser();
		if(suUser.getGroupid()==null){
			result.put("msg", "当前用户["+sysUser.getRealname()+"]没有维护对应机构分组,无法进行操作!");
			result.put("success", false);
		}else{
			result.put("success", true);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> doCheckSuUserSupply() {
		HashMap<String, Object> result = doCheckSuUser();
		if("false".equals(result.get("success").toString())){
			return result;
		}
		SuUser suUser =getCurrentSuUser();
		SysUser sysUser =getCurrentUser();
		if(suUser.getSupplyid()==null){
			result.put("msg", "当前用户["+sysUser.getRealname()+"]没有维护对应供应商,无法进行操作!");
			result.put("success", false);	
		}else{
			result.put("success", true);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> doCheckSuUserSupplyLicense() {
		HashMap<String, Object> result = doCheckSuUserSupply();
		if("false".equals(result.get("success").toString())){
			return result;
		}
		SuUser suUser =getCurrentSuUser();
		SysUser sysUser =getCurrentUser();
		//用户维护完毕后,判断用户对应供应商,是否选择了证照管控
		SuSupply suSupply=SuSupply.INSTANCE.queryOne("supplyid=?", suUser.getSupplyid());
		if(suSupply.getCategoryid()==null){
			result.put("msg", "当前用户["+sysUser.getRealname()+"]对应供应商没有选择证照管控,无法进行当前操作!");
			result.put("success", false);	
		}else{
			result.put("success", true);
		}
		
		return result;
	}

	
}
