package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuGoodsSupply;
import com.cms.model.biz.supply.SuSupply;
import com.cms.service.biz.supply.ISuGoodsSupplyService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.model.MsgModel;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;

@Service
public class SuGoodsSupplyServiceImpl implements ISuGoodsSupplyService{

	@Override
	public Integer checkBeforeMapData(String orgid, String supplyid, String sugoodsid, String goodsid) {
		SuGoodsSupply suGoodsSupply=SuGoodsSupply.INSTANCE
				.queryOne("orgid=? and supplyid=? and goodsid=?",  orgid,  supplyid,  goodsid);
		if(suGoodsSupply==null){
			return -1;
		}else{
			return Integer.valueOf(suGoodsSupply.getSugoodsid());
		}
	}
	
	@Override
	public int mapData(String orgid, String supplyid, String sugoodsid, String goodsid) {
		try {
			SuGoodsSupply suGoodsSupply=SuGoodsSupply.INSTANCE
					.queryOne("orgid=? and supplyid=? and sugoodsid=?",  orgid,  supplyid,  sugoodsid);
			suGoodsSupply.setGoodsid(goodsid);
			suGoodsSupply.update();
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	@Override
	public int unMapData(String orgid, String supplyid, String sugoodsid) {
		try {
			SuGoodsSupply suGoodsSupply=SuGoodsSupply.INSTANCE
					.queryOne("orgid=? and supplyid=? and sugoodsid=?",  orgid,  supplyid,  sugoodsid);
			suGoodsSupply.setGoodsid("");
			suGoodsSupply.update();
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	@Override
	public GridDataModel<SuGoodsSupply> gridDataSupplyByNameSpell(String orgid,String supplyid,String sugoodsid, String sugoodsname, String spell) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		filter.append(" and orgid = ? and supplyid=? ");
		params.add(orgid);
		params.add(supplyid);
		
		if (!sugoodsid.equals("")) {
			filter.append(" and sugoodsid = ?");
			params.add(sugoodsid);
		}
		
		if (!sugoodsname.equals("")) {
			filter.append(" and sugoodsname like ?");
			params.add("%"+sugoodsname+"%");
		}
		if (!spell.equals("")) {
			filter.append(" and spell like ?");
			params.add("%"+spell+"%");
		}
		GridDataModel<SuGoodsSupply> gridDataModel = PageFactoryEasyUI.newPage(SuGoodsSupply.class, filter.toString(),
				" order by sugoodsid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public MsgModel importData(JSONArray importModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuGoodsSupply getBySupplyidOrgidGoodsid(String supplyid, String orgid,String goodsid) {
		// TODO Auto-generated method stub
		SuGoodsSupply suGoodsSupply=SuGoodsSupply.INSTANCE.queryOne("supplyid=? and orgid=? and goodsid=?", supplyid,orgid,goodsid);
		return suGoodsSupply;
	}

	@Override
	public SuGoodsSupply getBySupplyidOrgidSugoodsid(String supplyid, String orgid, String sugoodsid) {
		// TODO Auto-generated method stub
		SuGoodsSupply suGoodsSupply=SuGoodsSupply.INSTANCE.queryOne("supplyid=? and orgid=? and sugoodsid=?", supplyid,orgid,sugoodsid);
		return suGoodsSupply;
	}

	

}
