package com.cms.service.biz.supply.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.supply.SuComGoodsClassV;
import com.cms.model.biz.supply.SuComGoodsV;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.supply.ISuGoodsOrgService;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;
import com.cms.util.biz.model.MsgModel;

import my.dao.annotation.Column;
import my.dao.mapping.ColumnType;
import my.dao.pool.DBManager;
import net.sf.json.JSONArray;

@Service
public class SuGoodsOrgServiceImpl implements ISuGoodsOrgService{

	@Override
	public GridDataModel<SuGoodsOrg> gridDataOrgByClassidNameIdSpell(String orgid,String classcode, String goodsname,String goodsid, String spell) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		filter.append(" and orgid = ?");
		params.add(orgid);
		
		if (!classcode.equals("")) {
			filter.append(" and classcode like ?");
			params.add(classcode+"%");
		}
		if (!goodsname.equals("")) {
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsid.equals("")) {
			filter.append(" and goodsid = ?");
			params.add(goodsid);
		}
		if (!spell.equals("")) {
			filter.append(" and spell like ?");
			params.add("%"+spell+"%");
		}
		GridDataModel<SuGoodsOrg> gridDataModel = PageFactoryEasyUI.newPage(SuGoodsOrg.class, filter.toString(),
				" order by goodsid ", params.toArray());
		return gridDataModel;
	}
	/**
	 * 实现从英克货品字典同步到平台货品字典
	 */
	@SuppressWarnings("null")
	@Override
	public MsgModel importData(String orgid) {
		SuUser suUser=(SuUser)SessionHelpUtils.getSession().getAttribute("suUser");
		MsgModel msg=new MsgModel();
		Integer i=0;
		Integer u=0;
		boolean ub=false;
		try {
			//接口中需要有与当前用户的orgid对应的字典数据
			List<SuComGoodsV> list=SuComGoodsV.INSTANCE.query("orgid=? order by goodsid ",orgid);
			for (SuComGoodsV suComGoodsV : list) {
				SuGoodsOrg suGoodsOrg=SuGoodsOrg.INSTANCE.queryOne("orgid=? and goodsid=?", suComGoodsV.getOrgid(),suComGoodsV.getGoodsid());
				ub=false;
				if(suGoodsOrg != null){
					if(!Objects.equals(suGoodsOrg.getGoodsname(),suComGoodsV.getGoodsname())){
						suGoodsOrg.setGoodsname(suComGoodsV.getGoodsname());ub=true;}
					if(!Objects.equals(suGoodsOrg.getSpell(),suComGoodsV.getSpell())){
						suGoodsOrg.setSpell(suComGoodsV.getSpell());ub=true;}
					if(!Objects.equals(suGoodsOrg.getGoodstype(),suComGoodsV.getGoodstype())){
						suGoodsOrg.setGoodstype(suComGoodsV.getGoodstype());ub=true;}
					if(!Objects.equals(suGoodsOrg.getClasscode(),suComGoodsV.getClasscode())){
						suGoodsOrg.setClasscode(suComGoodsV.getClasscode());ub=true;}
					if(!Objects.equals(suGoodsOrg.getClassname(),suComGoodsV.getClassname())){
						suGoodsOrg.setClassname(suComGoodsV.getClassname());ub=true;}
					if(!Objects.equals(suGoodsOrg.getSpec(),suComGoodsV.getSpec())){
						suGoodsOrg.setSpec(suComGoodsV.getSpec());ub=true;}
					if(!Objects.equals(suGoodsOrg.getUnit(),suComGoodsV.getUnit())){
						suGoodsOrg.setUnit(suComGoodsV.getUnit());ub=true;}
					if(!Objects.equals(suGoodsOrg.getFactory(),suComGoodsV.getFactory())){
						suGoodsOrg.setFactory(suComGoodsV.getFactory());ub=true;}
					if(!Objects.equals(suGoodsOrg.getProdarea(),suComGoodsV.getProdarea())){
						suGoodsOrg.setProdarea(suComGoodsV.getProdarea());ub=true;}
					if(ub){suGoodsOrg.update();u++;}
				}else{
					suGoodsOrg=new SuGoodsOrg();
					BeanUtils.copyProperties(suComGoodsV, suGoodsOrg);
					suGoodsOrg.insert();
					i++;
				}
			}
			DBManager.commitAll();
			msg.setMsg("同步完成！");
			msg.setContent("更新:"+u+"条 ,插入:"+i+"条");
			msg.setCode("1");
		} catch (Exception e) {
			DBManager.rollbackAll();
			msg.setMsg("同步失败！");
			msg.setContent(e.getMessage());
			msg.setCode("-1");
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<SuComGoodsClassV> getSuComGoodsClass(String orgid){
		SuUser suUser=(SuUser)SessionHelpUtils.getSession().getAttribute("suUser");
		return SuComGoodsClassV.INSTANCE.query("orgid=? order by orgid,classcode",orgid);
	}
	@Override
	public SuGoodsOrg getByOrgidGoodsid(String orgid,String goodsid) {
		
		return SuGoodsOrg.INSTANCE.queryOne(" orgid=? and goodsid=? ", orgid,goodsid);
	}
}
