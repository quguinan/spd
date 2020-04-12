package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.service.biz.supply.ISuComSupplyService;
import com.cms.util.biz.PageFactoryEasyUI;

@Service
public class SuComSupplyServiceImpl implements ISuComSupplyService {

	@Override
	public GridDataModel<SuComSupplyV> getGridDataByOrgid(String supplyname, String supplyspell, String type) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		if (!supplyname.equals("")) {
			filter.append(" and supplyname like ?");
			params.add("%" + supplyname + "%");
		}
		if (!supplyspell.equals("")) {
			filter.append(" and spell like ?");
			params.add("%" + supplyspell + "%");
		}
		if (!type.equals("")) {
			filter.append(" and sucomtype =?");
			params.add(type);
		}
		GridDataModel<SuComSupplyV> gridDataModel = PageFactoryEasyUI.newPage(SuComSupplyV.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
	}

}
