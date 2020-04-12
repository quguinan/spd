package com.cms.service.biz.goods.stockCheck;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;
import com.cms.model.biz.goods.GoodsLot;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.ConstantsUtil;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
import my.dao.utils.Record;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月24日 下午9:03:25 
 * 
 *@descriptions 类说明：盘点单相关操作
 */
@Service
public class StockCheckServiceImpl implements StockCheckService{
	
	/**
	 * 查找当前<新建>或<审核>的总单,by docid
	 */
	@Override
	public GoodsStockCheck getUnfinishedDocid(String storeid){
		GoodsStockCheck billGoodsStockCheck=GoodsStockCheck.INSTANCE.queryOne("status in ('N','C') and storeid=?",storeid);
		if(billGoodsStockCheck==null){
			return null;
		}else{
			return billGoodsStockCheck;
		}
	}
	/**
	 * 根据docid,获取盘点合计Sum的dg数据
	 */
	@Override
	public GridDataModel<GoodsStockCheckSum> gridDataSumByDocid(String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid=?");
		params.add(docid);
		GridDataModel<GoodsStockCheckSum> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockCheckSum.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 根据docid,获取盘点明细Dtl的dg数据
	 */
	@Override
	public GridDataModel<GoodsStockCheckDtl> gridDataDtlByDocid(String goodsid,String goodsdtlid,String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and goodsid=? and goodsdtlid=? and docid=?");
		params.add(goodsid);
		params.add(goodsdtlid);
		params.add(docid);
		GridDataModel<GoodsStockCheckDtl> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockCheckDtl.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 盘点开始,由库存生成盘点单
	 */
	@Override
	public int checkStart(String storeid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			/*
			 * 总单
			 */
			GoodsStockCheck billGoodsStockCheck=new GoodsStockCheck();
			String docid=billGoodsStockCheck.newId().toString();
			billGoodsStockCheck.setDocid(docid);
			billGoodsStockCheck.setDocno(ConstantsUtil.STOCK_CHECK_TYPE_KFPD+sdf2.format(date)+"-"+docid);
			billGoodsStockCheck.setBilltype(ConstantsUtil.STOCK_CHECK_TYPE_KFPD);
			billGoodsStockCheck.setBillname(ConstantsUtil.STOCK_CHECK_TYPE_KFPD_TEXT); 
			billGoodsStockCheck.setStoreid(storeid);
			billGoodsStockCheck.setBegindate(sdf.format(date));
			//billGoodsStockCheck.setEnddate(enddate);
			billGoodsStockCheck.setCreaterid(user.getUserid()); 
			//billGoodsStockCheck.setCheckerid("");
			//billGoodsStockCheck.setCheckdate("");
			billGoodsStockCheck.setMemo(""); 
			billGoodsStockCheck.setStatus(ConstantsUtil.STOCK_CHECK_STATUS_N);//N临时  F作废(取消) C已审  D已完成
			billGoodsStockCheck.insert();
			/*
			 * 细单汇总
			 */
			List<GoodsStockSumV> listSum=GoodsStockSumV.INSTANCE.query("storeid=? order by posid,goodsname", storeid);
			for (int i = 0; i < listSum.size(); i++) {
				GoodsStockCheckSum billGoodsStockCheckSum=new GoodsStockCheckSum();
				billGoodsStockCheckSum.setSumid(billGoodsStockCheckSum.newId().toString()); 
				billGoodsStockCheckSum.setDocid(docid); 
				billGoodsStockCheckSum.setRowno(""+i);  
				billGoodsStockCheckSum.setGoodsid(listSum.get(i).getGoodsid());  
				billGoodsStockCheckSum.setGoodsdtlid(listSum.get(i).getGoodsdtlid()); 
				billGoodsStockCheckSum.setPosid(""); 
				billGoodsStockCheckSum.setQty(listSum.get(i).getQty());  
				billGoodsStockCheckSum.setRealqty(listSum.get(i).getQty());  
				billGoodsStockCheckSum.setDiffqty(BigDecimal.valueOf(0)); 
				billGoodsStockCheckSum.setStatus("N"); //Y已盘N未盘
				billGoodsStockCheckSum.setMemo(""); 
				billGoodsStockCheckSum.setDatasource("1"); //数据来源 1库存表 2用户新增录入
				billGoodsStockCheckSum.insert();
			}
			/*
			 * 细单明细
			 */
			List<GoodsStock> listDtl=GoodsStock.INSTANCE.query("storeid=? order by posid,goodsname,lotid", storeid);
			for (int i = 0; i < listDtl.size(); i++) {
				GoodsStockCheckDtl billGoodsStockCheckDtl=new GoodsStockCheckDtl();
				billGoodsStockCheckDtl.setDtlid(billGoodsStockCheckDtl.newId().toString()); 
				billGoodsStockCheckDtl.setDocid(docid); 
				billGoodsStockCheckDtl.setRowno(""+i);  
				billGoodsStockCheckDtl.setGoodsid(listDtl.get(i).getGoodsid());  
				billGoodsStockCheckDtl.setGoodsdtlid(listDtl.get(i).getGoodsdtlid()); 
				billGoodsStockCheckDtl.setPosid(""); 
				billGoodsStockCheckDtl.setLotid(listDtl.get(i).getLotid()); 
				billGoodsStockCheckDtl.setQty(listDtl.get(i).getQty());  
				billGoodsStockCheckDtl.setRealqty(listDtl.get(i).getQty());  
				billGoodsStockCheckDtl.setDiffqty(BigDecimal.valueOf(0)); 
				billGoodsStockCheckDtl.setStatus("N"); //Y已盘N未盘
				billGoodsStockCheckDtl.setMemo(""); 
				billGoodsStockCheckDtl.setDatasource("1"); //数据来源 1库存表 2用户新增录入
				billGoodsStockCheckDtl.insert();
			}
			DBManager.commitAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		
		return 1;
	}
	
	/**
	 * 盘点保存sum表
	 * 总单已经生成,保存过程不需要对总单操作
	 * sum入参为GridSaveModel
	 */
	@Override
	public int checkSave(GridSaveModel modelSum){
		
		try {
			if(modelSum!=null){
				List<GoodsStockCheckSum> insertSum = modelSum.inserts(GoodsStockCheckSum.class);
//				List<GoodsStockCheckSum> deleteSum = modelSum.deletes(GoodsStockCheckSum.class);
				List<GoodsStockCheckSum> updateSum = modelSum.updates(GoodsStockCheckSum.class);
				/* 处理sum表 */
//				//其实没有删除,删除操作参看deleteGoods方法
//				for (GoodsStockCheckSum comp : deleteSum) {
//					comp.delete();
//				}
				//更新遵按批次循顺序核销
				for (GoodsStockCheckSum comp : updateSum) {
					comp.update();
					List<GoodsStockCheckDtl> list=GoodsStockCheckDtl.INSTANCE.query(" docid=? and goodsid=? and goodsdtlid=? ", comp.getDocid(),comp.getGoodsid(),comp.getGoodsdtlid());
					for (GoodsStockCheckDtl billGoodsStockCheckDtl : list) {
						System.out.println(billGoodsStockCheckDtl.getLotid());
					}
				}
				for (GoodsStockCheckSum comp : insertSum) {
					comp.setSumid(comp.newId().toString());
					comp.insert();
				}
			}
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		
		return 1;
	}
	/**
	 * 盘点单增加项目
	 * sum和dtl都增加一条,不存在一对多的情况.即对应的dtl的lotid只有一条.
	 * @param docid
	 * @param goodsid
	 * @param goodsdtlid
	 * @return 1 成功  ;0 数据库操作失败; -1 状态不是临时,不能操作
	 * //N临时  F作废(取消) C已审  D已完成
	 */
	@Override
	public int addGoods(String docid, String goodsid, String goodsdtlid) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GoodsStockCheck goodsStockCheck=GoodsStockCheck.INSTANCE.queryOne(" DOCID=? ", docid);
		if(!"N".equals(goodsStockCheck.getStatus())){
			return -1;
		}else{
			try {
				//sum
				GoodsStockCheckSum goodsStockCheckSum=new GoodsStockCheckSum();
				goodsStockCheckSum.setSumid(goodsStockCheckSum.newId().toString());
				goodsStockCheckSum.setDocid(docid) ;
				Record maxRownoSum=GoodsStockCheckSum.INSTANCE.viewHelper().queryOne("select max(rowno) as rowno  from GOODS_STOCK_CHECK_SUM where docid=?", docid);
				goodsStockCheckSum.setRowno(String.valueOf(Integer.valueOf(maxRownoSum.toString())+1)) ;
				goodsStockCheckSum.setGoodsid(goodsid) ;
				goodsStockCheckSum.setGoodsdtlid(goodsdtlid) ;
				goodsStockCheckSum.setPosid("") ;
				goodsStockCheckSum.setQty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckSum.setRealqty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckSum.setDiffqty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckSum.setStatus("Y") ;//Y已盘N未盘
				goodsStockCheckSum.setMemo("") ; 
				goodsStockCheckSum.setDatasource("2") ;//数据来源 1库存表 2用户新增录入
				goodsStockCheckSum.insert();
				//dtl
				GoodsStockCheckDtl goodsStockCheckDtl=new GoodsStockCheckDtl();
				String dtlid=goodsStockCheckDtl.newId().toString();
				goodsStockCheckDtl.setDtlid(dtlid) ;
				goodsStockCheckDtl.setDocid(docid) ;
				Record maxRownoDtl=GoodsStockCheckSum.INSTANCE.viewHelper().queryOne("select max(rowno) as rowno  from GOODS_STOCK_CHECK_Dtl where docid=?", docid);
				goodsStockCheckDtl.setRowno(String.valueOf(Integer.valueOf(maxRownoDtl.toString())+1)) ;
				goodsStockCheckDtl.setGoodsid(goodsid) ;
				goodsStockCheckDtl.setGoodsdtlid(goodsdtlid) ;
				goodsStockCheckDtl.setPosid("") ;
				goodsStockCheckDtl.setQty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckDtl.setRealqty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckDtl.setDiffqty(BigDecimal.valueOf(0.0)) ;
				goodsStockCheckDtl.setStatus("Y") ;//Y已盘N未盘
				goodsStockCheckDtl.setMemo("") ; 
				goodsStockCheckDtl.setDatasource("2") ;//数据来源 1库存表 2用户新增录入 ;
				/*****单独处理批次*****/
				GoodsLot goodsLot=new GoodsLot();
				String lotid=goodsLot.newId().toString();
				goodsLot.setLotid(lotid);
				goodsLot.setBilltable(ConstantsUtil.TABLENAME_GOODS_STOCK_CHECK_DTL); 
				goodsLot.setBilldtlid(dtlid);
				goodsLot.setGoodsid(goodsid);
				goodsLot.setGoodsdtlid(goodsdtlid);
				goodsLot.setPurprice(BigDecimal.valueOf(0.0));
				goodsLot.setDtlprice(BigDecimal.valueOf(0.0));
				goodsLot.setMemo(ConstantsUtil.STOCK_CHECK_TYPE_KFPD_TEXT);
				goodsLot.setCreatedate(sdf.format(date));
				goodsLot.setBatchno("");
				goodsLot.setProdate("");
				goodsLot.setExpiredate("");
				goodsLot.insert();
				/*****单独处理批次完毕*****/
				goodsStockCheckDtl.setLotid(lotid) ;//批次
				goodsStockCheckDtl.insert();
				
				DBManager.commitAll();
			} catch (Exception e) {
				e.printStackTrace();
				DBManager.rollbackAll();
				return 0;
			}
			return 1;
		}
	}
	/**
	 * 盘点单删除项目
	 * @param docid
	 * @param sumid
	 * @param dtlid
	 * @return 1 成功  ;0 数据库操作失败; -1 状态不是临时,不能操作  ;-2 Sum和Dtl数据源不是新增数据
	 * //N临时  F作废(取消) C已审  D已完成
	 */
	@Override
	public int deleteGoods(String docid, String sumid, String dtlid) {
		GoodsStockCheck goodsStockCheck=GoodsStockCheck.INSTANCE.queryOne(" DOCID=? ", docid);
		GoodsStockCheckSum goodsStockCheckSum=GoodsStockCheckSum.INSTANCE.queryOne("sumid=?", sumid);
		GoodsStockCheckDtl goodsStockCheckDtl=GoodsStockCheckDtl.INSTANCE.queryOne("dtlid=?", dtlid);
		if(!"N".equals(goodsStockCheck.getStatus())){
			return -1;
		}else if(!"2".equals(goodsStockCheckSum.getDatasource())||!"2".equals(goodsStockCheckDtl.getDatasource())){//数据来源 1库存表 2用户新增录入
			return -2;
		}else{
			try {
				goodsStockCheckSum.delete();
				goodsStockCheckDtl.delete();
				DBManager.commitAll();
			} catch (Exception e) {
				e.printStackTrace();
				DBManager.rollbackAll();
				return 0;
			}
			return 1;
		}
	}
	/**
	 * 
	 * 完成盘点
	 * @param docid
	 * 
	 */
	@Override
	public int finishByDocid(String docid) {
		GoodsStockCheck goodsStockCheck=GoodsStockCheck.INSTANCE.queryOne(" DOCID=? ", docid);
		
		return 0;
	}
	/**
	 * 取消盘点
	 * @param docid 
	 * N临时  F作废(取消) C已审  D已完成
	 * 只有N状态的才能进行作废 
	 * return  1:成功  , 0:数据库操作失败 , -1:条件不满足 , -2：其他异常  
	 */
	@Override
	public int cancelByDocid(String docid) {
		GoodsStockCheck goodsStockCheck=GoodsStockCheck.INSTANCE.queryOne(" DOCID=? ", docid);
		String status=goodsStockCheck.getStatus();
		if("F".equals(status)||"C".equals(status)||"D".equals(status)){
			return -1;
		}else if("N".equals(status)){
			try {
				goodsStockCheck.setStatus("F");
				goodsStockCheck.update();
				DBManager.commitAll();
			} catch (Exception e) {
				e.printStackTrace();
				DBManager.rollbackAll();
				return 0;
			}
			return 1;
		}else{
			return -2;
		}
	}
	/**
	 * 审核盘点
	 * @param docid 
	 * N临时  F作废(取消) C已审  D已完成
	 * 只有N状态的才能进行审核
	 * return  1:成功  , 0:数据库操作失败 , -1:条件不满足 , -2：其他异常  
	 */
	@Override
	public int reviewByDocid(String docid) {
		GoodsStockCheck goodsStockCheck=GoodsStockCheck.INSTANCE.queryOne(" DOCID=? ", docid);
		String status=goodsStockCheck.getStatus();
		if("F".equals(status)||"C".equals(status)||"D".equals(status)){
			return -1;
		}else if("N".equals(status)){
			try {
				goodsStockCheck.setStatus("C");
				goodsStockCheck.update();
				DBManager.commitAll();
			} catch (Exception e) {
				e.printStackTrace();
				DBManager.rollbackAll();
				return 0;
			}
			return 1;
		}else{
			return -2;
		}
	}
	
}
