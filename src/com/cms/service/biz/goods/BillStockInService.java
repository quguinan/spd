package com.cms.service.biz.goods;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsBillIn;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsLot;
import com.cms.model.biz.goods.GoodsStockPool;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.stock.StockService;
import com.cms.util.biz.ConstantsUtil;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月15日 上午9:45:23
 * 
 * @descriptions 类说明：入库单相关操作
 */
@Service
public class BillStockInService {
	@Autowired
	private StockService stockService;
	/**
	 * 根据id,获取doc的对象数据
	 */
	public GoodsBillIn getBillGoodsInByDocid(String docid){
		GoodsBillIn billGoodsIn=GoodsBillIn.INSTANCE.queryOne("docid=?", docid);
		return billGoodsIn;
	}
	/**
	 * 根据docid,获取dtl的dg数据
	 */
	public GridDataModel<GoodsBillInDtl> gridDataDtlByDocid(String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid=?");
		params.add(docid);
		GridDataModel<GoodsBillInDtl> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillInDtl.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 根据credate,获取doc的dg数据
	 */
	public GridDataModel<GoodsBillIn> gridDataDocBycredate(String credate){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!credate.equals("")){
			filter.append(" and to_char(createdate,'yyyy-mm-dd')=?");
			params.add(credate);
		}
		GridDataModel<GoodsBillIn> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillIn.class, filter.toString()," order by docid desc ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 设置主单状态
	 */
	public  int setBillGoodsInDocStatus(String docid,String status){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillIn billGoodsIn=GoodsBillIn.INSTANCE.queryOne("docid=?", docid);
		try {
			if(status.equals(ConstantsUtil.IN_STATUS_C)){
				billGoodsIn.setCheckerid(user.getUserid());
				billGoodsIn.setCheckdate(sdf.format(date));
			}
			billGoodsIn.setStatus(status);
			billGoodsIn.update();
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 新增单据 采购入库
	 * @param billGoodsIn 总单
	 * @param inserts 细单新增数据
	 * @return  1 正常  
	 * 			-1其他问题
	 */
	public int addCGRK(GoodsBillIn billGoodsIn,List<GoodsBillInDtl> inserts){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			//总单
			String docid=""+billGoodsIn.newId();
			billGoodsIn.setDocid(docid);
			billGoodsIn.setDocno(ConstantsUtil.IN_TYPE_CGRK+sdf2.format(date)+"-"+docid);
			billGoodsIn.setBilltype(ConstantsUtil.IN_TYPE_CGRK);
			billGoodsIn.setBillname(ConstantsUtil.IN_TYPE_CGRK_TEXT);
			//billGoodsIn.setStoreid(storeid);
			//billGoodsIn.setSupplyid(supplyid);
			//billGoodsIn.setCost(cost);
			billGoodsIn.setCreatedate(sdf.format(date));
			billGoodsIn.setCreaterid(user.getUserid());
			//billGoodsIn.setCheckerid(checkerid);
			//billGoodsIn.setCheckdate(checkdate);
			//billGoodsIn.setOpid(opid);
			//billGoodsIn.setOpdate(opdate);
			billGoodsIn.setStatus(ConstantsUtil.IN_STATUS_N);//状态:N临时C已审核D已处理F作废
			//billGoodsIn.setMemo(memo);
			billGoodsIn.insert();
			
			//细单表和批次表
			for(GoodsBillInDtl billGoodsInDtl:inserts){
				GoodsLot goodsLot=new GoodsLot();
				String lotid=goodsLot.newId().toString();
				String dtlid=billGoodsInDtl.newId().toString();
				//批次表
				goodsLot.setLotid(lotid);
				goodsLot.setBilltable(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL); 
				goodsLot.setBilldtlid(dtlid);
				goodsLot.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsLot.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsLot.setPurprice(billGoodsInDtl.getPurprice());
				goodsLot.setDtlprice(billGoodsInDtl.getDtlprice());
				goodsLot.setMemo(billGoodsInDtl.getMemo());
				goodsLot.setCreatedate(sdf.format(date));
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.insert();
				//业务表
				billGoodsInDtl.setLotid(lotid);
				billGoodsInDtl.setDtlid(dtlid);
				billGoodsInDtl.setDocid(docid);
				billGoodsInDtl.insert();
				
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		
	}
	/**
	 * 新增单据 采购退货
	 * @param billGoodsIn 总单
	 * @param inserts 细单新增数据
	 * @return  1 正常  
	 * 			-2退货数量必须小于零  
	 * 			-3校验退货数量不能大于库存 
	 * 			-1其他问题
	 */
	public int addCGTH(GoodsBillIn billGoodsIn,List<GoodsBillInDtl> inserts){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			//总单
			String docid=""+billGoodsIn.newId();
			billGoodsIn.setDocid(docid);
			billGoodsIn.setDocno(ConstantsUtil.IN_TYPE_CGTH+sdf2.format(date)+"-"+docid);
			billGoodsIn.setBilltype(ConstantsUtil.IN_TYPE_CGTH);
			billGoodsIn.setBillname(ConstantsUtil.IN_TYPE_CGTH_TEXT);
			//billGoodsIn.setStoreid(storeid);
			//billGoodsIn.setSupplyid(supplyid);
			//billGoodsIn.setCost(cost);
			billGoodsIn.setCreatedate(sdf.format(date));
			billGoodsIn.setCreaterid(user.getUserid());
			//billGoodsIn.setCheckerid(checkerid);
			//billGoodsIn.setCheckdate(checkdate);
			//billGoodsIn.setOpid(opid);
			//billGoodsIn.setOpdate(opdate);
			billGoodsIn.setStatus(ConstantsUtil.IN_STATUS_N);//状态:N临时C已审核D已处理F作废
			//billGoodsIn.setMemo(memo);
			billGoodsIn.insert();
			for (GoodsBillInDtl billGoodsInDtl : inserts) {
				/* 校验数量必须小于零*/
				if(billGoodsInDtl.getQty().compareTo(BigDecimal.ZERO)>0){
					return -2;
				}
				/* 校验退货数量不能大于库存*/
				if(billGoodsInDtl.getQty().abs().compareTo(billGoodsInDtl.getStockqty())>0){
					return -3;
				}
			}
			
			//细单表和批次表
			for(GoodsBillInDtl billGoodsInDtl:inserts){
				GoodsLot goodsLot=new GoodsLot();
				String lotid=goodsLot.newId().toString();
				String dtlid=billGoodsInDtl.newId().toString();
				
				//批次表
				goodsLot.setLotid(lotid);
				goodsLot.setBilltable(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL); 
				goodsLot.setBilldtlid(dtlid);
				goodsLot.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsLot.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsLot.setPurprice(billGoodsInDtl.getPurprice());
				goodsLot.setDtlprice(billGoodsInDtl.getDtlprice());
				goodsLot.setMemo(billGoodsInDtl.getMemo());
				goodsLot.setCreatedate(sdf.format(date));
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.insert();
				//业务表
				billGoodsInDtl.setLotid(lotid);
				billGoodsInDtl.setDtlid(dtlid);
				billGoodsInDtl.setDocid(docid);
				billGoodsInDtl.insert();
				
				//库存缓冲
				GoodsStockPool goodsStockPool=new GoodsStockPool();
				goodsStockPool.setTablename(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL);
				goodsStockPool.setDtlid(dtlid);
				goodsStockPool.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsStockPool.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsStockPool.setStockid(billGoodsInDtl.getStockid());//
				goodsStockPool.insert();
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		
	}
	/**
	 * 更新单据 采购入库
	 * @param billGoodsIn 总单
	 * @param updates 细单更新数据
	 * @param deletes 细单删除数据
	 * @return  1 正常   -1其他问题
	 * 			
	 */
	public int updateCGRK(GoodsBillIn billGoodsIn,GridSaveModel model){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			//总单
			billGoodsIn.update();
			//细单
			List<GoodsBillInDtl> deletes = model.deletes(GoodsBillInDtl.class);
			List<GoodsBillInDtl> updates = model.updates(GoodsBillInDtl.class);
			List<GoodsBillInDtl> inserts = model.inserts(GoodsBillInDtl.class);
			
			for(GoodsBillInDtl billGoodsInDtl:deletes){
				GoodsLot goodsLot=GoodsLot.INSTANCE.queryOne("lotid=?", billGoodsInDtl.getLotid());
				goodsLot.delete();
				billGoodsInDtl.delete();
			}
			for(GoodsBillInDtl billGoodsInDtl:updates){
				GoodsLot goodsLot=GoodsLot.INSTANCE.queryOne("lotid=?", billGoodsInDtl.getLotid());
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.update();
				billGoodsInDtl.update();
			}
			for(GoodsBillInDtl billGoodsInDtl:inserts){
				GoodsLot goodsLot=new GoodsLot();
				String lotid=goodsLot.newId().toString();
				String dtlid=billGoodsInDtl.newId().toString();
				//批次表
				goodsLot.setLotid(lotid);
				goodsLot.setBilltable(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL); //产生批次的业务表
				goodsLot.setBilldtlid(dtlid);
				goodsLot.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsLot.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsLot.setPurprice(billGoodsInDtl.getPurprice());
				goodsLot.setDtlprice(billGoodsInDtl.getDtlprice());
				goodsLot.setMemo(billGoodsInDtl.getMemo());
				goodsLot.setCreatedate(sdf.format(date));
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.insert();
				//业务表
				billGoodsInDtl.setLotid(lotid);
				billGoodsInDtl.setDtlid(dtlid);
				billGoodsInDtl.setDocid(billGoodsIn.getDocid());
				billGoodsInDtl.insert();
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		
		
	}
	/**
	 * 更新单据 采购退货
	 * @param billGoodsIn 总单
	 * @param updates 细单更新数据
	 * @param deletes 细单删除数据
	 * @return  1 正常  
	 * 			-2退货数量必须小于零  
	 * 			-3校验退货数量不能大于库存 
	 * 			-1其他问题
	 */
	public int updateCGTH(GoodsBillIn billGoodsIn,GridSaveModel model ){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		
		try {
			//总单
			billGoodsIn.update();
			/* 细单 */
			List<GoodsBillInDtl> delete = model.deletes(GoodsBillInDtl.class);
			List<GoodsBillInDtl> update = model.updates(GoodsBillInDtl.class);
			List<GoodsBillInDtl> insert = model.inserts(GoodsBillInDtl.class);
			for(GoodsBillInDtl billGoodsInDtl:delete){
				GoodsLot goodsLot=GoodsLot.INSTANCE.queryOne("lotid=?", billGoodsInDtl.getLotid());
				goodsLot.delete();
				billGoodsInDtl.delete();
				//清除缓冲
//				GoodsStockPool goodsStockPool=GoodsStockPool.INSTANCE.queryOne("tablename=? and dtlid=?", "BILL_GOODS_IN_DTL",billGoodsInDtl.getDtlid());
//				goodsStockPool.delete();
			}
			for(GoodsBillInDtl billGoodsInDtl:update){
				/* 校验数量必须小于零*/
				if(billGoodsInDtl.getQty().compareTo(BigDecimal.ZERO)>0){
					return -2;
				}
				/* 校验退货数量不能大于库存*/
				if(billGoodsInDtl.getQty().abs().compareTo(billGoodsInDtl.getStockqty())>0){
					return -3;
				}
				GoodsLot goodsLot=GoodsLot.INSTANCE.queryOne("lotid=?", billGoodsInDtl.getLotid());
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.update();
				billGoodsInDtl.update();
			}
			for(GoodsBillInDtl billGoodsInDtl:insert){
				GoodsLot goodsLot=new GoodsLot();
				String lotid=goodsLot.newId().toString();
				String dtlid=billGoodsInDtl.newId().toString();
				
				//批次表
				goodsLot.setLotid(lotid);
				goodsLot.setBilltable(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL); 
				goodsLot.setBilldtlid(dtlid);
				goodsLot.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsLot.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsLot.setPurprice(billGoodsInDtl.getPurprice());
				goodsLot.setDtlprice(billGoodsInDtl.getDtlprice());
				goodsLot.setMemo(billGoodsInDtl.getMemo());
				goodsLot.setCreatedate(sdf.format(date));
				goodsLot.setBatchno(billGoodsInDtl.getBatchno());
				goodsLot.setProdate(billGoodsInDtl.getProdate());
				goodsLot.setExpiredate(billGoodsInDtl.getExpiredate());
				goodsLot.insert();
				//业务表
				billGoodsInDtl.setLotid(lotid);
				billGoodsInDtl.setDtlid(dtlid);
				billGoodsInDtl.setDocid(billGoodsIn.getDocid());
				billGoodsInDtl.insert();
				
				//库存缓冲
				GoodsStockPool goodsStockPool=new GoodsStockPool();
				goodsStockPool.setTablename(ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL);
				goodsStockPool.setDtlid(dtlid);
				goodsStockPool.setGoodsid(billGoodsInDtl.getGoodsid());
				goodsStockPool.setGoodsdtlid(billGoodsInDtl.getGoodsdtlid());
				goodsStockPool.setStockid(billGoodsInDtl.getStockid());//
				goodsStockPool.insert();
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 已审核(C)单据入库
	 */
	public int billGoodsInstock(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			GoodsBillIn billGoodsIn=getBillGoodsInByDocid(docid);
			//总单
			billGoodsIn.setStatus(ConstantsUtil.IN_STATUS_D);//状态:N临时C已审核D已处理F作废
			billGoodsIn.setOpdate(sdf.format(date));
			billGoodsIn.setOpid(user.getUserid());
			billGoodsIn.update();
			//细单
			List<GoodsBillInDtl> list=GoodsBillInDtl.INSTANCE.query("docid=?", docid);
			for (GoodsBillInDtl billGoodsInDtl : list) {
				if (stockService.goodsInStock(
							 billGoodsIn.getStoreid(), 
							 billGoodsInDtl.getGoodsid(), 
							 billGoodsInDtl.getGoodsdtlid(), 
							 billGoodsInDtl.getLotid(), 
							 "", 
							 billGoodsInDtl.getQty(), 
							 billGoodsInDtl.getDtlid(), 
							 billGoodsIn.getBilltype(), 
							 ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL,
							 ConstantsUtil.IN_TYPE_CGRK_TEXT)<0) {
					throw new Exception("生成库存失败！");
				}
			}
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
	}
	/**
	 * 已审核(C)单据退库(退供应商)
	 */
	public int billGoodsRefundstock(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			GoodsBillIn billGoodsIn=getBillGoodsInByDocid(docid);
			//总单
			billGoodsIn.setStatus(ConstantsUtil.IN_STATUS_D);//状态:N临时C已审核D已处理F作废
			billGoodsIn.setOpdate(sdf.format(date));
			billGoodsIn.setOpid(user.getUserid());
			billGoodsIn.update();
			//细单
			List<GoodsBillInDtl> list=GoodsBillInDtl.INSTANCE.query("docid=?", docid);
			for (GoodsBillInDtl billGoodsInDtl : list) {
				if (stockService.goodsOutStock(
							 billGoodsInDtl.getStockid(),
							 billGoodsIn.getStoreid(), 
							 billGoodsInDtl.getQty(), 
							 billGoodsInDtl.getDtlid(), 
							 billGoodsIn.getBilltype(), 
							 ConstantsUtil.TABLENAME_GOODS_BILL_IN_DTL,
							 ConstantsUtil.IN_TYPE_CGTH_TEXT)<0) {
					throw new Exception("减库存失败！");
				}
			}
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
	}
	

}
