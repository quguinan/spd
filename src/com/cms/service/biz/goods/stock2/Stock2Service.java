package com.cms.service.biz.goods.stock2;

import java.math.BigDecimal;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock2;
import com.cms.model.biz.goods.GoodsStock2Io;
import com.cms.model.biz.goods.GoodsStock2SumV;

public interface Stock2Service {
	/**
	 * 二级库存datagrid汇总 
	 */
	public GridDataModel<GoodsStock2SumV> getGridDataSum();
	/**
	 * 二级库存datagrid 明细
	 */
	public GridDataModel<GoodsStock2> getGridDataDtl();
	/**
	 * 二级库存datagrid StockIO  
	 * 通过goodsid  获取IO流水
	 */
	public GridDataModel<GoodsStock2Io> getGridDataStockIOByGoodsid(String goodsid,String goodsdtlid);
	/**
	 * 二级库存datagrid StockIO  
	 * 通过批次  获取IO流水
	 */
	public GridDataModel<GoodsStock2Io> getGridDataStockIOByLotid(String lotid);
	
	/**
	 * 二级库存datagrid 汇总 根据名称，拼音
	 */
	public GridDataModel<GoodsStock2SumV> getGridDataSum(String deptid,String goodsname,String goodsspell);
	/**
	 * 二级库存datagrid 明细 根据名称，拼音
	 */
	public GridDataModel<GoodsStock2> getGridDataDtl(String deptid,String goodsname,String goodsspell);
	/**
	 * 验证单一货品库存是否满足指定数量
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty	验证的数量必须是正数
	 * @return
	 */
	public boolean isValidStock2Qty(String deptid,String goodsid,String goodsdtlid,final BigDecimal qty);
	/**
	 * 
	 * @param stockid
	 * @return
	 */
	public GoodsStock2 findOne(String stockid);
	/**
	 * 
	 * @param stockid
	 * @return
	 */
	public GoodsStock2SumV findOneSum(String deptid,String goodsid ,String goodsdtlid);
	/**
	 * 二级库入库
	 * 所有正向入库统一调用
	 * 参数lotid保证唯一,如果存在则合并数量.
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * @param deptid	二级库科室ID
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid  批次号已知,需要查找批次号是否已存在,如果存在,则库存合并.<====******合并******
	 * @param posid
	 * @param qty	入库数量必须为正
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return 1 成功  -1其他失败 -2入库数量不能小于或等于0
	 * @return
	 */
	public int goodsInStock2(
			 String deptid,
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
	 * 通用二级库存出库操作   (原理与一级库出库相同)
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第一种情况:指定库存记录消减 ,需要提供stockid,消减库存的数量qty(负数!!!)
	 * @param stockid 已知库存ID,要更新的库存条目
	 * @param storeid 以下参数是库存流水需要的单据明细信息
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid
	 * @param posid
	 * @param qty	出库数量必须是负数
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return  1 成功  -1其他失败 -2出库数量不能大于或等于0
	 */
	public int goodsOutStock2(
							 String stockid ,
							 String deptid,
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
	 * 通用二级库存出库操作     (原理与一级库出库相同)
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第二种情况:未指定库存记录 ,没有提供deptid ,lotid , pos,只有消减数量 qty(负数!!!)
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
	public  int goodsOutStock2(
							 String deptid,
							 String goodsid,
							 String goodsdtlid,
							 final BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable,
							 String memo);
	/**
	 * 二级库房调拨
	 * 从科室调拨到库房
	 * @return
	 */
	public int goodsStock2TransDeptToStore();
	/**
	 * 二级库房调拨
	 * 从科室调拨到科室
	 * @return
	 */
	public int goodsStock2TransDeptToDept();
	
}
