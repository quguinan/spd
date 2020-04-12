package com.cms.service.biz.supply;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComSupplyV;

public interface ISuComSupplyService {

	public GridDataModel<SuComSupplyV> getGridDataByOrgid(String supplyname, String supplyspell, String type);

}
