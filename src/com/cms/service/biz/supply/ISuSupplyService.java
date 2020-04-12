package com.cms.service.biz.supply;

import java.util.HashMap;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuSupplyOrg;

public interface ISuSupplyService {
	
	/**
	 * 平台供应商数据
	 * @param orgid
	 * @return
	 */
	public GridDataModel<SuSupply> getGridDataByOrgid(String orgid);

	public GridDataModel<SuSupply> getGridData();
	
	public GridDataModel<SuSupply> getGridDataBySupplynameSpell(String supplyname,String spell);

	public List<SuSupply> findAll();

	public List<SuSupply> findByOrgid(String orgid);

	public List<SuSupply> findByGroupid(String groupid);
	//from supplyOrg
	public Integer selectSupply(String orgid,String supplyid);
	/**
	 * 选择供应商,来自com的接口
	 * @return
	 */
	public Integer selectComSupply(SuComSupplyV suComSupplyV);
	
	public HashMap<String, Object> save(String json);

	public SuSupply getBySupplyid(String supplyid);

	
	
}
