package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.util.biz.model.MsgModel;

import net.sf.json.JSONArray;
public interface ISuLicenseTypeService{
	public Long newId();
	//增
	public Integer insert(SuLicenseType gspLicenseType);
	//删
	public Integer delete(SuLicenseType gspLicenseType);
	//改
	public Integer update(SuLicenseType gspLicenseType);
	//查所有
	public List<SuLicenseType> findAll(String orgid);
	//查一
	public SuLicenseType getById(String lisencetypeid);
	//查全部分页 , 平台级数据 不需要orgid区分
	public GridDataModel<SuLicenseType> getGridData();
	/*
	 * 可以从外部获取数据源,导入到本地
	 */
	public MsgModel importData(JSONArray importModel);
}
