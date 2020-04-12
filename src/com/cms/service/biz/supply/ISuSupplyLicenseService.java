package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuSupplyLicense;
import com.cms.util.biz.model.MsgModel;

import net.sf.json.JSONArray;

public interface ISuSupplyLicenseService {
	public Long newId();
	//增
	public Integer insert(SuSupplyLicense dictSupplyLicense);
	//删
	public Integer delete(SuSupplyLicense dictSupplyLicense);
	//改
	public Integer update(SuSupplyLicense dictSupplyLicense);
	//查所有
	public List<SuSupplyLicense> findAll(String orgid);
	//查一,1
	public SuSupplyLicense getById(String licenseid);
	//查一,2
	public SuSupplyLicense getBySupplyidLicensetypeid(String supplyid,String licensetypeid);
	//查分页
	public GridDataModel<SuSupplyLicense> getGridData(String licensetypeid);
	/*
	 * 可以从外部获取数据源,导入到本地
	 * 返回标准数据格式 msgModel.java 
	 */
	public MsgModel importData(JSONArray importModel);
	
	public SuSupplyLicense newSuSupplyLicense(String supplyid,String licensetypeid);
	
}
