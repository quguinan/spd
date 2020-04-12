package com.cms.service.biz.supply.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuBillOrderDoc;
import com.cms.model.biz.supply.SuBillOrderDtl;
import com.cms.model.biz.supply.SuComSupplyV;
import com.cms.model.biz.supply.SuUser;
import com.cms.service.BaseService;
import com.cms.service.biz.supply.ISuBillOrderService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;

@Service
public class SuBillOrderServiceImpl extends BaseService implements ISuBillOrderService {

	@Override
	public Long newDocId() {
		// TODO Auto-generated method stub
		return SuBillOrderDoc.INSTANCE.newId();
	}

	@Override
	public Long newDtlId() {
		// TODO Auto-generated method stub
		return SuBillOrderDtl.INSTANCE.newId();
	}

	@Override
	public int insert(SuBillOrderDoc doc) {
		// TODO Auto-generated method stub
		doc.insert();
		return 1;
	}

	@Override
	public int insert(SuBillOrderDtl dtl) {
		// TODO Auto-generated method stub
		dtl.insert();
		return 1;
	}

	@Override
	public int delete(SuBillOrderDoc doc) {
		// TODO Auto-generated method stub
		return doc.delete();
	}

	@Override
	public int delete(SuBillOrderDtl dtl) {
		// TODO Auto-generated method stub
		return dtl.delete();
	}

	@Override
	public int update(SuBillOrderDoc doc) {
		return doc.update();
	}

	@Override
	public int update(SuBillOrderDtl dtl) {
		return dtl.update();
	}

	@Override
	public List<SuBillOrderDoc> findByOrgid(String orgid) {
		// TODO Auto-generated method stub
		return SuBillOrderDoc.INSTANCE.query(" orgid=? ", orgid);
	}

	@Override
	public List<SuBillOrderDoc> findByOrgidSupplyid(String orgid, String supplyid) {
		// TODO Auto-generated method stub
		return SuBillOrderDoc.INSTANCE.query(" orgid=? and supplyid=? ", orgid, supplyid);
	}

	@Override
	public GridDataModel<SuBillOrderDoc> getGridDataDoc(String docid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!docid.equals("")) {
			filter.append(" and docid = ?");
			params.add(docid);
		}
		GridDataModel<SuBillOrderDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDoc.class, filter.toString(),
				" order by docid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public GridDataModel<SuBillOrderDoc> getGridDataDocByGroupidSupplyid(String groupid, String supplyid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		SuUser suUser = (SuUser) SessionHelpUtils.getSession().getAttribute("suUser");

		String orgids = getOrgidsByGroupid(groupid);
		if (!orgids.equals("")) {
			filter.append(" and orgid in (" + orgids + ")");
		}
		if (!supplyid.equals("")) {
			filter.append(" and supplyid = ?");
			params.add(supplyid);
		}
		GridDataModel<SuBillOrderDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDoc.class, filter.toString(),
				" order by docid,dtlid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public GridDataModel<SuBillOrderDoc> getGridDataDocBySupplyidCredateStatus(String supplyid, String credate1,
			String credate2, String status) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!supplyid.equals("")) {
			filter.append(" and supplyid = ?");
			params.add(supplyid);
		}
		if (!"".equals(credate1) && !"".equals(credate2)) {
			filter.append(" and to_char(credate,'yyyy-mm-dd') >= ? and to_char(credate,'yyyy-mm-dd') <= ?");
			params.add(credate1);
			params.add(credate2);
		}
		if (!status.equals("")) {
			filter.append(" and status = ?");
			params.add(status);
		}
		GridDataModel<SuBillOrderDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDoc.class, filter.toString(),
				" order by docid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public GridDataModel<SuBillOrderDoc> getGridDataDocByGroupidSupplyidCredateStatus(String groupid, String supplyid,
			String credate1, String credate2, String status) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!groupid.equals("")) {
			filter.append(" and orgid in (" + getOrgidsByGroupid(groupid) + ") ");
		}
		if (!supplyid.equals("")) {
			filter.append(" and supplyid = ?");
			params.add(supplyid);
		}
		if (!"".equals(credate1) && !"".equals(credate2)) {
			filter.append(" and to_char(credate,'yyyy-mm-dd') >= ? and to_char(credate,'yyyy-mm-dd') <= ?");
			params.add(credate1);
			params.add(credate2);
		}
		if (!status.equals("")) {
			filter.append(" and status = ?");
			params.add(status);
		}
		GridDataModel<SuBillOrderDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDoc.class, filter.toString(),
				" order by docid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public List<SuBillOrderDtl> findDtlByDocid(String docid) {
		// TODO Auto-generated method stub
		return SuBillOrderDtl.INSTANCE.query(" docid=? ", docid);
	}

	@Override
	public GridDataModel<SuBillOrderDtl> getGridDataDtl(String docid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!docid.equals("")) {
			filter.append(" and docid = ?");
			params.add(docid);
		}
		GridDataModel<SuBillOrderDtl> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDtl.class, filter.toString(),
				" order by dtlid ", params.toArray());
		return gridDataModel;
	}

	@Override
	public List<SuBillOrderDoc> findByOrgidSupplyidSourceid(String orgid, String supplyid, String sourceid) {
		// TODO Auto-generated method stub
		// 作废后可重新上传
		return SuBillOrderDoc.INSTANCE.query(" orgid=? and supplyid=? and sourceid=? and status<>-1", orgid, supplyid,
				sourceid);
	}

	@Override
	public List<SuBillOrderDoc> findByOrgidSupplyidStatus(String orgid, String supplyid, String status) {
		// TODO Auto-generated method stub
		return SuBillOrderDoc.INSTANCE.query(" orgid=? and supplyid=? and status=?", orgid, supplyid, status);
	}

	@Override
	public SuBillOrderDoc getByDocid(String docid) {
		// TODO Auto-generated method stub
		return SuBillOrderDoc.INSTANCE.queryOne(" docid=? ", docid);
	}

	@Override
	public HashMap<String, Object> setStatus(String docid, String status) {
		SuUser suUser = (SuUser) SessionHelpUtils.getSession().getAttribute("suUser");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> result = new HashMap<String, Object>();
		SuBillOrderDoc suBillOrderDoc = getByDocid(docid);// 原状态
		// 单据状态: -1作废,0新订单,1已审核,2已接收
		/*
		 * 审核
		 */
		if ("1".equals(status)) {
			if ("-1".equals(suBillOrderDoc.getStatus())) {
				result.put("msg", "作废单据,不能审核!");
				result.put("success", false);
			} else if ("1".equals(suBillOrderDoc.getStatus())) {
				result.put("msg", "已审核单据,不能再次审核!");
				result.put("success", false);
			} else if ("2".equals(suBillOrderDoc.getStatus()) && "1".equals(suUser.getUsertype())) {// 1 机构用户 2 供应商用户
				// 机构用户接收后不能回退,供应商用户接收后为了可以重新接收,允许回退到审核状态
				result.put("msg", "已接收单据,不能审核!");
				result.put("success", false);
			} else {
				suBillOrderDoc.setStatus("1");
				suBillOrderDoc.setCheckuserid(suUser.getUserid().toString());
				suBillOrderDoc.setCheckdate(sdf.format(date));
				update(suBillOrderDoc);
				result.put("msg", "审核完成!");
				result.put("success", true);
			}
			/*
			 * 取消审核
			 */
		} else if ("0".equals(status)) {//
			if (!"1".equals(suBillOrderDoc.getStatus())) {
				result.put("msg", "只有已审核,才能取消审核!");
				result.put("success", false);
			} else if (!suUser.getUserid().equals(suBillOrderDoc.getCheckuserid())) {
				result.put("msg", "非本人,不能取消审核!");
				result.put("success", false);
			} else {
				suBillOrderDoc.setStatus("0");
				suBillOrderDoc.setCheckuserid("");
				suBillOrderDoc.setCheckdate("");
				update(suBillOrderDoc);
				result.put("msg", "取消审核完成!");
				result.put("success", true);
			}
			/*
			 * 作废
			 */
		} else if ("-1".equals(status)) { // 作废
			if (!"0".equals(suBillOrderDoc.getStatus())) {
				result.put("msg", "只有新单据,才能作废!");
				result.put("success", false);
			} else {
				suBillOrderDoc.setStatus("-1");
				update(suBillOrderDoc);
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
	public GridDataModel<SuBillOrderDoc> findByOrgidSupplyid(String supplyid) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!supplyid.equals("")) {
			filter.append(" and supplyid = ?");
			params.add(supplyid);
		}
		GridDataModel<SuBillOrderDoc> gridDataModel = PageFactoryEasyUI.newPage(SuBillOrderDoc.class, filter.toString(),
				" order by docid ", params.toArray());
		return gridDataModel;
	}

}
