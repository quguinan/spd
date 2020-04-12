package com.cms.controller.biz.rpt;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.ColumnsEasyuiBean;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.rpt.RptCustom;
import com.cms.model.biz.rpt.RptCustomColumn;
import com.cms.model.biz.rpt.RptCustomEvent;
import com.cms.model.biz.rpt.RptCustomProperty;
import com.cms.model.biz.rpt.RptEvents;
import com.cms.model.biz.rpt.RptProperties;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactoryEasyUI;

import my.dao.pool.DBManager;
import my.dao.utils.Record;
import my.util.MD5Util;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

@Controller
@RequestMapping("/cms/biz")
public class RptCustomController extends BaseController{
	@RequestMapping("rpt/setting")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/rpt/rptSetting";
	}
	/**
	 * 返回报表的预览页面
	 * @param m
	 * @param session
	 * @return
	 */
	@RequestMapping("rpt/rptDisplay")
	public String rptDisplay(Model m,HttpSession session) {
		String docid=param("docid","");
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		m.addAttribute("docid", docid);
		return "cms/biz/rpt/rptDisplay";
	}
	/**
	 * 报表的预览页面--查询数据
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/displayGridData")
	public JSONObject displayGridData() {
		
		JSONObject joData=new JSONObject();
		
		String docid=param("docid","");
		RptCustom rptCustom=RptCustom.INSTANCE.queryOne("docid=?", docid);
		String sql=rptCustom.getSqltext();
		Connection conn =DBManager.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs =null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//ResultSetMetaData data = rs.getMetaData();
			List<Map<String,String>> list=new ArrayList<>();
			while (rs.next()) {
				ResultSetMetaData rsMeta=rs.getMetaData();
			    int columnCount=rsMeta.getColumnCount();
			    //Record record=new Record();
			    Map<String,String> record=new HashMap<>();
			    for (int i=1; i<=columnCount; i++) {
			    	record.put((rsMeta.getColumnLabel(i)==null?"":rsMeta.getColumnLabel(i)).toLowerCase(), rs.getString(i)==null?"":rs.getString(i));
			    }
			    list.add(record);
			}
			
			joData.put("total", list.size());
			joData.put("rows", list);
		}catch (Exception  e) {
			e.printStackTrace();
		}finally { 
			try { if (rs != null) rs.close(); } catch (Exception e) {};
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
			try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		
		return joData;
	}
	/**
	 * SQL语句解析
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/createGridData")
	public HashMap<String, Object> createGridData() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		//dg的所有属性
		JSONObject jo=new JSONObject();
		
		List<RptCustomProperty> RptCustomProperties=RptCustomProperty.INSTANCE.query("docid=?", docid);
		for (RptCustomProperty property : RptCustomProperties) {
			jo.put(property.getProname(), property.getProvalue());
		}
		//单独处理columns
		List<RptCustomColumn> columns=RptCustomColumn.INSTANCE.query("docid=?", docid);
		JSONArray jacolumns=new JSONArray();
		for (RptCustomColumn column : columns) {
			JSONObject joColumn=JSONObject.fromObject(column);
			jacolumns.add(joColumn);
		}
		JSONArray rtn=new JSONArray();
		rtn.add(jacolumns);
		jo.put("columns", rtn);
		//单独处理toolbar
		/*JSONArray jaToolbar=new JSONArray();
		JSONObject jo1=new JSONObject();
		jo1.put("text", "查询");
		jo1.put("iconCls", "icon_zoom");
		jo1.put("handler", "");
		JSONObject jo2=new JSONObject();
		jo2.put("text", "重置");
		jo2.put("iconCls", "icon_arrow_refresh");
		jo2.put("handler", "");
		jaToolbar.add(jo1);
		jaToolbar.add(jo2);
		jo.put("toolbar", jaToolbar);*/
		jo.put("toolbar", "#toolbar");

		result.put("properties", jo);
		result.put("msg", "更新成功");
		result.put("success", true);
		return result;
	}
	/**
	 * SQL语句解析
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/sqlAnalyse")
	public JSONObject sqlAnalyse(Model m,HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String sql=param("sql","")+"  ";//后边空格必须加
//		String[] str=sql.split("\\?");
//		Integer count_param=str.length-1;
		Connection conn =DBManager.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs =null;
		try {
			/*
			 * 报表字段
			 */
			List<ColumnsEasyuiBean> list1=new ArrayList<ColumnsEasyuiBean>();
			stmt = conn.prepareStatement(sql);
			//填充"?"
//			for (int i = 1; i <= count_param ; i++) { 
//				stmt.setObject(i, ""); 
//			}
			
			
			rs = stmt.executeQuery();
			ResultSetMetaData data = rs.getMetaData();
			for (int i = 1; i <= data.getColumnCount(); i++) {
				ColumnsEasyuiBean ceBean=new ColumnsEasyuiBean();
				ceBean.setField(data.getColumnName(i));
				ceBean.setTitle(data.getColumnName(i));//标题默认是字段名
				ceBean.setAlign("left");
				ceBean.setHalign("center");
				ceBean.setHidden((Boolean) null);
				ceBean.setWidth(100);
				ceBean.setSortable((Boolean) null);
				ceBean.setOrder("");
				list1.add(ceBean);
			}
			/*
			 * 查询条件
			 */
			Statement statement = CCJSqlParserUtil.parse(sql);
			if (statement instanceof Select) {
				//获得Select对象 
	        	Select select = (Select)CCJSqlParserUtil.parse(sql);
	            SelectBody selectBody = select.getSelectBody();
	            PlainSelect plainSelect = (PlainSelect)selectBody;
	            //获得where条件表达式
	            Expression where = plainSelect.getWhere();
	            //BinaryExpression包括了整个where条件，
	            //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
//	            if(where instanceof BinaryExpression){
//	            	Expression leftExpression = ((BinaryExpression) where).getLeftExpression();
//	            	Expression rightExpression = ((BinaryExpression) where).getRightExpression();
//	            	System.out.println(leftExpression);
//	            	System.out.println(rightExpression);
//	            }
			}
			
			
			
			List<ColumnsEasyuiBean> list2=new ArrayList<ColumnsEasyuiBean>();
			
			/*
			 * 返回页面
			 */
			result.put("msg", "操作成功!");
			result.put("success", true);
			result.put("data_column", JSONArray.fromObject(list1));
			result.put("data_filter", JSONArray.fromObject(list2));
		}	catch (Exception  e) {
			e.printStackTrace();
			result.put("msg", e.getMessage());
			result.put("success", false);
		}finally { 
			try { if (rs != null) rs.close(); } catch (Exception e) {};
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
			try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		
		
		return JSONObject.fromObject(result);
	}
	/**
	 * 保存
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/save")
	public JSONObject save(Model m,HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String jsonDoc=param("jsonDoc","");
		String jsonColumns=param("jsonColumns","");
		String jsonProperties=param("jsonProperties","");
		if(docid.equals("")){
			result=add(jsonDoc, jsonColumns, jsonProperties);
		}else{
			result=update(jsonDoc, jsonColumns, jsonProperties);
		}
		
		return JSONObject.fromObject(result);
	}
	//新增
	public HashMap<String, Object> add(String jsonDoc,String jsonColumns,String jsonProperties){
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid="";
		try {
			/*
			 * 新增总单DOC
			 */
			RptCustom rpt=new RptCustom();
			JSONArray jaDoc=JSONArray.fromObject(jsonDoc);
			for (int i = 0; i < jaDoc.size(); i++) {
				switch((String) jaDoc.getJSONObject(i).get("name")){
				    case "docid" :
				        docid=RptCustom.INSTANCE.newId().toString();
				        rpt.setDocid(docid);
				        break; 
				    case "rptname" :
				        rpt.setRptname((String)jaDoc.getJSONObject(i).get("value"));
				        break; 
				    case "sqltext" :
					    rpt.setSqltext((String)jaDoc.getJSONObject(i).get("value"));
					    break; 
				    default : //可选
				        throw new Exception("总单数据错误！");
				} 
			}
			
			rpt.insert();
			
			/*
			 * 新增Column
			 */
			JSONArray jaColumn=JSONArray.fromObject(jsonColumns);
			for (Object obj : jaColumn) {
				RptCustomColumn rptCustomColumn=(RptCustomColumn)JSONObject.toBean((JSONObject) obj,RptCustomColumn.class);
				rptCustomColumn.setDocid(docid);
				rptCustomColumn.setDtlid(rptCustomColumn.newId().toString());
				rptCustomColumn.insert();
			}
			
			/*
			 * 新增条件filter
			 */
			
			
			/*
			 * 新增DG属性
			 */
			JSONArray jaProperty=JSONArray.fromObject(jsonProperties);
			for (Object obj : jaProperty) {
				RptCustomProperty rptCustomProperty=(RptCustomProperty)JSONObject.toBean((JSONObject) obj,RptCustomProperty.class);
				rptCustomProperty.setDocid(docid);
				rptCustomProperty.setDtlid(rptCustomProperty.newId().toString());
				rptCustomProperty.insert();
			}
			DBManager.commitAll();
			result.put("msg", "新增成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "新增失败!:"+e.getMessage());
		}finally {
			
		}
		return result;
	}
	//更新
	public HashMap<String, Object> update(String jsonDoc,String jsonColumns,String jsonProperties){
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			/*
			 * 更新总单DOC
			 */
			RptCustom rpt=new RptCustom();
			JSONArray jaDoc=JSONArray.fromObject(jsonDoc);
			for (int i = 0; i < jaDoc.size(); i++) {
				switch((String) jaDoc.getJSONObject(i).get("name")){
				    case "docid" :
				        rpt.setDocid((String)jaDoc.getJSONObject(i).get("value"));
				        break; 
				    case "rptname" :
				        rpt.setRptname((String)jaDoc.getJSONObject(i).get("value"));
				        break; 
				    case "sqltext" :
					    rpt.setSqltext((String)jaDoc.getJSONObject(i).get("value"));
					    break; 
				    default : //可选
				        throw new Exception("总单数据错误！");
				} 
			}
			rpt.update();
			
			/*
			 * 更新Column
			 */
			JSONArray jaColumn=JSONArray.fromObject(jsonColumns);
			for (Object obj : jaColumn) {
				RptCustomColumn rptCustomColumn=(RptCustomColumn)JSONObject.toBean((JSONObject) obj,RptCustomColumn.class);
				rptCustomColumn.update();
			}
			
			/*
			 * 更新条件filter
			 */
			
			
			/*
			 * 更新DG属性
			 */
			JSONArray jaProperty=JSONArray.fromObject(jsonProperties);
			for (Object obj : jaProperty) {
				RptCustomProperty rptCustomProperty=(RptCustomProperty)JSONObject.toBean((JSONObject) obj,RptCustomProperty.class);
				rptCustomProperty.update();
			}
			DBManager.commitAll();
			result.put("msg", "更新成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "更新失败!:"+e.getMessage());
		}finally {
			
		}
		
		
		return result;
	}
	/**
	 * Doc
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/gridDataDoc")
	public GridDataModel<RptCustom> gridDataDoc() {
		GridDataModel<RptCustom> gridDataModel=new GridDataModel<>();
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		gridDataModel=PageFactoryEasyUI.newPage(RptCustom.class, filter.toString()," order by docid ", params.toArray());
		return gridDataModel;
	}
	/**
	 * Properties
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/gridDataProperties")
	public GridDataModel<RptCustomProperty> gridDataProperties() {
		GridDataModel<RptCustomProperty> gridDataModel=new GridDataModel<>();
		String docid = param("docid", "");
		if(docid==""){
			List<RptProperties> list=RptProperties.INSTANCE.query("");
			List<RptCustomProperty> modelList=new ArrayList<RptCustomProperty>();
			for (RptProperties rptProperties : list) {
				modelList.add(new RptCustomProperty(rptProperties.getName(),rptProperties.getValuedefault()));
			}
			gridDataModel.setRows(modelList);
			gridDataModel.setTotal(modelList.size());
		}else{
			StringBuffer filter = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			filter.append(" and docid=? ");
			params.add(docid);
			gridDataModel=PageFactoryEasyUI.newPage(RptCustomProperty.class, filter.toString()," order by dtlid ", params.toArray());
		}
		
		return gridDataModel;
	}
	/**
	 * Events
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rpt/gridDataEvents")
	public GridDataModel<RptCustomEvent> getEvents() {
		GridDataModel<RptCustomEvent> gridDataModel=new GridDataModel<>();
		String docid = param("docid", "");
		if(docid==""){
			List<RptEvents> list=RptEvents.INSTANCE.query("");
			List<RptCustomEvent> modelList=new ArrayList<RptCustomEvent>();
			for (RptEvents rptEvent : list) {
				modelList.add(new RptCustomEvent(rptEvent.getName(),rptEvent.getEventparam(), ""));
			}
			gridDataModel.setRows(modelList);
			gridDataModel.setTotal(modelList.size());
		}else{
			StringBuffer filter = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			filter.append(" and docid=? ");
			params.add(docid);
			gridDataModel=PageFactoryEasyUI.newPage(RptCustomEvent.class, filter.toString()," order by dtlid ", params.toArray());
		}
		
		return gridDataModel;
	}
	
}
