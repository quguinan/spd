package com.cms.service.biz.goods;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.biz.goods.GoodsStock2Io;
import com.cms.model.biz.goods.GoodsStockIo;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.stock.StockService;
import com.cms.service.biz.goods.stock2.Stock2Service;
import com.cms.util.biz.ConstantsUtil;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月15日 上午9:45:23
 * 
 * @descriptions 类说明：二级库申领单操作
 */
@Service
public class BillStock2ApplyService {
	@Autowired
	private Stock2Service stock2Service;
	@Autowired
	private StockService stockService;
	/**
	 * 根据id,获取doc的对象数据
	 */
	public GoodsBillApply getBillGoodsInByDocid(String docid){
		GoodsBillApply billGoodsIn=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		return billGoodsIn;
	}
	
	/**
	 * 根据docid,获取dtl的dg数据
	 */
	public GridDataModel<GoodsBillApplyDtl> gridDataDtlByDocid(String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid=?");
		params.add(docid);
		GridDataModel<GoodsBillApplyDtl> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApplyDtl.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * N临时F作废C审核S发送A接收O出库D入库
	 * 根据credate,获取doc的dg数据
	 * 申请入库的单据
	 * 发送前状态
	 */
	public GridDataModel<GoodsBillApply> gridDataDocBycredateBeforeS(String credate,String billtype){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!credate.equals("")){
			//二级库申领单状态:N临时F作废C审核S发送A接收O出库D入库
			filter.append(" and to_char(createdate,'yyyy-mm-dd')=? and status in ('N','F','C') and billtype=? ");
			params.add(credate);
			params.add(billtype);
		}
		GridDataModel<GoodsBillApply> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApply.class, filter.toString()," order by docid desc ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * N临时F作废C审核S发送A接收O出库D入库
	 * 根据credate,获取doc的dg数据
	 * 发送后状态
	 */
	public GridDataModel<GoodsBillApply> gridDataDocBycredateAfterS(String credate,String billtype){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!credate.equals("")){
			//二级库申领单状态:N临时F作废C审核S发送A接收O出库D入库
			filter.append(" and to_char(createdate,'yyyy-mm-dd')=? and status in ('S','A','O','D') and billtype=? ");
			params.add(credate);
			params.add(billtype);
		}
		GridDataModel<GoodsBillApply> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApply.class, filter.toString()," order by docid desc ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * N临时F作废C审核S发送A接收O出库D入库
	 * 根据credate,获取doc的dg数据
	 * O状态  待入库状态
	 */
	public GridDataModel<GoodsBillApply> gridDataDocBycredateO(String credate,String billtype){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!credate.equals("")){
			//二级库申领单状态:N临时F作废C审核S发送A接收O出库D入库
			filter.append(" and to_char(createdate,'yyyy-mm-dd')=? and status in ('O')  and billtype=? ");
			params.add(credate);
			params.add(billtype);
		}
		GridDataModel<GoodsBillApply> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApply.class, filter.toString()," order by docid desc ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 设置申请单主单状态
	 * N临时  F作废  C审核  S发送  A接收  O出库  D入库
	 * 
	 * 申请单状态的描述:
	 * 1.科室申请  billtype=KSSQ
	 * 二级库房出库时,实际数量默认等于申请数量
	 * 二级库房发送时(C->S),实际数量默认等于申请数量
	 * N->C->S->A->O->D
	 * 
	 * 2.科室退库  billtype=KSTK
	 * 科室退库审核后直接二级库房科室出库,然后一库库房入库,结束
	 * 二级库房出库时(C->O),实际数量默认等于申请数量
	 * N->C->O->D
	 * 
	 */
	public  int setBillGoodsApplyDocStatus(String docid,String status){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		String billtype=goodsBillApply.getBilltype();
		try {
			if(status.equals(ConstantsUtil.APPLY_STATUS_C)){
				goodsBillApply.setCheckerid(user.getUserid());
				goodsBillApply.setCheckdate(sdf.format(date));
			}else if(status.equals(ConstantsUtil.APPLY_STATUS_S)){//设置成发送后,默认realqty=qty
				List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
				for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
					goodsBillApplyDtl.setRealqty(goodsBillApplyDtl.getQty());
					goodsBillApplyDtl.update();
				}
				goodsBillApply.setSenderid(user.getUserid());
				goodsBillApply.setSenddate(sdf.format(date));
			}else if(status.equals(ConstantsUtil.APPLY_STATUS_A)){
				goodsBillApply.setAcceptid(user.getUserid());
				goodsBillApply.setAcceptdate(sdf.format(date));
			}else if(status.equals(ConstantsUtil.APPLY_STATUS_O)&&billtype.equals(ConstantsUtil.APPLY_TYPE_KSSQ)){
				goodsBillApply.setIoid(user.getUserid());
				goodsBillApply.setIodate(sdf.format(date));
			}else if(status.equals(ConstantsUtil.APPLY_STATUS_O)&&billtype.equals(ConstantsUtil.APPLY_TYPE_KSTK)){//科室申请退库,设置成出库后,默认realqty=qty
				List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
				for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
					goodsBillApplyDtl.setRealqty(goodsBillApplyDtl.getQty());
					goodsBillApplyDtl.update();
				}
				goodsBillApply.setIoid(user.getUserid());
				goodsBillApply.setIodate(sdf.format(date));
			}else if(status.equals(ConstantsUtil.APPLY_STATUS_D)){
				goodsBillApply.setIo2id(user.getUserid());
				goodsBillApply.setIo2date(sdf.format(date));
			}
			goodsBillApply.setStatus(status);
			goodsBillApply.update();
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 新增单据  入库
	 * @param goodsBillApply 总单
	 * @param inserts 细单新增数据
	 * @return  1 正常  
	 * 			-1其他问题
	 */
	public int addKSSQ(GoodsBillApply goodsBillApply,GridSaveModel model){
			//List<GoodsBillApplyDtl> inserts){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		
		try {
			//总单
			String docid=""+goodsBillApply.newId();
			goodsBillApply.setDocid(docid);
			goodsBillApply.setDocno(ConstantsUtil.APPLY_TYPE_KSSQ+sdf2.format(date)+"-"+docid);
			goodsBillApply.setBilltype(ConstantsUtil.APPLY_TYPE_KSSQ);
			goodsBillApply.setBillname(ConstantsUtil.APPLY_TYPE_KSSQ_TEXT);
			goodsBillApply.setCreatedate(sdf.format(date));
			goodsBillApply.setCreaterid(user.getUserid());
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_N);//状态:N临时C已审核D已处理F作废
			goodsBillApply.insert();
			//细单
			List<GoodsBillApplyDtl> inserts = model.inserts(GoodsBillApplyDtl.class);
			for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
				String dtlid=goodsBillApplyDtl.newId().toString();
				goodsBillApplyDtl.setDtlid(dtlid);
				goodsBillApplyDtl.setDocid(docid);
				goodsBillApplyDtl.insert();
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
	 * 新增单据 退库
	 * @param goodsBillApply 总单
	 * @param inserts 细单新增数据
	 * @return  1 正常  
	 * 			-1其他问题
	 */
	public int addKSTK(GoodsBillApply goodsBillApply,GridSaveModel model){
			//List<GoodsBillApplyDtl> inserts){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		
		try {
			//总单
			String docid=""+goodsBillApply.newId();
			goodsBillApply.setDocid(docid);
			goodsBillApply.setDocno(ConstantsUtil.APPLY_TYPE_KSTK+sdf2.format(date)+"-"+docid);
			goodsBillApply.setBilltype(ConstantsUtil.APPLY_TYPE_KSTK);
			goodsBillApply.setBillname(ConstantsUtil.APPLY_TYPE_KSTK_TEXT);
			goodsBillApply.setCreatedate(sdf.format(date));
			goodsBillApply.setCreaterid(user.getUserid());
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_N);//状态:N临时C已审核D已处理F作废
			goodsBillApply.insert();
			//细单
			List<GoodsBillApplyDtl> inserts = model.inserts(GoodsBillApplyDtl.class);
			for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
				String dtlid=goodsBillApplyDtl.newId().toString();
				goodsBillApplyDtl.setRealqty(goodsBillApplyDtl.getQty().abs().negate());//确保退库数量是负数
				goodsBillApplyDtl.setDtlid(dtlid);
				goodsBillApplyDtl.setDocid(docid);
				goodsBillApplyDtl.insert();
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
	 * 更新单据 入库
	 * @param GoodsBillApply 总单
	 * @param updates 细单更新数据
	 * @param deletes 细单删除数据
	 * @return  1 正常   -1其他问题
	 * 			
	 */
	public int updateKSRK(GoodsBillApply billGoodsApply,GridSaveModel model){
		try {
			//总单
			billGoodsApply.update();
			//细单
			if (model!=null){
				List<GoodsBillApplyDtl> deletes = model.deletes(GoodsBillApplyDtl.class);
				List<GoodsBillApplyDtl> updates = model.updates(GoodsBillApplyDtl.class);
				List<GoodsBillApplyDtl> inserts = model.inserts(GoodsBillApplyDtl.class);
				for(GoodsBillApplyDtl goodsBillApplyDtl:deletes){
					goodsBillApplyDtl.delete();
				}
				for(GoodsBillApplyDtl goodsBillApplyDtl:updates){
					goodsBillApplyDtl.setQty(goodsBillApplyDtl.getQty().abs().negate());//确保退库数量是负数
					goodsBillApplyDtl.update();
				}
				for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
					String dtlid=goodsBillApplyDtl.newId().toString();
					goodsBillApplyDtl.setQty(goodsBillApplyDtl.getQty().abs().negate());//确保退库数量是负数
					goodsBillApplyDtl.setDtlid(dtlid);
					goodsBillApplyDtl.setDocid(billGoodsApply.getDocid());
					goodsBillApplyDtl.insert();
				}
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
	 * 更新单据  退库
	 * @param GoodsBillApply 总单
	 * @param updates 细单更新数据
	 * @param deletes 细单删除数据
	 * @return  1 正常   -1其他问题
	 * 			
	 */
	public int updateKSTK(GoodsBillApply billGoodsApply,GridSaveModel model){
		try {
			//总单
			billGoodsApply.update();
			//细单
			if(model!=null){
				List<GoodsBillApplyDtl> deletes = model.deletes(GoodsBillApplyDtl.class);
				List<GoodsBillApplyDtl> updates = model.updates(GoodsBillApplyDtl.class);
				List<GoodsBillApplyDtl> inserts = model.inserts(GoodsBillApplyDtl.class);
				for(GoodsBillApplyDtl goodsBillApplyDtl:deletes){
					goodsBillApplyDtl.delete();
				}
				for(GoodsBillApplyDtl goodsBillApplyDtl:updates){
					goodsBillApplyDtl.update();
				}
				for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
					String dtlid=goodsBillApplyDtl.newId().toString();
					goodsBillApplyDtl.setDtlid(dtlid);
					goodsBillApplyDtl.setDocid(billGoodsApply.getDocid());
					goodsBillApplyDtl.setRealqty(goodsBillApplyDtl.getQty());
					goodsBillApplyDtl.insert();
				}
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
	 * 科室申请
	 * 申请单验证
	 * 一级库库存是否满足出库数量
	 */
	public HashMap<String, Object>  billOutStockValid(String docid){
		HashMap<String, Object> result = new HashMap<String, Object>();
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
		for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
			if(!stockService.isValidStockQty(goodsBillApply.getStoreid(), 
					goodsBillApplyDtl.getGoodsid(), 
					goodsBillApplyDtl.getGoodsdtlid(), 
					goodsBillApplyDtl.getRealqty())){
				result.put("success", false);
				result.put("msg", "《"+goodsBillApplyDtl.getGoodsname()+"》库存不够,不能出库");
				return result;
			}
		}
		
		result.put("success", true);
		result.put("msg", "");
		return result;
	}
	
	/**
	 * 科室申请
	 * 根据申请单    一级库出库
	 * @param docid
	 * @return  1 成功 <0 失败
	 */
	public int billOutStock(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
		try {
			/*
			 * 总单
			 */
			//N临时F作废C审核S发送A接收O出库D入库
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_O);
			goodsBillApply.setAcceptid(user.getUserid());
			goodsBillApply.setAcceptdate(sdf.format(date));
			goodsBillApply.setIoid(user.getUserid());
			goodsBillApply.setIodate(sdf.format(date));
			goodsBillApply.update();
			/*
			 * 细单
			 */
			for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
				Integer rtn=stockService.goodsOutStock(
						goodsBillApply.getStoreid(), 
						goodsBillApplyDtl.getGoodsid(), 
						goodsBillApplyDtl.getGoodsdtlid(), 
						goodsBillApplyDtl.getRealqty().abs().negate(), //正数相反数
						goodsBillApplyDtl.getDtlid(), 
						goodsBillApply.getBilltype(),
						ConstantsUtil.TABLENAME_GOODS_BILL_APPLY_DTL,
						goodsBillApply.getBillname());
				if(rtn<0){
					DBManager.rollbackAll();
					return rtn;
				}
			}
			
			/*
			 * 提交
			 */
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		return 1;
	}
	
	/**
	 * 科室申请
	 * 根据申请单   二级库入库
	 * @param docid
	 * @return 1 成功  -9单据状态不正确 -1其他失败 -2入库数量不能小于或等于0
	 */
	public int billInstock2(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
		//校验单据状态
		if(!goodsBillApply.getStatus().equals(ConstantsUtil.APPLY_STATUS_O)){
			return -9;
		}
		
		try {
			/*
			 * 总单
			 */
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_D);
			goodsBillApply.setIo2date(sdf.format(date));
			goodsBillApply.setIo2id(user.getUserid());
			goodsBillApply.update();
			/*
			 * 细单
			 */
			for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
				//一条申请单明细对应多条IO
				List<GoodsStockIo> goodsStockIos=GoodsStockIo.INSTANCE.query("BILLTABLE='GOODS_BILL_APPLY_DTL' and billdtlid=?", goodsBillApplyDtl.getDtlid());
				for (GoodsStockIo goodsStockIo : goodsStockIos) {
					Integer rtn=stock2Service.goodsInStock2(
							goodsBillApply.getDeptid(), 
							goodsStockIo.getGoodsid(), 
							goodsStockIo.getGoodsdtlid(), 
							goodsStockIo.getLotid(), 
							"", //pos
							goodsStockIo.getQty().abs(), //对于一级库是出库,数量为负数,此处需要去绝对值
							goodsStockIo.getBilldtlid(), 
							ConstantsUtil.APPLY_TYPE_KSSQ, 
							"GOODS_BILL_APPLY_DTL", 
							"申请入库入二级库");
					if(rtn<0){
						DBManager.rollbackAll();
						return rtn;
					}
				}
				
			}
			/*
			 * 提交
			 */
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		
		
		return 1;
	}
	
	/**
	 * 科室退库
	 * 根据申请单    二级库出库
	 * @param docid
	 * @return 1 成功  -9单据状态不正确 -1其他失败 -2入库数量不能大于或等于0
	 */
	public int billOutStock2(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
		//校验单据状态
		if(!goodsBillApply.getStatus().equals(ConstantsUtil.APPLY_STATUS_C)){
			return -9;
		}
		for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
			//-1 小于 0等于 or 1 大于
			if(goodsBillApplyDtl.getRealqty().compareTo(BigDecimal.ZERO)!=-1){ //大于或等于0
				//入库数量不能大于或等于0
				return -2;
			}
		}
		
		try {
			/*
			 * 总单,二级库出库时间 
			 */
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_O);
			goodsBillApply.setSenddate(sdf.format(date));
			goodsBillApply.setSenderid(user.getUserid());
			goodsBillApply.setIo2date(sdf.format(date));
			goodsBillApply.setIo2id(user.getUserid());
			goodsBillApply.update();
			/*
			 * 细单
			 */
			for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
				stock2Service.goodsOutStock2(
						goodsBillApply.getDeptid(), 
						goodsBillApplyDtl.getGoodsid(), 
						goodsBillApplyDtl.getGoodsdtlid(), 
						goodsBillApplyDtl.getQty(), /*数量必须是负数*/
						goodsBillApplyDtl.getDtlid(), 
						"SQTK", 
						ConstantsUtil.TABLENAME_GOODS_BILL_APPLY_DTL, 
						"申请退库出二级库");
			}
			/*
			 * 提交
			 */
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		return 1;
	}
	/**
	 * 科室退库 
	 * 根据申请单  一级库入库
	 * @param docid
	 * @return  1 成功 <0 失败
	 */
	public int billInStock(String docid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		List<GoodsBillApplyDtl> list=GoodsBillApplyDtl.INSTANCE.query("docid=?", docid);
		
		try {
			/*
			 * 总单
			 */
			//N临时F作废C审核S发送A接收O出库D入库
			goodsBillApply.setStatus(ConstantsUtil.APPLY_STATUS_D);
			goodsBillApply.setAcceptid(user.getUserid());
			goodsBillApply.setAcceptdate(sdf.format(date));
			goodsBillApply.setIoid(user.getUserid());
			goodsBillApply.setIodate(sdf.format(date));
			goodsBillApply.update();
			/*
			 * 细单
			 */
			for (GoodsBillApplyDtl goodsBillApplyDtl : list) {
				/*dtl对应的io记录*/
				List<GoodsStock2Io> listIO=GoodsStock2Io.INSTANCE.query(" BILLDTLID=? and billtable='GOODS_BILL_APPLY_DTL' ", goodsBillApplyDtl.getDtlid());
				for (GoodsStock2Io goodsStock2Io : listIO) {
					Integer rtn=stockService.goodsInStock(
							goodsBillApply.getStoreid(), 
							goodsStock2Io.getGoodsid(), 
							goodsStock2Io.getGoodsdtlid(), 
							goodsStock2Io.getLotid(), 
							"", //pos
							goodsStock2Io.getQty().abs(), //入库数量 正数
							goodsStock2Io.getBilldtlid(), 
							goodsStock2Io.getBilltype(), 
							goodsStock2Io.getBilltable(), 
							"科室退库入库");
					if(rtn<0){
						DBManager.rollbackAll();
						return rtn;
					}
				}
			}
			
			/*
			 * 提交
			 */
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		return 1;
	}
}
