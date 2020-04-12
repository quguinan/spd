package com.cms.service.biz.dict;

import java.util.HashMap;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictSupply;

public interface IDictSupplyService {

	public List<DictSupply> findAll();

	public GridDataModel<DictSupply> getGridData(String supplyname, String spell);

	public HashMap<String, Object> save(String json);

	public List<DictSupply> getGridByType();

}
