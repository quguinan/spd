package com.cms.service.biz.supply.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.SupGoods;
import com.cms.service.biz.supply.ISupGoodsServcie;
import com.cms.util.biz.PageFactoryEasyUI;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import my.dao.pool.DBManager;

@Service
public class SupGoodsServcieImpl implements ISupGoodsServcie {

	@Override
	public HashMap<String, Object> ImportGoods(MultipartFile file_upload, String supplyid) {

		HashMap<String, Object> result = new HashMap<String, Object>();

		if (!file_upload.isEmpty()) {
			try {
				Workbook book = Workbook.getWorkbook(file_upload.getInputStream());
				int rowTotals = 0;
				SupGoods goods = new SupGoods();
				Sheet sheet = book.getSheet(0);
				for (int row = 1; row < sheet.getRows(); row++) {
					for (int col = 0; col < sheet.getColumns(); col++) {
						if (sheet.getCell(col, 0).getContents().equals("货品ID")) {
							goods.setSupgoodsid(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("货品名称")) {
							goods.setSupgoodsname(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("货品规格")) {
							goods.setSupgoodstype(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("单位")) {
							goods.setSupgoodsunit(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("厂家/产地")) {
							goods.setSupfactory(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("批准文号")) {
							goods.setSupapprovedocno(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("注册证号")) {
							goods.setSupregistdocno(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("类型")) {
							goods.setGspcategory(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("拼音")) {
							goods.setGoodspinyin(sheet.getCell(col, row).getContents().toUpperCase());
						}
					}
					goods.setOrgid(SupGoods.INSTANCE.newId().toString());
					goods.setSupplyid(supplyid);
					rowTotals++;
					goods.insert();
					DBManager.commitAll();
					result.put("success", true);
					result.put("msg", "保存成功!本次导入" + rowTotals + "条数据");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DBManager.rollbackAll();
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "保存失败!");
			}
		}

		return result;
	}

	@Override
	public GridDataModel<SupGoods> getSupGoos(String goodsname, String spell, String supplyid, String goodsid) {
		// TODO Auto-generated method stub
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" supplyid= " + supplyid);
		if (!goodsname.equals("")) {
			filter.append(" and supgoodsname like ? ");
			params.add("%" + goodsname + "%");
		}

		if (!goodsid.equals("")) {
			filter.append(" and supgoodsid = ? ");
			params.add(goodsid);
		}
		if (!spell.equals("")) {
			filter.append(" and  goodspinyin like ? ");
			params.add("%" + spell.toUpperCase() + "%");
		}

		return PageFactoryEasyUI.newPage(SupGoods.class, filter.toString(), "order by orgid ", params.toArray());
	}

	@Override
	public HashMap<String, Object> save(String json) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SupGoods> insert = model.inserts(SupGoods.class);
		List<SupGoods> update = model.updates(SupGoods.class);
		List<SupGoods> delete = model.deletes(SupGoods.class);
		try {
			for (SupGoods comp : insert) {
				comp.insert();
			}
			for (SupGoods comp : update) {
				comp.update();
			}
			for (SupGoods comp : delete) {
				comp.delete();
			}

			DBManager.commitAll();
			result.put("msg", "操作成功");
			result.put("success", true);
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		}
		return result;
	}

	@Override
	public SupGoods getSupGoodsidByOrggoodsid(String supplyid, String goodsid) {
		// TODO Auto-generated method stub
		return SupGoods.INSTANCE.queryOne("supplyid=? and orggoodsid=?", supplyid, goodsid);
	}

}
