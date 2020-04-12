package com.cms.service.biz.supply.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.supply.SuBillOrderDoc;
import com.cms.model.biz.supply.SuBillOrderDtl;
import com.cms.model.biz.supply.SuBillSendDoc;
import com.cms.model.biz.supply.SuBillSendDtl;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuBillSendService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
import my.util.MD5Util;

@Service
public class SuBillSendServiceImpl extends BaseService implements ISuBillSendService {

	@Override
	public Long newDocId() {
		// TODO Auto-generated method stub
		return SuBillSendDoc.INSTANCE.newId();
	}

	@Override
	public Long newDtlId() {
		// TODO Auto-generated method stub
		return SuBillSendDtl.INSTANCE.newId();
	}

	@Override
	public int insert(SuBillSendDoc doc) {
		// TODO Auto-generated method stub
		doc.insert();
		return 1;
	}

	@Override
	public int insert(SuBillSendDtl dtl) {
		// TODO Auto-generated method stub
		dtl.insert();
		return 1;
	}

	@Override
	public int delete(SuBillSendDoc doc) {
		// TODO Auto-generated method stub
		doc.delete();
		return 1;
	}

	@Override
	public int delete(SuBillSendDtl dtl) {
		// TODO Auto-generated method stub
		dtl.delete();
		return 1;
	}

	@Override
	public int update(SuBillSendDoc doc) {
		// TODO Auto-generated method stub
		doc.update();
		return 1;
	}

	@Override
	public int update(SuBillSendDtl dtl) {
		// TODO Auto-generated method stub
		dtl.update();
		return 1;
	}

	@Override
	public SuBillSendDoc getByDocid(String docid) {
		// TODO Auto-generated method stub
		return SuBillSendDoc.INSTANCE.queryOne(" docid=? ", docid);
	}

	@Override
	public List<SuBillSendDoc> findByOrgid(String orgid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SuBillSendDoc> findByOrgidSupplyid(String orgid, String supplyid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SuBillSendDoc> findByOrgidSupplyidStatus(String orgid, String supplyid, String status) {
		// TODO Auto-generated method stub
		System.out.println(supplyid);
		if ("".equals(supplyid)) {
			return SuBillSendDoc.INSTANCE.query(" orgid=? and status=? ", orgid, status);
		} else {
			return SuBillSendDoc.INSTANCE.query(" orgid=? and supplyid=? and status=? ", orgid, supplyid, status);
		}

	}

	@Override
	public List<SuBillSendDoc> findByOrgidSupplyidSourceid(String orgid, String supplyid, String sourceid) {
		// TODO Auto-generated method stub
		return SuBillSendDoc.INSTANCE.query(" orgid=? and supplyid=? and sourceid=? and status<>-1", orgid, supplyid,
				sourceid);
	}

	@Override
	public GridDataModel<SuBillSendDoc> getGridDataDoc(String docid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridDataModel<SuBillSendDoc> getGridDataDocByGroupidSupplyid(String groupid, String supplyid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridDataModel<SuBillSendDoc> getGridDataDocByGroupidSupplyidCredateStatusIntype(String groupid,
			String supplyid, String credate1, String credate2, String status, String intype) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		if (!intype.equals("")) {
			filter.append(" and intype = ?");
			params.add(intype);
		}

		if (!groupid.equals("")) {
			filter.append(" and orgid in (" + getOrgidsByGroupid(groupid) + ") ");
		}
		if (!supplyid.equals("")) {
			filter.append(" and supplyid = ?");
			params.add(supplyid);
		}
		if (!credate1.equals("") && !credate2.equals("")) {
			filter.append(" and to_char(credate,'yyyy-mm-dd') >= ? and to_char(credate,'yyyy-mm-dd') <= ? ");
			params.add(credate1);
			params.add(credate2);
		}
		if (!status.equals("")) {
			filter.append(" and status = ?");
			params.add(status);
		}
		GridDataModel<SuBillSendDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillSendDoc.class, filter.toString(),
				" order by docid desc ", params.toArray());

		return gridDataModel;
	}

	@Override
	public List<SuBillSendDtl> findDtlByDocid(String docid) {
		// TODO Auto-generated method stub
		return SuBillSendDtl.INSTANCE.query(" docid=? ", docid);
	}

	@Override
	public GridDataModel<SuBillSendDtl> getGridDataDtl(String docid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid = ?");
		params.add(docid);
		GridDataModel<SuBillSendDtl> gridDataModel = PageFactoryEasyUI.newPage(SuBillSendDtl.class, filter.toString(),
				" order by docid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public HashMap<String, Object> setStatus(String docid, String status) {
		SuUser suUser = (SuUser) SessionHelpUtils.getSession().getAttribute("suUser");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> result = new HashMap<String, Object>();
		SuBillSendDoc suBillSendDoc = getByDocid(docid);// 原状态
		// 单据状态: -1作废,0新订单,1已审核,2已接收
		/*
		 * 审核
		 */
		if ("1".equals(status)) {
			if ("-1".equals(suBillSendDoc.getStatus())) {
				result.put("msg", "作废单据,不能审核!");
				result.put("success", false);
			} else if ("1".equals(suBillSendDoc.getStatus())) {
				result.put("msg", "已审核单据,不能再次审核!");
				result.put("success", false);
			} else if ("2".equals(suBillSendDoc.getStatus()) && "2".equals(suUser.getUsertype())) {// 1 机构用户 2 供应商用户
				// 供应商用户接收后不能回退,机构用户接收后为了可以重新接收,允许回退到审核状态
				result.put("msg", "已接收单据,不能审核!");
				result.put("success", false);
			} else {
				suBillSendDoc.setStatus("1");
				suBillSendDoc.setCheckuserid(suUser.getUserid().toString());
				suBillSendDoc.setCheckdate(sdf.format(date));
				update(suBillSendDoc);
				result.put("msg", "审核完成!");
				result.put("success", true);
			}
			/*
			 * 取消审核
			 */
		} else if ("0".equals(status)) {//
			if (!"1".equals(suBillSendDoc.getStatus())) {
				result.put("msg", "只有已审核,才能取消审核!");
				result.put("success", false);
			} else if (!suUser.getUserid().equals(suBillSendDoc.getCheckuserid())) {
				result.put("msg", "非本人,不能取消审核!");
				result.put("success", false);
			} else {
				suBillSendDoc.setStatus("0");
				suBillSendDoc.setCheckuserid("");
				suBillSendDoc.setCheckdate("");
				update(suBillSendDoc);
				result.put("msg", "取消审核完成!");
				result.put("success", true);
			}
			/*
			 * 作废
			 */
		} else if ("-1".equals(status)) { // 作废
			if (!"0".equals(suBillSendDoc.getStatus())) {
				result.put("msg", "只有新单据,才能作废!");
				result.put("success", false);
			} else {
				suBillSendDoc.setStatus("-1");
				update(suBillSendDoc);
				result.put("msg", "单据作废完成!");
				result.put("success", true);
			}
			/*
			 * 不识别状态
			 */
		} else {
			result.put("msg", "不识别状态[" + status + "]!");
			result.put("success", false);
		}
		DBManager.commitAll();
		return result;
	}

	@Override
	public HashMap<String, Object> saveSuBillSendDoc(String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuBillSendDoc> insert = model.inserts(SuBillSendDoc.class);
		List<SuBillSendDoc> delete = model.deletes(SuBillSendDoc.class);
		List<SuBillSendDoc> update = model.updates(SuBillSendDoc.class);
		try {
			for (SuBillSendDoc comp : delete) {
				comp.delete();
			}
			for (SuBillSendDoc comp : update) {
				comp.update();
			}
			for (SuBillSendDoc comp : insert) {
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);

		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		} finally {

		}
		return result;
	}

	@Override
	public HashMap<String, Object> saveSuBillSendDtl(String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SuBillSendDtl> insert = model.inserts(SuBillSendDtl.class);
		List<SuBillSendDtl> delete = model.deletes(SuBillSendDtl.class);
		List<SuBillSendDtl> update = model.updates(SuBillSendDtl.class);
		try {
			for (SuBillSendDtl comp : delete) {
				comp.delete();
			}
			for (SuBillSendDtl comp : update) {
				comp.update();
			}
			for (SuBillSendDtl comp : insert) {
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);

		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		} finally {

		}
		return result;
	}

	@Override
	public HashMap<String, Object> createSendbillByOrderbill(String OrgbillDocid) {
		SuUser suUser = (SuUser) SessionHelpUtils.getSession().getAttribute("suUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			SuBillOrderDoc orderDoc = SuBillOrderDoc.INSTANCE.queryOne("docid=?", OrgbillDocid);
			List<SuBillOrderDtl> orderDtls = SuBillOrderDtl.INSTANCE.query("docid=?", OrgbillDocid);
			// Sendbill总单
			SuBillSendDoc SendDoc = new SuBillSendDoc();
			String docid = SendDoc.newId().toString();
			SendDoc.setDocid(docid);
			SendDoc.setDocno("SEND" + sdf2.format(date) + "-" + docid);
			SendDoc.setOrgid(orderDoc.getOrgid());
			SendDoc.setSupplyid(orderDoc.getSupplyid());
			SendDoc.setIntype("3"); // 手工录入
			SendDoc.setSendtype(orderDoc.getOrdtype());
			SendDoc.setCheckuserid("");
			SendDoc.setCheckdate("");
			SendDoc.setSendaddress("");
			SendDoc.setSenddatetime("");
			SendDoc.setMemo("");
			SendDoc.setStatus("0");
			SendDoc.setCredate(sdf.format(date));
			SendDoc.setUserid(suUser.getUserid());
			SendDoc.setStorerid(orderDoc.getStorerid());
			SendDoc.setOrderid(orderDoc.getOrderid());
			SendDoc.setSourceid("");
			SendDoc.setSendid(docid);// 手工录入默认同docid
			SendDoc.insert();
			// Sendbill细单
			for (SuBillOrderDtl orderDtl : orderDtls) {
				SuBillSendDtl SendDtl = new SuBillSendDtl();
				String dtlid = SendDtl.newId().toString();
				SendDtl.setDtlid(dtlid);
				SendDtl.setDocid(docid);
				SendDtl.setSortno(orderDtl.getSortno());
				SendDtl.setSugoodsid("");
				SendDtl.setGoodsid(orderDtl.getGoodsid());
				SendDtl.setSendqty(orderDtl.getOrderqty());
				SendDtl.setRecieveqty("");
				SendDtl.setUnit(orderDtl.getUnit());
				SendDtl.setUnitprice(orderDtl.getUnitprice());
				SendDtl.setTotal(orderDtl.getTotal());
				SendDtl.setMemo(orderDtl.getMemo());
				SendDtl.setBatchno("");
				SendDtl.setExpirydate("");
				SendDtl.setDtlstatus("0");// 明细状态:-1退回,0初始,1全部接收,2部分接收,3拒绝接收.
				SendDtl.setOrderdtlid(orderDtl.getOrderdtlid());
				SendDtl.setSenddtlid(dtlid);// 手工录入默认同dtlid
				SendDtl.insert();
			}
			// 原订单状态改成2已处理
			orderDoc.setStatus("2");
			orderDoc.update();
			// 提交
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:" + e.getMessage());
		}

		return result;
	}

}
