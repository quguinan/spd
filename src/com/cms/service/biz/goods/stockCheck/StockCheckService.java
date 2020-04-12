package com.cms.service.biz.goods.stockCheck;


import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;


public interface StockCheckService {
	/**
	 *  查找当前唯一没有盘点完成的总单,by docid
	 * @param storeid
	 * @return
	 */
	public GoodsStockCheck getUnfinishedDocid(String storeid);
	/**
	 * 根据docid,获取盘点合计Sum的dg数据
	 * @param docid
	 * @return
	 */
	public GridDataModel<GoodsStockCheckSum> gridDataSumByDocid(String docid);
	/**
	 * 根据docid,获取盘点明细Dtl的dg数据
	 * @param goodsid
	 * @param goodsdtlid
	 * @param docid
	 * @return
	 */
	public GridDataModel<GoodsStockCheckDtl> gridDataDtlByDocid(String goodsid,String goodsdtlid,String docid);
	/**
	 * 盘点开始,由库存生成盘点单
	 * @param storeid
	 * @return
	 */
	public int checkStart(String storeid);
	/**
	 * * 盘点保存sum表
	 * 总单已经生成,保存过程不需要对总单操作
	 * @param modelSum 入参为GridSaveModel
	 * @return
	 */
	public int checkSave(GridSaveModel modelSum);
	
	/**
	 * 盘点单增加项目
	 * @param docid
	 * @param goodsid
	 * @param goodsdtlid
	 * @return
	 */
	public int addGoods(String docid,String goodsid,String goodsdtlid);
	/**
	 * 盘点单删除项目
	 * @param docid
	 * @param goodsid
	 * @param goodsdtlid
	 * @return
	 */
	public int deleteGoods(String docid, String sumid, String dtlid);
	/**
	 * 完成盘点
	 * @param docid
	 */
	public int finishByDocid(String docid);
	
	/**
	 * 
	 * 取消盘点
	 * @param docid
	 * N临时  F作废(取消) C已审  D已完成
	 * 只有N状态的才能进行作废
	 * 
	 * return  1:成功  , 0:条件不满足 , -1:异常失败  
	 */
	public int cancelByDocid(String docid);
	
	/**
	 * 
	 * 审核盘点
	 * @param docid
	 * N临时  F作废(取消) C已审  D已完成
	 * 只有N状态的才能进行审核
	 * 
	 * return  1:成功  , 0:条件不满足 , -1:异常失败  
	 */
	public int reviewByDocid(String docid);
}
