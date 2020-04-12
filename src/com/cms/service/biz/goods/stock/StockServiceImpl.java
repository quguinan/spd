package com.cms.service.biz.goods.stock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockIo;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.util.biz.PageFactoryEasyUI;


/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月20日 上午8:24:17 
 * 
 *@descriptions 类说明：库存业务service
 */
@Service
public class StockServiceImpl implements StockService{
	
	/**
	 * 一级库存datagrid汇总 
	 */
	@Override
	public GridDataModel<GoodsStockSumV> getGridDataSum(String storeid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and storeid=? ");
		params.add(storeid);
		GridDataModel<GoodsStockSumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockSumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 明细
	 */
	@Override
	public GridDataModel<GoodsStock> getGridDataDtl(String storeid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and storeid=? ");
		params.add(storeid);
		GridDataModel<GoodsStock> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock.class, filter.toString()," order by goodsid,goodsdtlid , lotid",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid StockIO  
	 * 通过goodsid  获取IO流水
	 */
	@Override
	public GridDataModel<GoodsStockIo> getGridDataStockIOByGoodsid(String storeid,String goodsid,String goodsdtlid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and storeid=? and goodsid=? and goodsdtlid=?");
		params.add(storeid);
		params.add(goodsid);
		params.add(goodsdtlid);
		GridDataModel<GoodsStockIo> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockIo.class, filter.toString()," order by ioid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid StockIO  
	 * 通过批次  获取IO流水
	 */
	@Override
	public GridDataModel<GoodsStockIo> getGridDataStockIOByLotid(String storeid,String lotid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and storeid=? and lotid=?");
		params.add(storeid);
		params.add(lotid);
		GridDataModel<GoodsStockIo> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockIo.class, filter.toString()," order by ioid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 汇总 根据名称，拼音
	 */
	@Override
	public GridDataModel<GoodsStockSumV> getGridDataSum(String storeid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		filter.append(" and storeid = ?");
		params.add(storeid);
		
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStockSumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockSumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 明细 根据名称，拼音
	 */
	@Override
	public GridDataModel<GoodsStock> getGridDataDtl(String storeid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		filter.append(" and storeid = ?");
		params.add(storeid);
		
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStock> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存根据storeid  查找类列表
	 */
	@Override
	public List<GoodsStock> findList(String storeid){
		return GoodsStock.INSTANCE.query(" storeid=? ", storeid);
	}
	/**
	 * 一级库存根据storeid , goodsid 查找类列表
	 */
	@Override
	public List<GoodsStock> findList(String storeid,String goodsid){
		return GoodsStock.INSTANCE.query(" storeid=? and goodsid=? ", goodsid);
	}
	/**
	 * 一级库存根据storeid , goodsid, goodsdtlid 查找类列表
	 */
	@Override
	public List<GoodsStock> findList(String storeid,String goodsid,String goodsdtlid){
		return GoodsStock.INSTANCE.query(" storeid=? and goodsid=? and goodsdtlid=?", storeid,goodsid,goodsdtlid);
	}
	/**
	 * 一级库存根据stockid 查找类
	 */
	@Override
	public GoodsStock findOne(String stockid){
		return GoodsStock.INSTANCE.queryOne(" stockid=?", stockid);
	}
	/**
	 * 验证单一货品库存是否满足指定数量
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty	验证的数量必须是正数
	 * @return
	 */
	@Override
	public boolean isValidStockQty(String storeid,String goodsid,String goodsdtlid,final BigDecimal qty){
		//compareTo : -1表示小于，0是等于，1是大于。
		if(qty.compareTo(BigDecimal.ZERO)!=1){ //小于或等于0
			return false;
		}
		GoodsStockSumV goodsStockSumV=GoodsStockSumV.INSTANCE.queryOne(" storeid=? and goodsid=? and goodsdtlid=? ",storeid, goodsid,goodsdtlid);
		if(goodsStockSumV.getQty().compareTo(qty)==-1){
			return false;
		}else{
			return true;
		}
	}
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
	@Override
	public  int goodsInStock(String storeid,
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
			GoodsStock goodsStockLot=GoodsStock.INSTANCE.queryOne("storeid=? and lotid=? and goodsid=? and goodsdtlid=?", storeid,lotid,goodsid,goodsdtlid);
			if(goodsStockLot==null){
				/*
				 * 新增入库存
				 */
				GoodsStock goodsStock=new GoodsStock();
				String stockid=goodsStock.newId().toString();
				goodsStock.setStockid(stockid);
				goodsStock.setStoreid(storeid);
				goodsStock.setGoodsid(goodsid);
				goodsStock.setGoodsdtlid(goodsdtlid);
				goodsStock.setLotid(lotid);
				goodsStock.setPosid(posid);
				goodsStock.setQty(qty);
				goodsStock.setStatus("Y");//状态 Y可用 N禁用
				
				goodsStock.insert();
				/*
				 * 库存流水
				 */
				goodsStockIO(posid, billdtlid, billtype, billtable, stockid, storeid, lotid, goodsid, goodsdtlid,memo, qty);
			}else{
				/*
				 * 更新入库存
				 */
				goodsStockLot.updateField("qty", goodsStockLot.getQty().add(qty), "storeid=? and lotid=? and goodsid=? and goodsdtlid=?",storeid, lotid,goodsid,goodsdtlid);
				/*
				 * 库存流水
				 */
				goodsStockIO(posid, billdtlid, billtype, billtable, goodsStockLot.getStockid(), storeid, lotid, goodsid, goodsdtlid,memo, qty);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
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
	@Override
	public int goodsOutStock(
							 String stockid ,
							 String storeid,
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
			GoodsStock goodsStock=GoodsStock.INSTANCE.queryOne("stockid=? and status='Y'", stockid);
			goodsStock.setQty(goodsStock.getQty().subtract(qty.abs()));//库存数量相减
			goodsStock.update();
			/*
			 * 库存IO流水
			 */
			String goodsid=goodsStock.getGoodsid();
			String goodsdtlid=goodsStock.getGoodsdtlid();
			String lotid=goodsStock.getLotid();
			String posid=goodsStock.getPosid();
			goodsStockIO(posid, billdtlid, billtype, billtable, stockid, storeid, lotid, goodsid, goodsdtlid,memo, qty);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
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
	@Override
	public  int goodsOutStock(
							 String storeid,
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
		List<GoodsStock> list=GoodsStock.INSTANCE.query("storeid=? and goodsid=? and goodsdtlid=? and status='Y' "
				+ "order by lotid ", storeid,goodsid,goodsdtlid); //按照批次顺序核销 , 此处order by非常重要
		try {
			//临时可变数量
			BigDecimal qtyTemp=qty.abs();
			for (GoodsStock goodsStock : list) {
				/*
				 * 出库存
				 */
				//compareTo : -1表示小于，0是等于，1是大于。
				if(goodsStock.getQty().compareTo(qtyTemp)!=-1){
					/*
					 * 库存IO流水
					 */
					goodsStockIO(goodsStock.getPosid(), billdtlid, billtype, billtable, goodsStock.getStockid(), storeid, goodsStock.getLotid(), goodsid, goodsdtlid, memo,qtyTemp.abs().negate());//出库一定是负数
					//当前库存大于或等于出库数量
					goodsStock.setQty(goodsStock.getQty().subtract(qtyTemp));
					goodsStock.update();
					//完成减库存,结束循环
					break;
				}else{
					//如果批次数量为0,则不作操作.
					if(goodsStock.getQty().compareTo(BigDecimal.ZERO)!=0){
						/*
						 * 库存IO流水
						 */
						goodsStockIO(goodsStock.getPosid(), billdtlid, billtype, billtable, goodsStock.getStockid(), storeid, goodsStock.getLotid(), goodsid, goodsdtlid, memo,goodsStock.getQty().abs().negate());//出库一定是负数
						qtyTemp=qtyTemp.subtract(goodsStock.getQty());
						//当前库存小于出库数量
						goodsStock.setQty(BigDecimal.ZERO); 
						goodsStock.update();
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
	 * 调拨
	 * 一级库房
	 * 从库房调拨到库房
	 * @return
	 */
	@Override
	public int goodsTransStoreToStore(
			String oldStoreid,
			String newStoreid,
			String stockid,
			BigDecimal qty
			){
		
		return 1;
	}
	/**
	 * 调拨
	 * 一级库房
	 * 从库房调拨到科室
	 * @return
	 */
	@Override
	public int goodsTransStoreToDept(){
		return 1;
	}
	/**
	 * 通用库存IO操作
	 * 所有出入库操作统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 必须定义成private,不能单独使用
	 * @param posid
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @param stockid
	 * @param storeid
	 * @param lotid
	 * @param goodsid
	 * @param goodsdtlid
	 * @param qty
	 * @return null
	 * @throws Exception 
	 */
	private void goodsStockIO ( 
			 String posid,
			 String billdtlid,
			 String billtype,
			 String billtable,
			 String stockid,
			 String storeid,
			 String lotid,
			 String goodsid,
			 String goodsdtlid,
			 String memo,
			 final BigDecimal qty ) throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*
		 * 库存IO流水
		 */
		try {
			GoodsStockIo goodsStockIo=new GoodsStockIo();
			goodsStockIo.setIoid(goodsStockIo.newId().toString());
			goodsStockIo.setIodate(sdf.format(date));
			goodsStockIo.setBilldtlid(billdtlid);
			goodsStockIo.setBilltype(billtype);
			goodsStockIo.setBilltable(billtable);
			goodsStockIo.setMemo(memo);
			goodsStockIo.setStockid(stockid);
			goodsStockIo.setStoreid(storeid);
			goodsStockIo.setLotid(lotid);
			goodsStockIo.setGoodsid(goodsid);
			goodsStockIo.setGoodsdtlid(goodsdtlid);
			goodsStockIo.setQty(qty);
			
			goodsStockIo.insert();
		} catch (Exception e) {
			//没有返回值,直接抛异常
			throw new Exception();
		}
		
	}
}























