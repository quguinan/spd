package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuSupplyOrg;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuSupplyService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.base.BasePO;
import my.dao.pool.DBManager;
@Service
public class SuSupplyServiceImpl extends BaseService implements ISuSupplyService{

	@Override
	public GridDataModel<SuSupply> getGridDataByOrgid(String orgid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		
		if (!"".equals(orgid)) {
			filter.append(" and orgid = ?");
			params.add(orgid);
		}
		
		GridDataModel<SuSupply> gridDataModel = PageFactoryEasyUI.newPage(SuSupply.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
		
	}
	@Override
	public GridDataModel<SuSupply> getGridData() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		GridDataModel<SuSupply> gridDataModel = PageFactoryEasyUI.newPage(SuSupply.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
	}
	
	@Override
	public GridDataModel<SuSupply> getGridDataBySupplynameSpell(String supplyname, String spell) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(supplyname)) {
			filter.append(" and supplyname like ?");
			params.add("%"+supplyname+"%");
		}
		if (!"".equals(spell)) {
			filter.append(" and spell like ?");
			params.add("%"+spell+"%");
		}
		GridDataModel<SuSupply> gridDataModel = PageFactoryEasyUI.newPage(SuSupply.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
	}
	@Override
	public Integer selectSupply(String orgid, String supplyid) {
		try {
			SuSupplyOrg suSupplyOrg=SuSupplyOrg.INSTANCE.queryOne(" orgid=? and supplyid=?",orgid,supplyid);
			if(suSupplyOrg!=null){
				//do nothing。。。
			}else{
				suSupplyOrg=new SuSupplyOrg();
				suSupplyOrg.setOrgid(orgid);
				suSupplyOrg.setSupplyid(supplyid);
				suSupplyOrg.setLogname(orgid+supplyid);
				suSupplyOrg.setPassword("123456");
				suSupplyOrg.insert();
			}
			DBManager.commitAll();
		} catch (BeansException e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	
	
	
	
	
	
	@Override
	public Integer selectComSupply(SuComSupplyV suComSupplyV) {
		try {
			SuSupply suSupply=SuSupply.INSTANCE.queryOne(" supplyid=?",suComSupplyV.getSupplyid());
			if(suSupply!=null){
				BeanUtils.copyProperties(suComSupplyV, suSupply);
				suSupply.update();
			}else{
				suSupply=new SuSupply();
				BeanUtils.copyProperties(suComSupplyV, suSupply);
				suSupply.setStatus("0");
				suSupply.insert();
			}
			DBManager.commitAll();
		} catch (BeansException e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	@Override
	public List<SuSupply> findAll() {
		return SuSupply.INSTANCE.query("");
	}

	@Override
	public HashMap<String, Object> save(String json){
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuSupply> insert = model.inserts(SuSupply.class);
		List<SuSupply> delete = model.deletes(SuSupply.class);
		List<SuSupply> update = model.updates(SuSupply.class);
		try {
			for (SuSupply comp : delete) {
				comp.delete();
			}
			for (SuSupply comp : update) {
				comp.update();
			}
			for (SuSupply comp : insert) {
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
	public SuSupply getBySupplyid(String supplyid){
		return SuSupply.INSTANCE.queryOne("supplyid=?", supplyid);
	}
	@Override
	public List<SuSupply> findByOrgid(String orgid) {
		return SuSupplyOrg.INSTANCE.query(" orgid=?", orgid);
	}
	@Override
	public List<SuSupply> findByGroupid(String groupid) {
		String orgids=getOrgidsByGroupid(groupid);
		List<SuSupply> list=new ArrayList<>();
		List<SuSupplyOrg> listSuSupplyOrg=SuSupplyOrg.INSTANCE.query(" orgid in ("+orgids+")");
		for (SuSupplyOrg suSupplyOrg : listSuSupplyOrg) {
			SuSupply suSupply=SuSupply.INSTANCE.queryOne(" supplyid=? ", suSupplyOrg.getSupplyid());
			list.add(suSupply);
		}
		//使用java8新特性stream进行List去重 
		List<SuSupply> newList = list.stream().distinct().collect(Collectors.toList()); 
		return newList;
	}
}



