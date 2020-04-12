package com.cms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsLot;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuOrgGroupDoc;
import com.cms.model.biz.supply.SuOrgGroupDtl;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.SessionHelpUtils;

import my.base.BasePO;
import my.dao.pool.DBManager;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年5月9日 上午10:10:59 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Component
public class BaseService  {
	
//	private static Singleton1 singleton = null;
//	private Singleton1() {
//	}
//	public static Singleton1 getInstance() {
//    if (singleton == null) {
//      singleton = new Singleton1();
//    }
//    return singleton;
//	}
	
	public SysUser getCurrentUser(){
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		return user;
	}
	
	public SuUser getCurrentSuUser(){
		SuUser suUser=(SuUser)SessionHelpUtils.getSession().getAttribute("suUser");
		return suUser;
	}
	
//	public HashMap<String, Object> save(String json,Class<T> cls){
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
//		List<T> insert = model.inserts(cls);
//		List<T> delete = model.deletes(cls);
//		List<T> update = model.updates(cls);
//		try {
//			for (T comp : delete) {
//				comp.delete();
//			}
//			for (T comp : update) {
//				comp.update();
//			}
//			for (T comp : insert) {
//				comp.insert();
//			}
//			DBManager.commitAll();
//			result.put("msg", "保存成功");
//			result.put("success", true);
//			
//		} catch (Exception e) {
//			DBManager.rollbackAll();
//			e.printStackTrace();
//			result.put("success", false);
//			result.put("msg", "失败!:"+e.getMessage());
//		}finally {
//			
//		}
//		return result;
//	}
	
	public String getOrgidsByGroupid(String groupid){
		List<SuOrgGroupDtl> suOrgs=SuOrgGroupDtl.INSTANCE.query("groupid=?", groupid);
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < suOrgs.size(); i++) {
			if(i==0){
				sb.append(suOrgs.get(i).getOrgid());
			}else{
				sb.append(",").append(suOrgs.get(i).getOrgid());
			}
		}
		return sb.toString();
	}
}











