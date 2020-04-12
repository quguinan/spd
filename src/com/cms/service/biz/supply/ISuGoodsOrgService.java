package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComGoodsClassV;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.util.biz.model.MsgModel;

import net.sf.json.JSONArray;

public interface ISuGoodsOrgService {
	
	
	public SuGoodsOrg getByOrgidGoodsid(String orgid,String goodsid);
	/**
	 * @param orgid
	 * @param classid
	 * @param supplyname
	 * @param spell
	 * @return
	 */
	public GridDataModel<SuGoodsOrg> gridDataOrgByClassidNameIdSpell(String orgid,String classid,String goodsname,String goodsid, String spell);

	/**
	 * 可以从外部获取数据源,导入到本地
	 * @param importModel	
	 * @return MsgModel	返回接口固定格式
	 */
	public MsgModel importData(String orgid);
	/**
	 * 返回耗材类型列表
	 * @return
	 */
	public List<SuComGoodsClassV> getSuComGoodsClass(String orgid);
	
}
