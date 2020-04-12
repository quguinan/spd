package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuCategoryDoc;
import com.cms.model.biz.supply.SuCategoryDtl;
import com.cms.model.biz.supply.SuCategoryDtlLicenseV;

public interface ISuCategoryService {
	//总单ID
	public Long newDocId();
	//细单ID
	public Long newDtlId();
	//增doc
	public Integer insert(SuCategoryDoc doc);
	//增dtl
	public Integer insert(SuCategoryDtl dtl);
	//删doc
	public Integer delete(SuCategoryDoc doc);
	//删dtl
	public Integer delete(SuCategoryDtl dtl);
	//改doc
	public Integer update(SuCategoryDoc doc);
	//改dtl
	public Integer update(SuCategoryDtl dtl);
	//查所有doc 平台数据 不分机构
	public List<SuCategoryDoc> findAll();
	//根据docid查dtl
	public List<SuCategoryDtl> findDtlByDocid(String docid);
	//查全部分页doc 本机构
	public GridDataModel<SuCategoryDoc> getGridDataDoc();
	//根据docid查分页dtl
	public GridDataModel<SuCategoryDtl> getGridDataDtl(String docid);
	//根据supplyid查分页dtl
	public GridDataModel<SuCategoryDtlLicenseV> getGridDataDtlByOrgidSupplyid( String supplyid);
	
	
	public List<SuCategoryDoc> findByDtlInfo();
}
