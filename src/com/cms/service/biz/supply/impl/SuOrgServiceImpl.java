package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuOrgGroupDoc;
import com.cms.model.biz.supply.SuOrgGroupDtl;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuOrgService;
import com.cms.util.biz.PageFactoryEasyUI;

import net.sf.json.JSONArray;
@Service
public class SuOrgServiceImpl extends BaseService implements ISuOrgService{

	@Override
	public GridDataModel<SuOrg> gridDataOrg() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuOrg.class, filter.toString(), " order by orgid", params.toArray());
	}

	@Override
	public GridDataModel<SuOrg> gridDataOrg(String groupid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		String orgids=getOrgidsByGroupid(groupid);
		if(!"".equals(orgids)){
			filter.append(" orgid in ("+orgids+")");
		}
		return PageFactoryEasyUI.newPage(SuOrg.class, filter.toString(), " order by orgid", params.toArray());
	}
	
	@Override
	public JSONArray findOrgByGroupid(String groupid) {
		String orgids=getOrgidsByGroupid(groupid);
		JSONArray ja=JSONArray.fromObject(SuOrg.INSTANCE.query(" orgid in ( "+orgids+" )"));
		return ja;
	}
	@Override
	public JSONArray findOrgAll() {
		JSONArray ja=JSONArray.fromObject(SuOrg.INSTANCE.query(""));
		return ja;
	}
	@Override
	public List<SuOrg> findAll() {
		List<SuOrg> list=SuOrg.INSTANCE.query("");
		return list;
	}

	@Override
	public SuOrg getByLogname(String logname) {
		// TODO Auto-generated method stub
		return SuOrg.INSTANCE.queryOne("logname=?", logname);
	}
	
	@Override
	public SuOrg getByLognamePassword(String logname,String password) {
		// TODO Auto-generated method stub
		return SuOrg.INSTANCE.queryOne("logname=? and password=?", logname,password);
	}

	@Override
	public JSONArray findAlljson() {
		return JSONArray.fromObject(SuOrgGroupDoc.INSTANCE.query(""));
	}
	
	@Override
	public GridDataModel<SuOrgGroupDoc> gridDataOrgGroupDoc() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuOrgGroupDoc.class, filter.toString(), " order by groupid", params.toArray());
	}

	@Override
	public GridDataModel<SuOrgGroupDtl> gridDataOrgGroupDtl(String groupid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" groupid=? ");
		params.add(groupid);
		return PageFactoryEasyUI.newPage(SuOrgGroupDtl.class, filter.toString(), " order by groupid,orgid", params.toArray());
	}

	

	


	

}
