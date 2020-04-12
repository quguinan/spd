package com.cms.service.biz.supply;

import java.util.HashMap;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuSupplyOrg;

/**
 * 供应商与机构的1对多对应关系处理 su_supply_org
 */
public interface ISuSupplyOrgService {
	
	public GridDataModel<SuSupplyOrg> getGridData();

	public GridDataModel<SuSupplyOrg> getGridDataByOrgid(String orgid);
	
	public List<SuSupplyOrg> findBySupplyid(String supplyid);
	
	public SuSupplyOrg getBySupplyidOrgid(String orgid,String supplyid);

	public SuSupplyOrg getByLogname(String logname);

	public SuSupplyOrg getByLognamePassword(String logname,String password);
	
	public HashMap<String, Object> save(String json);
	
	
}
