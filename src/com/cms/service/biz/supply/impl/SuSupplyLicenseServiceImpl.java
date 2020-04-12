package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictSupply;
import com.cms.model.biz.supply.SuSupplyLicense;
import com.cms.service.biz.supply.ISuSupplyLicenseService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.model.MsgModel;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月16日 上午11:26:52
 * 
 * @descriptions 类说明：证照管控字典SERVICEImpl
 */
@Service
public class SuSupplyLicenseServiceImpl implements ISuSupplyLicenseService{

	@Override
	public Long newId() {
		return SuSupplyLicense.INSTANCE.newId();
	}

	@Override
	public Integer insert(SuSupplyLicense dictSupplyLicense) {
		return dictSupplyLicense.insert();
	}

	@Override
	public Integer delete(SuSupplyLicense dictSupplyLicense) {
		return dictSupplyLicense.delete();
	}

	@Override
	public Integer update(SuSupplyLicense dictSupplyLicense) {
		return dictSupplyLicense.update();
	}

	@Override
	public List<SuSupplyLicense> findAll(String orgid) {
		return SuSupplyLicense.INSTANCE.query(" orgid=? ",orgid);
	}

	@Override
	public SuSupplyLicense getById(String licenseid) {
		return SuSupplyLicense.INSTANCE.queryOne("licenseid=?", licenseid);
	}
	@Override
	public SuSupplyLicense getBySupplyidLicensetypeid(String supplyid, String licensetypeid) {
		// TODO Auto-generated method stub
		return SuSupplyLicense.INSTANCE.queryOne("supplyid=? and licensetypeid=? ",supplyid, licensetypeid);
	}

	@Override
	public GridDataModel<SuSupplyLicense> getGridData(String licensetypeid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!licensetypeid.equals("")) {
			filter.append(" and licensetypeid = ?");
			params.add(licensetypeid);
		}
		GridDataModel<SuSupplyLicense> gridDataModel = PageFactoryEasyUI.newPage(SuSupplyLicense.class, filter.toString(),
				" order by licenseid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public MsgModel importData(JSONArray importModelList) {
		MsgModel msg=new MsgModel();
		try {
			for (Object obj : importModelList) {
				SuSupplyLicense model=JSON.parseObject(JSONObject.fromObject(obj).toString(), SuSupplyLicense.class);
				//要么插入，要么更新，没有删除！
				if(getById(model.getLicenseid()) != null){
					model.update();
				}else{
					model.insert();
				}
			}
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			msg.setCode("-1");
			msg.setContent("系统错误："+e.getMessage());
			msg.setMsg("Failed");
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public SuSupplyLicense newSuSupplyLicense( String supplyid, String licensetypeid) {
		SuSupplyLicense suSupplyLicense = new SuSupplyLicense(SuSupplyLicense.INSTANCE.newId().toString(), supplyid, licensetypeid);
		try {
			suSupplyLicense.insert();
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
		}
		
		return suSupplyLicense;
	}


}



