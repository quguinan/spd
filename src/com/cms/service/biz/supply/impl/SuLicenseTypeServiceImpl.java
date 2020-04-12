package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuLicenseType;
import com.cms.service.biz.supply.ISuLicenseTypeService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.model.MsgModel;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SuLicenseTypeServiceImpl implements ISuLicenseTypeService{

	@Override
	public Long newId() {
		return SuLicenseType.INSTANCE.newId();
	}

	@Override
	public Integer insert(SuLicenseType gspLicenseType) {
		return gspLicenseType.insert();
	}

	@Override
	public Integer delete(SuLicenseType gspLicenseType) {
		return gspLicenseType.delete();
	}

	@Override
	public Integer update(SuLicenseType gspLicenseType) {
		return gspLicenseType.update();
	}

	@Override
	public List<SuLicenseType> findAll(String orgid) {
		return SuLicenseType.INSTANCE.query("orgid=?",orgid);
	}

	@Override
	public SuLicenseType getById(String licensetypeid) {
		return SuLicenseType.INSTANCE.queryOne("licensetypeid=?", licensetypeid);
	}

	@Override
	public GridDataModel<SuLicenseType> getGridData() {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		return PageFactoryEasyUI.newPage(SuLicenseType.class, filter.toString(), " order by licensetypeid", params.toArray());
	}

	@Override
	public MsgModel importData(JSONArray importModelList) {
		MsgModel msg=new MsgModel();
		try {
			for (Object obj : importModelList) {
				SuLicenseType model=JSON.parseObject(JSONObject.fromObject(obj).toString(), SuLicenseType.class);
				//要么插入，要么更新，没有删除！
				if(getById(model.getLicensetypeid()) != null){
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

}
