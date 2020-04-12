package com.cms.service.biz.supply;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SupGoods;

import net.sf.json.JSONObject;

public interface ISupGoodsServcie {

	/**
	 * 导入Excel 表格数据 add by wlm 2020年3月26日
	 * 
	 * @param file_upload
	 * @param supplyid
	 * @return
	 */
	public HashMap<String, Object> ImportGoods(MultipartFile file_upload, String supplyid);

	/**
	 * 根据条件查询出相应信息 add by wlm 2020年3月26日
	 * 
	 * @param goodsname
	 * @param spell
	 * @param supplyid
	 * @param goodsid
	 * @return
	 */
	public GridDataModel<SupGoods> getSupGoos(String goodsname, String spell, String supplyid, String goodsid);

	/**
	 * 保存对照 add by wlm 2020年3月26日
	 * 
	 * @param json
	 * @return
	 */
	public HashMap<String, Object> save(String json);

	/**
	 * 根据平台字典ID 查询到供应商对照的字典ID
	 * 
	 * @param supplyid
	 * @param goodsid
	 * @return
	 */
	public SupGoods getSupGoodsidByOrggoodsid(String supplyid, String goodsid);
}
