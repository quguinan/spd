package com.cms.service.biz.dict;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuOrg;

public interface IDictOrgService {
	
	public List<SuOrg> findAll();
	
	public GridDataModel<SuOrg> getGridDataByOrgid(String orgid);

	public GridDataModel<SuOrg> getGridData();

}
