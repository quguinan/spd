package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuOrgGroupDoc;
import com.cms.model.biz.supply.SuOrgGroupDtl;

import net.sf.json.JSONArray;

public interface ISuOrgService {
	
	
	public GridDataModel<SuOrg> gridDataOrg();

	public GridDataModel<SuOrg> gridDataOrg(String groupid);

	/*
	 * 根据groupid查找org
	 */
	public JSONArray findOrgByGroupid(String groupid);

	public JSONArray findOrgAll();
	
	public List<SuOrg> findAll();
	
	//登录名唯一
	public SuOrg getByLogname(String logname);
	
	//验证用户名，密码
	public SuOrg getByLognamePassword(String logname,String password);
	
	//机构分组doc  json
	public JSONArray findAlljson();
	//机构分组doc
	public GridDataModel<SuOrgGroupDoc> gridDataOrgGroupDoc();
	
	//机构分组dtl
	public GridDataModel<SuOrgGroupDtl> gridDataOrgGroupDtl(String groupid);
}
