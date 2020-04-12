package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.BIGoodsV;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.service.biz.supply.IGoodsService;
import com.cms.util.biz.PageFactoryEasyUI;

@Service
public class GoodsServiceImpl implements IGoodsService {

	@Override
	public List<BIGoodsV> FindAll() {
		// TODO Auto-generated method stub
		return BIGoodsV.INSTANCE.query("");
	}

	@Override
	public GridDataModel<BIGoodsV> getGoods(String spell, String goodsname, String fenlei) {
		// TODO Auto-generated method stub
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!spell.equals("")) {
			filter.append("goodspinyin like ?");
			params.add("%" + spell.toUpperCase() + "%");
		}
		if (!goodsname.equals("")) {
			filter.append(" and goodsname like ?");
			params.add("%" + goodsname + "%");
		}
		if (!fenlei.equals("")) {
			filter.append(" and category = ?");
			params.add(fenlei);
		}

		return PageFactoryEasyUI.newPage(BIGoodsV.class, filter.toString(), " order by goodsid ", params.toArray());
	}

}
