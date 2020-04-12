package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuSupplyOrg;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuSupplyOrgService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.dao.pool.DBManager;

@Service
public class SuSupplyOrgService implements ISuSupplyOrgService {

	@Override
	public GridDataModel<SuSupplyOrg> getGridData() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		GridDataModel<SuSupplyOrg> gridDataModel = PageFactoryEasyUI.newPage(SuSupplyOrg.class, filter.toString(),
				" order by orgid,supplyid ", params.toArray());
		return gridDataModel;
	}
	@Override
	public List<SuSupplyOrg> findBySupplyid(String supplyid) {
		// TODO Auto-generated method stub
		return SuSupplyOrg.INSTANCE.query(" supplyid=?", supplyid);
	}
	@Override
	public HashMap<String, Object> save(String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuSupplyOrg> insert = model.inserts(SuSupplyOrg.class);
		List<SuSupplyOrg> delete = model.deletes(SuSupplyOrg.class);
		List<SuSupplyOrg> update = model.updates(SuSupplyOrg.class);
		try {
			for (SuSupplyOrg comp : delete) {
				comp.delete();
			}
			for (SuSupplyOrg comp : update) {
				comp.update();
			}
			for (SuSupplyOrg comp : insert) {
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:"+e.getMessage());
		}finally {
			
		}
		
		return result;
	}

	@Override
	public GridDataModel<SuSupplyOrg> getGridDataByOrgid(String orgid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		if (!"".equals(orgid)) {
			filter.append(" and orgid = ?");
			params.add(orgid);
		}
		GridDataModel<SuSupplyOrg> gridDataModel = PageFactoryEasyUI.newPage(SuSupplyOrg.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
	}
	@Override
	public SuSupplyOrg getBySupplyidOrgid(String orgid, String supplyid) {
		// TODO Auto-generated method stub
		return SuSupplyOrg.INSTANCE.queryOne(" orgid=? and supplyid=? ", orgid,supplyid);
	}
	@Override
	public SuSupplyOrg getByLogname(String logname) {
		// TODO Auto-generated method stub
		return SuSupplyOrg.INSTANCE.queryOne(" logname=? ", logname);
	}
	@Override
	public SuSupplyOrg getByLognamePassword(String logname, String password) {
		// TODO Auto-generated method stub
		return SuSupplyOrg.INSTANCE.queryOne(" logname=? and password=? ", logname,password);
	}

	

}
