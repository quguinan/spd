package com.cms.service.biz.dict.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuOrg;
import com.cms.service.biz.dict.IDictOrgService;
import com.cms.util.biz.PageFactoryEasyUI;
@Service
public class DictOrgServiceImpl implements IDictOrgService{

	@Override
	public List<SuOrg> findAll() {
		return SuOrg.INSTANCE.query("");
	}

	@Override
	public GridDataModel<SuOrg> getGridDataByOrgid(String orgid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(!orgid.equals("")){
			filter.append(" and orgid = ?");
			params.add(orgid);
		}
		
		GridDataModel<SuOrg> gridDataModel=PageFactoryEasyUI.newPage(
				SuOrg.class, filter.toString()," order by orgid ",params.toArray());
		return gridDataModel;
	}

	@Override
	public GridDataModel<SuOrg> getGridData() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		GridDataModel<SuOrg> gridDataModel=PageFactoryEasyUI.newPage(
				SuOrg.class, filter.toString()," order by orgid ",params.toArray());
		return gridDataModel;
	}

}
