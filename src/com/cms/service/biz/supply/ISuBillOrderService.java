package com.cms.service.biz.supply;

import java.util.HashMap;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuBillOrderDoc;
import com.cms.model.biz.supply.SuBillOrderDtl;

/**
 * 订单业务
 * 
 * @author qgn
 *
 */
public interface ISuBillOrderService {

	// 总单ID
	public Long newDocId();

	// 细单ID
	public Long newDtlId();

	// 增doc
	public int insert(SuBillOrderDoc doc);

	// 增dtl
	public int insert(SuBillOrderDtl dtl);

	// 删doc
	public int delete(SuBillOrderDoc doc);

	// 删dtl
	public int delete(SuBillOrderDtl dtl);

	// 改doc
	public int update(SuBillOrderDoc doc);

	// 改dtl
	public int update(SuBillOrderDtl dtl);

	/**** DOC ***/

	// 查所有doc by orgid
	public SuBillOrderDoc getByDocid(String docid);

	// 查所有doc by orgid
	public List<SuBillOrderDoc> findByOrgid(String orgid);

	// 查所有doc by orgid,supplyid
	public List<SuBillOrderDoc> findByOrgidSupplyid(String orgid, String supplyid);

	// 查所有doc by orgid,supplyid,status
	public List<SuBillOrderDoc> findByOrgidSupplyidStatus(String orgid, String supplyid, final String status);

	// 查所有doc by orgid,sourceid
	public List<SuBillOrderDoc> findByOrgidSupplyidSourceid(String orgid, String supplyid, String sourceid);

	// 根据docid查分页doc
	public GridDataModel<SuBillOrderDoc> getGridDataDoc(String docid);

	// 根据orgid,supplyid查分页doc
	public GridDataModel<SuBillOrderDoc> getGridDataDocByGroupidSupplyid(String groupid, String supplyid);

	// 根据groupid,supplyid,credate,status查分页doc 订单用
	public GridDataModel<SuBillOrderDoc> getGridDataDocByGroupidSupplyidCredateStatus(String groupid, String supplyid,
			String credate1, String credate2, final String status);

	// 根据supplyid,credate,status查分页doc 送货单用
	public GridDataModel<SuBillOrderDoc> getGridDataDocBySupplyidCredateStatus(String supplyid, String credate1,
			String credate2, final String status);

	/**** DTL ***/
	// 根据docid查dtl
	public List<SuBillOrderDtl> findDtlByDocid(String docid);

	// 根据docid查dtl
	public GridDataModel<SuBillOrderDtl> getGridDataDtl(String docid);

	public HashMap<String, Object> setStatus(String docid, String status);

	// 查所有doc supplyid
	public GridDataModel<SuBillOrderDoc> findByOrgidSupplyid(String supplyid);

}
