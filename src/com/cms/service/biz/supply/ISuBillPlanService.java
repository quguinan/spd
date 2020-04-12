package com.cms.service.biz.supply;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.PlanBackBak;
import com.cms.model.biz.supply.SuBillPlanDocV;
import com.cms.model.biz.supply.SuBillPlanDtlV;

public interface ISuBillPlanService {

	public GridDataModel<SuBillPlanDocV> findPlanBySupplyid(String supplyid);

	public GridDataModel<SuBillPlanDtlV> findPlanByPlandocid(String plandocid, String supplyid);

	public HashMap<String, Object> savePlanDataDtl(String json, String supplyid);

	public HashMap<String, Object> impExcel(MultipartFile file_upload, String ids, String rows, String supplyid);

	public GridDataModel<PlanBackBak> findAll(String supplyid, String goodsname);

}
