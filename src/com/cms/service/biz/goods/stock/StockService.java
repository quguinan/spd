package com.cms.service.biz.goods.stock;

import java.math.BigDecimal;
import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockIo;
import com.cms.model.biz.goods.GoodsStockSumV;

public interface StockService {
	/**
	 * 一级库存datagrid汇总 
	 */
	public GridDataModel<GoodsStockSumV> getGridDataSum(String storeid);
	/**
	 * 一级库存datagrid 明细
	 */
	public GridDataModel<GoodsStock> getGridDataDtl(String storeid);
	/**
	 * 一级库存datagrid StockIO  
	 * 通过goodsid  获取IO流水
	 */
	public GridDataModel<GoodsStockIo> getGridDataStockIOByGoodsid(String storeid,String goodsid,String goodsdtlid);
	/**
	 * 一级库存datagrid StockIO  
	 * 通过批次  获取IO流水
	 */
	public GridDataModel<GoodsStockIo> getGridDataStockIOByLotid(String storeid,String lotid);
	/**
	 * 一级库存datagrid 汇总 根据名称，拼音
	 */
	public GridDataModel<GoodsStockSumV> getGridDataSum(String storeid,String goodsname,String goodsspell);
	/**
	 * 一级库存datagrid 明细 根据名称，拼音
	 */
	public GridDataModel<GoodsStock> getGridDataDtl(String storeid,String goodsname,String goodsspell);
	/**
	 * 一级库存根据storeid  查找类列表
	 */
	public List<GoodsStock> findList(String storeid);
	/**
	 * 一级库存根据storeid , goodsid 查找类列表
	 */
	public List<GoodsStock> findList(String storeid,String goodsid);
	/**
	 * 一级库存根据storeid , goodsid, goodsdtlid 查找类列表
	 */
	public List<GoodsStock> findList(String storeid,String goodsid,String goodsdtlid);
	/**
	 * 一级库存根据stockid 查找类
	 */
	public GoodsStock findOne(String stockid);
	/**
	 * 验证单一货品库存是否满足指定数量
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty	验证的数量必须是正数
	 * @return
	 */
	public boolean isValidStockQty(String storeid,String goodsid,String goodsdtlid,final BigDecimal qty);
	/**
	 * 通用一级库存入库操作
	 * 所有正向入库统一调用
	 * 参数lotid保证唯一,如果存在则合并数量.
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * @param storeid
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid  批次号已知,需要查找批次号是否已存在,如果存在,则库存合并.<====******合并******
	 * @param posid
	 * @param qty	入库数量必须为正
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return 1 成功  -1其他失败 -2入库数量不能小于或等于0
	 */
	public  int goodsInStock(String storeid,
							 String goodsid,
							 String goodsdtlid,
							 String lotid,
							 String posid,
							 final BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable,
							 String memo);
	/**
	 * 通用一级库存出库操作
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第一种情况:指定库存记录消减 ,需要提供stockid,消减库存的数量qty(负数!!!)
	 * @param stockid 已知库存ID,要更新的库存条目
	 * @param storeid 以下参数是库存流水需要的单据明细信息
	 * @param qty	出库数量必须是负数
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return  1 成功  -1其他失败 -2出库数量不能大于或等于0
	 */
	public int goodsOutStock(
							 String stockid ,
							 String storeid,
							 final BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable,
							 String memo);
	/**
	 * 通用一级库存出库操作
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第二种情况:未指定库存记录 ,没有提供stockid ,lotid , pos,只有消减数量 qty(负数!!!)
	 * 			 按照满足条件的记录进行顺序核销
	 * 			 顺序核销的规则暂定按照入库时间从前往后消减
	 * @param storeid 
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty	出库数量必须是负数
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return  1 成功  -1其他失败 -2出库数量不能大于或等于0
	 */
	public  int goodsOutStock(
							 String storeid,
							 String goodsid,
							 String goodsdtlid,
							 final BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable,
							 String memo);
	/**
	 * 调拨
	 * 一级库房
	 * 从库房调拨到库房
	 * @return
	 */
	public int goodsTransStoreToStore(
			String oldStoreid,
			String newStoreid, 
			String stockid,
			BigDecimal qty
			);
	/**
	 * 调拨
	 * 一级库房
	 * 从库房调拨到科室
	 * @return
	 */
	public int goodsTransStoreToDept();
	
}
