package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.PlanBackBak;
import com.cms.model.biz.supply.SuBillPlanDocV;
import com.cms.model.biz.supply.SuBillPlanDtlV;
import com.cms.pubsrv.DBHelper;
import com.cms.service.biz.supply.ISuBillPlanService;
import com.cms.util.biz.PageFactoryEasyUI;

import jxl.Sheet;
import jxl.Workbook;
import my.dao.pool.DBManager;

@Service
public class SuBillPlanServiceImpl implements ISuBillPlanService {

	@Override
	public GridDataModel<SuBillPlanDocV> findPlanBySupplyid(String supplyid) {
		// TODO Auto-generated method stub
		StringBuffer filter = new StringBuffer();
		List<String> params = new ArrayList<String>();
		filter.append("supplyid=?");
		params.add(supplyid);

		GridDataModel<SuBillPlanDocV> model = PageFactoryEasyUI.newPage(SuBillPlanDocV.class, filter.toString(), "",
				params.toArray());
		return model;
	}

	@Override
	public GridDataModel<SuBillPlanDtlV> findPlanByPlandocid(String plandocid, String supplyid) {
		// TODO Auto-generated method stub
		StringBuffer filter = new StringBuffer();
		List<String> params = new ArrayList<String>();
		filter.append(" and plandocid=?");
		filter.append(" and supplyid=?");
		params.add(plandocid);
		params.add(supplyid);
		GridDataModel<SuBillPlanDtlV> model = PageFactoryEasyUI.newPage(SuBillPlanDtlV.class, filter.toString(), "",
				params.toArray());
		return model;
	}

	@Override
	public HashMap<String, Object> savePlanDataDtl(String json, String supplyid) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuBillPlanDtlV> updated = model.updates(SuBillPlanDtlV.class);
		PlanBackBak back = new PlanBackBak();
		try {
			for (SuBillPlanDtlV comp : updated) {
				comp.setBackflag("1");
				comp.setBackdate(DBHelper.getSysdate());
				// 备份
				back.setPlanid(comp.getPlanid());
				back.setSupbackprice(comp.getBackprice());
				back.setSupbackqty(comp.getBackqty());
				back.setPlandocid(comp.getPlandocid());
				back.setOrggoodsid(comp.getGoodsid());
				back.setOrggoodsname(comp.getGoodsname());
				back.setOrggoodstype(comp.getGoodstype());
				back.setOrggoodsfactory(comp.getFactoryname());
				back.setSupgoodsid(comp.getSupgoodsid());
				back.setSuprice(comp.getUnitprice());
				back.setOrggoodsunit(comp.getGoodsunit());
				back.setSugoodsqty(comp.getGoodsqty());
				back.setCreatedate(DBHelper.getSysdate());
				back.setBakid(PlanBackBak.INSTANCE.newId().toString());
				back.setSupplyid(supplyid);
				comp.update();
				back.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		}
		return result;
	}

	@Override
	public HashMap<String, Object> impExcel(MultipartFile file_upload, String ids, String rows, String supplyid) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (!file_upload.isEmpty()) {
			try {
				Workbook book = Workbook.getWorkbook(file_upload.getInputStream());
				SuBillPlanDtlV dtl = new SuBillPlanDtlV();
				PlanBackBak back = new PlanBackBak();
				Sheet sheet = book.getSheet(0);
				int rowTotals = 0;
				if (Integer.parseInt(rows) < sheet.getRows() - 1) {
					throw new Exception("上传的数据行不能大于需要反馈的细单行数");
				}
				for (int row = 1; row < sheet.getRows(); row++) {
					for (int col = 0; col < sheet.getColumns(); col++) {
						if (sheet.getCell(col, 0).getContents().equals("计划细单ID")) {
							dtl.setPlanid(sheet.getCell(col, row).getContents());
							back.setPlanid(dtl.getPlanid());
						}
						if (sheet.getCell(col, 0).getContents().equals("反馈单价")) {

							dtl.setBackprice(sheet.getCell(col, row).getContents());
							if (dtl.getBackprice().equals("")) {
								throw new Exception("第" + row + " 行，反馈单价不能为空,请检查！！");
							}
							back.setSupbackprice(dtl.getBackprice());
						}
						if (sheet.getCell(col, 0).getContents().equals("反馈数量")) {
							dtl.setBackqty(sheet.getCell(col, row).getContents());
							if (dtl.getBackqty().equals("")) {
								throw new Exception("第" + row + "行，反馈数量不能为空,请检查！！");
							}
							back.setSupbackqty(dtl.getBackqty());
						}
						// 备份
						if (sheet.getCell(col, 0).getContents().equals("总单ID")) {
							back.setPlandocid(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("平台货品ID")) {
							back.setOrggoodsid(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("货品名称")) {
							back.setOrggoodsname(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("规格")) {
							back.setOrggoodstype(sheet.getCell(col, row).getContents());

						}
						if (sheet.getCell(col, 0).getContents().equals("生产厂家")) {
							back.setOrggoodsfactory(sheet.getCell(col, row).getContents());

						}
						if (sheet.getCell(col, 0).getContents().equals("我的货品ID")) {
							back.setSupgoodsid(sheet.getCell(col, row).getContents());

						}
						if (sheet.getCell(col, 0).getContents().equals("采购单价")) {
							back.setSuprice(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("采购单位")) {
							back.setOrggoodsunit(sheet.getCell(col, row).getContents());

						}
						if (sheet.getCell(col, 0).getContents().equals("采购数量")) {
							back.setSugoodsqty(sheet.getCell(col, row).getContents());

						}

					}
					if (!ids.contains(dtl.getPlanid())) {
						System.out.println(dtl.getPlanid());
						throw new Exception("上传的数据行中计划西单ID为" + dtl.getPlanid() + "在细单中未找到");
					}
					dtl.setBackdate(DBHelper.getSysdate());
					dtl.setBackflag("1");
					rowTotals++;
					dtl.update();
					back.setCreatedate(DBHelper.getSysdate());
					back.setBakid(PlanBackBak.INSTANCE.newId().toString());
					back.setSupplyid(supplyid);
					back.insert();
				}
				DBManager.commitAll();
				result.put("msg", "保存成功!本次反馈共" + rowTotals + "条数据");
				result.put("success", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DBManager.rollbackAll();
				result.put("msg", "发生异常:" + e.getMessage());
				result.put("success", false);
			}
		}

		return result;
	}

	@Override
	public GridDataModel<PlanBackBak> findAll(String supplyid, String goodsname) {
		// TODO Auto-generated method stub
		StringBuffer filter = new StringBuffer();
		List<String> params = new ArrayList<String>();
		filter.append("supplyid=?");
		params.add(supplyid);
		if (!goodsname.equals("")) {
			filter.append(" and orggoodsname like ? ");
			params.add("%" + goodsname + "%");
		}

		GridDataModel<PlanBackBak> model = PageFactoryEasyUI.newPage(PlanBackBak.class, filter.toString(), "",
				params.toArray());
		return model;
	}
}
