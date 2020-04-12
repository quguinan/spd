package com.cms.service.biz.supply;

import java.util.HashMap;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuBillSendDoc;
import com.cms.model.biz.supply.SuBillSendDtl;

/**
 * 送货单业务
 * @author qgn
 *
 */
public interface ISuBillSendService {
		//总单ID
		public Long newDocId();
		
		//细单ID
		public Long newDtlId();
		
		//增doc
		public int insert(SuBillSendDoc doc);
		
		//增dtl
		public int insert(SuBillSendDtl dtl);
		
		//删doc
		public int delete(SuBillSendDoc doc);
		
		//删dtl
		public int delete(SuBillSendDtl dtl);
		
		//改doc
		public int update(SuBillSendDoc doc);
		
		//改dtl
		public int update(SuBillSendDtl dtl);
		
		/****DOC***/

		//查所有doc by orgid
		public SuBillSendDoc getByDocid(String docid);
		
		//查所有doc by orgid
		public List<SuBillSendDoc> findByOrgid(String orgid);
		
		//查所有doc by orgid,supplyid
		public List<SuBillSendDoc> findByOrgidSupplyid(String orgid,String supplyid);
		
		//查所有doc by orgid,supplyid,status
		public List<SuBillSendDoc> findByOrgidSupplyidStatus(String orgid,String supplyid,final String status);
			
		//查所有doc by orgid,sourceid
		public List<SuBillSendDoc> findByOrgidSupplyidSourceid(String orgid,String supplyid,String sourceid);
			
		//根据docid查分页doc
		public GridDataModel<SuBillSendDoc> getGridDataDoc(String docid);
		
		//根据orgid,supplyid查分页doc
		public GridDataModel<SuBillSendDoc> getGridDataDocByGroupidSupplyid(String groupid, String supplyid);
		
		/**
		 * 根据orgid,supplyid,credate,status,intype查分页doc
		 * @param groupid  orgid分组id
		 * @param supplyid  供应商id
		 * @param credate1  起始时间
		 * @param credate2 结束时间
		 * @param status  单据状态: -1作废,0新订单,1审核,2已接收
		 * @param intype  数据来源:1接口2导入3录入
		 * @return
		 */
		public GridDataModel<SuBillSendDoc> getGridDataDocByGroupidSupplyidCredateStatusIntype(String groupid, String supplyid,String credate1,String credate2,final String status,String intype);
		/**
		 * 创建并保存送货单Sendbill  通过订单Orderbill的docid
		 * @param SendbillDocid
		 * @return
		 */
		public HashMap<String, Object> createSendbillByOrderbill(String OrgbillDocid);
		/****DTL***/	
		//根据docid查dtl
		public List<SuBillSendDtl> findDtlByDocid(String docid);
		
		//根据docid查dtl
		public GridDataModel<SuBillSendDtl> getGridDataDtl(String docid);
		
		public HashMap<String, Object> setStatus(String docid,String status);

		public HashMap<String, Object> saveSuBillSendDoc(String json);
		
		public HashMap<String, Object> saveSuBillSendDtl(String json);
		
		
		
		

}
