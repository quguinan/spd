package com.cms.service.biz.supply;

import java.util.List;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.BIGoodsV;

public interface IGoodsService {

	public List<BIGoodsV> FindAll();

	public GridDataModel<BIGoodsV> getGoods(String spell, String goodsname,String fenlei);
}
