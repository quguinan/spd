package com.cms.service.biz.supply;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuGoodsSupply;
import com.cms.util.biz.model.MsgModel;

import net.sf.json.JSONArray;

public interface ISuGoodsSupplyService {
	
	
	/**
	 *  map之前检查平台货品是否已经被供应商对照过
	 * @param orgid
	 * @param supplyid
	 * @param sugoodsid
	 * @param goodsid
	 * @return 被对照的supplyid 或者 -1没有被对照过
	 */
	public Integer  checkBeforeMapData(String orgid,String supplyid,String sugoodsid,String goodsid);
	/**
	 * 对照数据
	 * @param orgid		机构id
	 * @param supplyid	供应商id
	 * @param sugoodsid	供应商货品ID
	 * @param goodsid	平台货品ID	
	 * @return 			1成功 0失败 -1系统错误	
	 */
	public int mapData(String orgid,String supplyid,String sugoodsid,String goodsid);
	/**
	 * 取消对照
	 * @param orgid		机构id
	 * @param supplyid	供应商id
	 * @param sugoodsid	供应商货品ID
	 * @return 			1成功 0失败 -1系统错误
	 */
	public int unMapData(String orgid,String supplyid,String sugoodsid);
	/**
	 * 
	 * @param supplyname
	 * @param spell
	 * @return
	 */
	public GridDataModel<SuGoodsSupply> gridDataSupplyByNameSpell(String orgid,String suppluid,String sugoodsid, String sugoodsname, String spell);
	/**
	 * 可以从外部获取数据源,导入到本地
	 * @param importModel	
	 * @return MsgModel	返回接口固定格式
	 */
	public MsgModel importData(JSONArray importModel);

	/**
	 * 根据供应商id,平台goodsid
	 * 检查判断是否做过对照
	 * @param supplyid
	 * @param orgid
	 * @param goodsid  此处是平台货品id,非供应商货品id--切记
	 * @return  返回值空 说明没有呗对照
	 */
	public SuGoodsSupply getBySupplyidOrgidGoodsid(String supplyid,String orgid,String goodsid);
	
	/**
	 * 
	 * @param supplyid
	 * @param orgid
	 * @param sugoodsid 此处是供应商货品id--切记
	 * @return  
	 */
	public SuGoodsSupply getBySupplyidOrgidSugoodsid(String supplyid,String orgid,String sugoodsid);
}
