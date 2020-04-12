package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuCategoryDoc;
import com.cms.model.biz.supply.SuCategoryDtl;
import com.cms.model.biz.supply.SuCategoryDtlLicenseV;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.model.biz.supply.SuSupply;
import com.cms.service.biz.supply.ISuCategoryService;
import com.cms.util.biz.PageFactoryEasyUI;

@Service
public class SuCategoryServiceImpl implements ISuCategoryService {
	@Override
	public Long newDocId() {
		// TODO Auto-generated method stub
		return SuCategoryDoc.INSTANCE.newId();
	}

	@Override
	public Long newDtlId() {
		// TODO Auto-generated method stub
		return SuCategoryDtl.INSTANCE.newId();
	}

	@Override
	public Integer insert(SuCategoryDoc doc) {
		// TODO Auto-generated method stub
		doc.insert();
		return 1;
	}

	@Override
	public Integer insert(SuCategoryDtl dtl) {
		// TODO Auto-generated method stub
		dtl.insert();
		return 1;
	}

	@Override
	public Integer delete(SuCategoryDoc doc) {
		// TODO Auto-generated method stub
		return doc.delete();
	}

	@Override
	public Integer delete(SuCategoryDtl dtl) {
		// TODO Auto-generated method stub
		return dtl.delete();
	}

	@Override
	public Integer update(SuCategoryDoc doc) {
		// TODO Auto-generated method stub
		return doc.update();
	}

	@Override
	public Integer update(SuCategoryDtl dtl) {
		// TODO Auto-generated method stub
		return dtl.update();
	}

	@Override
	public List<SuCategoryDoc> findAll() {
		// TODO Auto-generated method stub
		return SuCategoryDoc.INSTANCE.query("");
	}

	@Override
	public List<SuCategoryDtl> findDtlByDocid(String docid) {
		// TODO Auto-generated method stub
		return SuCategoryDtl.INSTANCE.query("docid=?", docid);
	}

	@Override
	public GridDataModel<SuCategoryDoc> getGridDataDoc() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
//		filter.append(" and orgid = ?");
//		params.add(orgid);
		return PageFactoryEasyUI.newPage(SuCategoryDoc.class, filter.toString(), " order by docid", params.toArray());
	}

	@Override
	public GridDataModel<SuCategoryDtl> getGridDataDtl(String docid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("docid=?");
		params.add(docid);
		return PageFactoryEasyUI.newPage(SuCategoryDtl.class, filter.toString(), " order by dtlid", params.toArray());
	}

	@Override
	public GridDataModel<SuCategoryDtlLicenseV> getGridDataDtlByOrgidSupplyid(String supplyid) {
		SuSupply suSupply = SuSupply.INSTANCE.queryOne("supplyid=?", supplyid);
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		filter.append(" and docid=?");
		params.add(suSupply.getCategoryid());

		filter.append(" and supplyid=?");
		params.add(supplyid);

		return PageFactoryEasyUI.newPage(SuCategoryDtlLicenseV.class, filter.toString(), " order by dtlid",
				params.toArray());
	}

	@Override
	public List<SuCategoryDoc> findByDtlInfo() {
		// TODO Auto-generated method stub
		return SuCategoryDoc.INSTANCE.query("docid in(96,139)");
	}
}
