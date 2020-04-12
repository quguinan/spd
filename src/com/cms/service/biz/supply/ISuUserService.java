package com.cms.service.biz.supply;

import java.util.HashMap;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.biz.supply.SuUserOrgV;
import com.cms.model.biz.supply.SuUserSupplyV;

public interface ISuUserService {
	
	/*平台全部用户*/
	public GridDataModel<SuUser> gridDataSuUser();
	/*平台机构用户*/
	public GridDataModel<SuUserOrgV> gridDataSuUserOrg();
	/*平台供应商用户*/
	public GridDataModel<SuUserSupplyV> gridDataSuUserSupply();

	public HashMap<String, Object> save(String json);
	
	/*
	 * 检查当前用户是否是 供应商平台用户
	 */
	public HashMap<String, Object> doCheckSuUser();
	/*
	 * 检查当前供应商平台用户是否是 机构用户 
	 */
	public HashMap<String, Object> doCheckSuUserOrg();
	/*
	 * 检查当前供应商平台用户是否是 供应商用户 
	 */
	public HashMap<String, Object> doCheckSuUserSupply();
	/*
	 * 检查当前供应商平台用户  的 供应商用户  是否维护了管控证照
	 */
	public HashMap<String, Object> doCheckSuUserSupplyLicense();
	
}
