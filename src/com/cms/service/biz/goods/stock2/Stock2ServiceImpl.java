package com.cms.service.biz.goods.stock2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock2;
import com.cms.model.biz.goods.GoodsStock2Io;
import com.cms.model.biz.goods.GoodsStock2SumV;
import com.cms.util.biz.PageFactoryEasyUI;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年5月16日 下午2:01:00 
 * 
 *@descriptions 类说明：二级库存业务
 */
@Service
public class Stock2ServiceImpl implements Stock2Service{
	/**
	 * 二级库存datagrid汇总 
	 */
	@Override
	public GridDataModel<GoodsStock2SumV> getGridDataSum(){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("1=?");
		params.add("1");
		GridDataModel<GoodsStock2SumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2SumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 二级库存datagrid 明细
	 */
	@Override
	public GridDataModel<GoodsStock2> getGridDataDtl(){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("1=?");
		params.add("1");
		GridDataModel<GoodsStock2> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2.class, filter.toString()," order by goodsid,goodsdtlid,lotid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 二级库存datagrid StockIO  
	 * 通过goodsid  获取IO流水
	 */
	@Override
	public GridDataModel<GoodsStock2Io> getGridDataStockIOByGoodsid(String goodsid,String goodsdtlid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("goodsid=? and goodsdtlid=?");
		params.add(goodsid);
		params.add(goodsdtlid);
		GridDataModel<GoodsStock2Io> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2Io.class, filter.toString()," order by ioid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 二级库存datagrid StockIO  
	 * 通过批次  获取IO流水
	 */
	@Override
	public GridDataModel<GoodsStock2Io> getGridDataStockIOByLotid(String lotid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("lotid=?");
		params.add(lotid);
		GridDataModel<GoodsStock2Io> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2Io.class, filter.toString()," order by ioid ",
				params.toArray());
		return gridDataModel;
	}
	
	/**
	 * 二级库存datagrid 汇总 根据名称，拼音
	 */
	@Override
	public GridDataModel<GoodsStock2SumV> getGridDataSum(String deptid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!deptid.equals("")){
			filter.append(" and deptid = ?");
			params.add(deptid);
		}
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStock2SumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2SumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 二级库存datagrid 明细 根据名称，拼音
	 */
	@Override
	public GridDataModel<GoodsStock2> getGridDataDtl(String deptid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!deptid.equals("")){
			filter.append(" and deptid = ?");
			params.add(deptid);
		}
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStock2> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock2.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 验证单一货品库存是否满足指定数量
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty	验证的数量必须是正数
	 * @return
	 */
	@Override
	public boolean isValidStock2Qty(String deptid,String goodsid,String goodsdtlid,final BigDecimal qty){
		//compareTo : -1表示小于，0是等于，1是大于。
		if(qty.compareTo(BigDecimal.ZERO)!=1){ //小于或等于0
			return false;
		}
		GoodsStock2SumV goodsStock2SumV=GoodsStock2SumV.INSTANCE.queryOne("goodsid=? and goodsdtlid=? and deptid=?", goodsid,goodsdtlid,deptid);
		if(goodsStock2SumV.getQty().compareTo(qty)==-1){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @param stockid
	 * @return
	 */
	@Override
	public GoodsStock2 findOne(String stockid){
		return GoodsStock2.INSTANCE.queryOne("stockid=?", stockid);
	}
	/**
	 * 
	 * @param stockid
	 * @return
	 */
	@Override
	public GoodsStock2SumV findOneSum(String deptid,String goodsid ,String goodsdtlid){
		return GoodsStock2SumV.INSTANCE.queryOne("deptid=? and goodsid=? and goodsdtlid=?", deptid,goodsid,goodsdtlid);
	}
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
	@Override
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
			 String memo){
		//compareTo : -1表示小于，0是等于，1是大于。
		if(qty.compareTo(BigDecimal.ZERO)!=1){ //小于或等于0
			return -2;
		}
		try {
			/*
			 * 判断库存中的批次号是否已经存在
			 */
			GoodsStock2 goodsStock2Lot=GoodsStock2.INSTANCE.queryOne("deptid=? and lotid=? and goodsid=? and goodsdtlid=?", deptid,lotid,goodsid,goodsdtlid);
			if(goodsStock2Lot==null){
				/*
				 * 新增入库存
				 */
				GoodsStock2 goodsStock2=new GoodsStock2();
				String stockid=goodsStock2.newId().toString();
				goodsStock2.setStockid(stockid);
				goodsStock2.setDeptid(deptid);
				goodsStock2.setGoodsid(goodsid);
				goodsStock2.setGoodsdtlid(goodsdtlid);
				goodsStock2.setLotid(lotid);
				goodsStock2.setPosid(posid);
				goodsStock2.setQty(qty);
				goodsStock2.setStatus("Y");//状态 Y可用 N禁用 
				
				goodsStock2.insert();
				/*
				 * 库存流水
				 */
				goodsStock2IO(posid, billdtlid, billtype, billtable, stockid, deptid, lotid, goodsid, goodsdtlid,memo, qty);
			}else{
				/*
				 * 更新入库存
				 */
				goodsStock2Lot.updateField("qty", goodsStock2Lot.getQty().add(qty), "deptid=? and lotid=? and goodsid=? and goodsdtlid=?",deptid, lotid,goodsid,goodsdtlid);
				/*
				 * 库存流水
				 */
				goodsStock2IO(posid, billdtlid, billtype, billtable, goodsStock2Lot.getStockid(), deptid, lotid, goodsid, goodsdtlid,memo, qty);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return 1;
	}
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
	@Override
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
							 String memo){
		//compareTo : -1表示小于，0是等于，1是大于。
		if(qty.compareTo(BigDecimal.ZERO)!=-1){ //大于或等于0
			return -2;
		}
		try {
			/*
			 * 出库存
			 */
			GoodsStock2 goodsStock2=GoodsStock2.INSTANCE.queryOne("stockid=? and status='Y'", stockid);
			goodsStock2.setQty(goodsStock2.getQty().subtract(qty.abs()));//库存数量相减
			goodsStock2.update();
			/*
			 * 库存IO流水
			 */
			goodsStock2IO(posid, billdtlid, billtype, billtable, stockid, deptid, lotid, goodsid, goodsdtlid,memo, qty);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
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
	@Override
	public  int goodsOutStock2(
							 String deptid,
							 String goodsid,
							 String goodsdtlid,
							 final BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable,
							 String memo){
		//compareTo : -1表示小于，0是等于，1是大于。
		if(qty.compareTo(BigDecimal.ZERO)!=-1){ //大于或等于0
			return -2;
		}
		List<GoodsStock2> list=GoodsStock2.INSTANCE.query("deptid=? and goodsid=? and goodsdtlid=? and status='Y' "
				+ "order by lotid ", deptid,goodsid,goodsdtlid); //按照批次顺序核销 , 此处order by非常重要
		try {
			//临时可变数量
			BigDecimal qtyTemp=qty.abs();
			for (GoodsStock2 goodsStock2 : list) {
				/*
				 * 出库存
				 */
				//compareTo : -1表示小于，0是等于，1是大于。
				if(goodsStock2.getQty().compareTo(qtyTemp)!=-1){
					/*
					 * 库存IO流水
					 */
					goodsStock2IO(goodsStock2.getPosid(), billdtlid, billtype, billtable, goodsStock2.getStockid(), deptid, goodsStock2.getLotid(), goodsid, goodsdtlid, memo,qtyTemp.abs().negate());//出库一定是负数
					//当前库存大于或等于出库数量
					goodsStock2.setQty(goodsStock2.getQty().subtract(qtyTemp));
					goodsStock2.update();
					//完成减库存,结束循环
					break;
				}else{
					//如果批次数量为0,则不作操作.
					if(goodsStock2.getQty().compareTo(BigDecimal.ZERO)!=0){
						/*
						 * 库存IO流水
						 */
						goodsStock2IO(goodsStock2.getPosid(), billdtlid, billtype, billtable, goodsStock2.getStockid(), deptid, goodsStock2.getLotid(), goodsid, goodsdtlid, memo,goodsStock2.getQty().abs().negate());//出库一定是负数
						qtyTemp=qtyTemp.subtract(goodsStock2.getQty());
						//当前库存小于出库数量
						goodsStock2.setQty(BigDecimal.ZERO); 
						goodsStock2.update();
					}
				}
			}
			//DBManager.commitAll();
		} catch (Exception e) {
			//DBManager.rollbackAll();
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * 二级库房调拨
	 * 从科室调拨到库房
	 * @return
	 */
	@Override
	public int goodsStock2TransDeptToStore(){
		return 1;
	}
	/**
	 * 二级库房调拨
	 * 从科室调拨到科室
	 * @return
	 */
	@Override
	public int goodsStock2TransDeptToDept(){
		return 1;
	}
	/**
	 * 通用二级库存IO操作
	 * 所有出入库操作统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * @param posid
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @param stockid
	 * @param deptid
	 * @param lotid
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty
	 * @return null
	 * @throws Exception 
	 */
	private  void goodsStock2IO ( 
			 String posid,
			 String billdtlid,
			 String billtype,
			 String billtable,
			 String stockid,
			 String deptid,
			 String lotid,
			 String goodsid,
			 String goodsdtlid,
			 String memo,
			 final BigDecimal qty ) throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*
		 * 二级库存IO流水
		 */
		try {
			GoodsStock2Io goodsStock2Io=new GoodsStock2Io();
			goodsStock2Io.setIoid(goodsStock2Io.newId().toString());
			goodsStock2Io.setIodate(sdf.format(date));
			goodsStock2Io.setBilldtlid(billdtlid);
			goodsStock2Io.setBilltype(billtype);
			goodsStock2Io.setBilltable(billtable);
			goodsStock2Io.setMemo(memo);
			goodsStock2Io.setStockid(stockid);
			goodsStock2Io.setDeptid(deptid);
			goodsStock2Io.setLotid(lotid);
			goodsStock2Io.setGoodsid(goodsid);
			goodsStock2Io.setGoodsdtlid(goodsdtlid);
			goodsStock2Io.setQty(qty);
			
			goodsStock2Io.insert();
		} catch (Exception e) {
			//没有返回值,直接抛异常
			throw new Exception();
		}
		
	}
}
