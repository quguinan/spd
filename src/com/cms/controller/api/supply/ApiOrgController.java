package com.cms.controller.api.supply;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.model.biz.supply.SuBillOrderDoc;
import com.cms.model.biz.supply.SuBillOrderDtl;
import com.cms.model.biz.supply.SuBillSendDoc;
import com.cms.model.biz.supply.SuBillSendDtl;
import com.cms.model.biz.supply.SuGoodsOrg;
import com.cms.model.biz.supply.SuGoodsSupply;
import com.cms.model.biz.supply.SuOrg;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SupGoods;
import com.cms.service.biz.supply.ISuBillOrderService;
import com.cms.service.biz.supply.ISuBillSendService;
import com.cms.service.biz.supply.ISuGoodsOrgService;
import com.cms.service.biz.supply.ISuGoodsSupplyService;
import com.cms.service.biz.supply.ISuOrgService;
import com.cms.service.biz.supply.ISupGoodsServcie;
import com.cms.util.biz.model.MsgModel;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 平台对应各机构(医院)提供的接口
 * 
 * @author qgn
 *
 *         所有的返回格式统一为MsgModel转化JSONObject格式
 */
@SuppressWarnings("finally")
@Controller
@RequestMapping("/api/org")
public class ApiOrgController extends BaseController {

	@Autowired
	private ISuBillSendService suBillSendService;
	@Autowired
	private ISuBillOrderService suBillOrderService;
	@Autowired
	private ISuOrgService suOrgService;
	@Autowired
	ISupGoodsServcie supgoodsService;
	@Autowired
	private ISuGoodsOrgService suGoodsOrgService;

	/**
	 * 上传机构订单 post参数结构: 参数名称:order 参数值(结构): { "logname":"ZMJT", "password":"123456",
	 * "doc": { "supplyid":"333","ordtype":"1","signdate":"2","signaddress":"3",
	 * "signman":"4","validbegdate":"","validenddate":"","settletype":"5",
	 * "prepay":"250","total":"260","memo":"备注","storerid":"1000","sourceid":"2000","orderid":"101",
	 * "dtl": [
	 * {"sortno":"1","goodsid":"10001","orderqty":"10","unit":"克","unitprice":"1.25","total":"1000","memo":"备注1","orderdtlid":"11"},
	 * {"sortno":"3","goodsid":"10002","orderqty":"10","unit":"克","unitprice":"1.25","total":"1000","memo":"备注2","orderdtlid":"12"},
	 * {"sortno":"4","goodsid":"10003","orderqty":"10","unit":"克","unitprice":"1.25","total":"1000","memo":"备注3","orderdtlid":"13"}
	 * ] } }
	 * 
	 * @return MsgModel
	 */
	@ResponseBody
	@RequestMapping(value = "uploadOrderBillOrg", method = RequestMethod.POST)
	public JSONObject uploadOrderBillOrg() {
		MsgModel result = new MsgModel("", "", "");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String jsondata = param("jsondata", "");
		JSONObject jsonOrder = JSONObject.fromObject(jsondata);
		try {
			/*******************************
			 * 解析参数
			 *******************************/
			JSONObject jsonDoc = jsonOrder.getJSONObject("doc");
			JSONArray jsonDtl = jsonDoc.getJSONArray("dtl");
			String supplyid = jsonDoc.getString("supplyid");
			String orgid = jsonDoc.getString("sourceid");
			/*******************************
			 * 校验
			 *******************************/

			/*******************************
			 * 处理doc
			 *******************************/
			SuBillOrderDoc billOrderDoc = new SuBillOrderDoc();
			jsonDoc.remove("dtl");
			billOrderDoc = (SuBillOrderDoc) JSONObject.toBean(jsonDoc, billOrderDoc.getClass());
			System.out.println("---------------------------------------" + billOrderDoc.getSignman());
			String newdocid = suBillOrderService.newDocId().toString();
			billOrderDoc.setDocid(newdocid);
			billOrderDoc.setDocno("ORDER" + sdf2.format(date) + "-" + newdocid);
			billOrderDoc.setOrgid(orgid);
			billOrderDoc.setIntype("1");// 数据来源:1接口2导入3录入
			billOrderDoc.setStatus("0");// 单据状态: -1作废,0新订单,1已审核,2已接收
			billOrderDoc.setCredate(sdf.format(date));
			// billOrderDoc.setUserid(userid);
			suBillOrderService.insert(billOrderDoc);

			/*******************************
			 * 处理dtl
			 *******************************/
			for (Object obj : jsonDtl) {
				SuBillOrderDtl billOrderDtl = new SuBillOrderDtl();
				billOrderDtl = (SuBillOrderDtl) JSONObject.toBean(JSONObject.fromObject(obj), billOrderDtl.getClass());
				billOrderDtl.setDtlid(suBillOrderService.newDtlId().toString());
				billOrderDtl.setDocid(newdocid);
//				SupGoods supgoods = supgoodsService.getSupGoodsidByOrggoodsid(supplyid, billOrderDtl.getGoodsid());
//				billOrderDtl.setSugoodsid(supgoods == null ? "" : supgoods.getSupgoodsid());
				suBillOrderService.insert(billOrderDtl);
			}
			// 提交
			DBManager.commitAll();
			result.setContent("上传订单成功!");
			result.setCode("1");
			result.setMsg("上传订单成功!");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(!result.getContent().equals("") ? result.getContent() : e.getMessage());
			result.setCode(!result.getCode().equals("") ? result.getCode() : "-1");
			result.setMsg(!result.getMsg().equals("") ? result.getMsg() : "系统异常");
			e.printStackTrace();
		} finally {
			System.out.println("uploadOrderBillsOrg");
			return JSONObject.fromObject(result);
		}
	}

//	 * @return MsgModel
//	 */
//	@ResponseBody
//	@RequestMapping(value="uploadOrderBillOrg",method = RequestMethod.POST)
//	public JSONObject uploadOrderBillOrg() {
//		MsgModel result=new MsgModel("","","");
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//		String jsondata=param("jsondata","");
//		JSONObject jsonOrder=JSONObject.fromObject(jsondata);
//		try {
//			/*******************************
//			 * 解析参数
//			 *******************************/
//			JSONObject jsonDoc=jsonOrder.getJSONObject("doc");
//			JSONArray jsonDtl=jsonDoc.getJSONArray("dtl");
//			String supplyid=jsonDoc.getString("supplyid");
//			/*******************************
//			 * 校验
//			 *******************************/
//			String logname=jsonOrder.getString("logname");
//			String password=jsonOrder.getString("password");
//			if(suOrgService.getByLogname(logname.toUpperCase())==null){
//				result.setCode("1001");
//				result.setMsg("用户不存在");
//				throw new Exception("用户不存在");
//			}
//			SuOrg suOrg=suOrgService.getByLognamePassword(logname.toUpperCase(), password);
//			if(suOrg==null){
//				result.setCode("1002");
//				result.setMsg("密码错误");
//				throw new Exception("密码错误");
//			}
//			String orgid=suOrg.getOrgid();
//			//如果sourceid不为空,总单的sourceid如果已经存在,不能重复进行上传
//			//如果sourceid为空,则不作判断
//			//在平台作废后可重新上传
//			String sourceid=jsonDoc.getString("sourceid");
//			int size=suBillOrderService.findByOrgidSupplyidSourceid(orgid,supplyid, sourceid).size();
//			if(!sourceid.equals("")&&size>0){
//				result.setCode("1003");
//				result.setMsg("单据重复上传!");
//				result.setContent("单据sourceid["+jsonDoc.getString("sourceid")+"]已上传!");
//				throw new Exception("单据重复上传!");
//			}
//			//明细中如果有在平台上<未同步>或<未对照>的货品id,则不能上传订单.
//			for (Object obj : jsonDtl) {
//				String goodsid=JSONObject.fromObject(obj).get("goodsid").toString();
//				SuGoodsOrg suGoodsOrg=suGoodsOrgService.getByOrgidGoodsid(orgid,goodsid);
//				SuGoodsSupply suGoodsSupply=suGoodsSupplyService.getBySupplyidOrgidGoodsid(supplyid, orgid, goodsid);
//				if(suGoodsOrg==null){
//					result.setCode("1004");
//					result.setMsg("货品未同步!");
//					result.setContent("货品ID["+goodsid+"]未同步!");
//					throw new Exception("平台货品未找到!");
//				}
//				if(suGoodsSupply==null){
//					result.setCode("1005");
//					result.setMsg("货品未对照!");
//					result.setContent("货品ID["+goodsid+"]未被对照!");
//					throw new Exception("平台货品未被对照!");
//				}
//			}
//			
//			/*******************************
//			 * 处理doc
//			 *******************************/
//			SuBillOrderDoc billOrderDoc=new SuBillOrderDoc();
//			jsonDoc.remove("dtl");
//			billOrderDoc=(SuBillOrderDoc) JSONObject.toBean(jsonDoc, billOrderDoc.getClass());
//			
//			String newdocid=suBillOrderService.newDocId().toString();
//			billOrderDoc.setDocid(newdocid);
//			billOrderDoc.setDocno("ORDER"+sdf2.format(date)+"-"+newdocid);
//			billOrderDoc.setOrgid(orgid);
//			billOrderDoc.setIntype("1");//数据来源:1接口2导入3录入
//			billOrderDoc.setStatus("0");//单据状态: -1作废,0新订单,1已审核,2已接收
//			billOrderDoc.setCredate(sdf.format(date));
//	//		billOrderDoc.setUserid(userid);
//			suBillOrderService.insert(billOrderDoc);
//
//			/*******************************
//			 * 处理dtl
//			 *******************************/
//			for (Object obj : jsonDtl) {
//				SuBillOrderDtl billOrderDtl=new SuBillOrderDtl();
//				billOrderDtl=(SuBillOrderDtl) JSONObject.toBean(JSONObject.fromObject(obj), billOrderDtl.getClass());
//				billOrderDtl.setDtlid(suBillOrderService.newDtlId().toString());
//				billOrderDtl.setDocid(newdocid); 
//				//添加goodsname,前边已经验证过,此处一定不为空
//				SuGoodsOrg suGoodsOrg=suGoodsOrgService.getByOrgidGoodsid(orgid,billOrderDtl.getGoodsid());
//				billOrderDtl.setGoodsname(suGoodsOrg.getGoodsname());
//				//对应的供应商货品id,没有对照则为空(此处原则上一定有对照,为对照不能上传)
//				SuGoodsSupply suGoodsSupply=suGoodsSupplyService
//						.getBySupplyidOrgidGoodsid(supplyid, orgid, billOrderDtl.getGoodsid());
//				billOrderDtl.setSugoodsid(suGoodsSupply==null?"":suGoodsSupply.getSugoodsid());
//				suBillOrderService.insert(billOrderDtl);
//			}
//			//提交
//			DBManager.commitAll();
//			result.setContent("上传订单成功!");
//			result.setCode("1");
//			result.setMsg("上传订单成功!");
//		} catch (Exception e) {
//			DBManager.rollbackAll();
//			result.setContent(!result.getContent().equals("")?result.getContent():e.getMessage());
//			result.setCode(!result.getCode().equals("")?result.getCode():"-1");
//			result.setMsg(!result.getMsg().equals("")?result.getMsg():"系统异常");
//			e.printStackTrace();
//		}finally{
//			System.out.println("uploadOrderBillsOrg");
//			return JSONObject.fromObject(result);
//		}
//	}
	/**
	 * 获取送货单信息 { "logname":"ZMJT", "password":"123456", "supplyid":"",
	 * //supplyid空则获取全部 "credate":"", }
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSendBillOrg", method = RequestMethod.POST)
	public JSONObject findSendBillOrg() {
		MsgModel result = new MsgModel("", "", "");
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		String jsondata = param("jsondata", "");
		JSONObject jsonInfo = JSONObject.fromObject(jsondata);
		try {
			/*******************************
			 * 解析参数
			 *******************************/
			String logname = jsonInfo.getString("logname");
			String password = jsonInfo.getString("password");
			String credate = jsonInfo.getString("credate");
			String supplyid = jsonInfo.getString("supplyid");
			if (suOrgService.getByLogname(logname.toUpperCase()) == null) {
				result.setCode("1001");
				result.setMsg("用户不存在");
				throw new Exception("用户不存在");
			}
			SuOrg suOrg = suOrgService.getByLognamePassword(logname.toUpperCase(), password);
			if (suOrg == null) {
				result.setCode("1002");
				result.setMsg("密码错误");
				throw new Exception("密码错误");
			}
			String orgid = suOrg.getOrgid();
			if (credate.equals("")) {// 默认查询当天
				credate = sdf3.format(date);
			}
			// 生成data
			List<SuBillSendDoc> listDoc = suBillSendService.findByOrgidSupplyidStatus(orgid, supplyid, "1");// 1获取已审核的单据2supplyid空则获取全部
			JSONArray ja = new JSONArray();
			for (SuBillSendDoc doc : listDoc) {
				List<SuBillSendDtl> listDtl = suBillSendService.findDtlByDocid(doc.getDocid());
				JSONObject jdoc = JSONObject.fromObject(doc);
				jdoc.put("dtl", listDtl);
				ja.add(jdoc);
			}
			result.setContent(ja.toString());
			// 提交
			DBManager.commitAll();
			result.setCode("1");
			result.setMsg("获取送货单成功![" + listDoc.size() + "条]");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(!result.getContent().equals("") ? result.getContent() : e.getMessage());
			result.setCode(!result.getCode().equals("") ? result.getCode() : "-1");
			result.setMsg(!result.getMsg().equals("") ? result.getMsg() : "系统异常");
			e.printStackTrace();
		} finally {
			System.out.println("findSendBillsOrg");
			return JSONObject.fromObject(result);
		}

	}

	/**
	 * 上传机构端的货品字典数据 参数名称:goods 参数值(结构): { "logname":"ZMJT", "password":"123456",
	 * "data": [
	 * {"goodsid":"10536","goodsname":"全效多酶清洗液AQ-504L","spell":"QXDMQXYAQ-504L","goodstype":"","classcode":"717","classname":"消毒材料","spec":"5L/瓶","unit":"瓶","factory":"广东中山","prodarea":""},
	 * {"goodsid":"10539","goodsname":"D-二聚体排除实验试剂盒II","spell":"D-EJTPCSYSJHII","goodstype":"","classcode":"713","classname":"化验材料","spec":"60T/盒","unit":"盒","factory":"法国梅里埃","prodarea":""},
	 * {"goodsid":"10541","goodsname":"穿刺活检针（单钩术前定位针）","spell":"CCHJZ（DGSQDWZ）","goodstype":"","classcode":"718","classname":"介入材料","spec":"BL
	 * 18/10","unit":"支","factory":"北京德迈科技发展有限公司","prodarea":""},
	 * {"goodsid":"10542","goodsname":"活检针（多用途千叶针）","spell":"HJZ（DYTQYZ）","goodstype":"","classcode":"718","classname":"介入材料","spec":"PA
	 * 23/08","unit":"支","factory":"北京德迈科技发展有限公司","prodarea":""},
	 * {"goodsid":"10543","goodsname":"一次性活检针","spell":"YCXHJZ","goodstype":"","classcode":"718","classname":"介入材料","spec":"SC16/16","unit":"支","factory":"北京德迈科技发展有限公司","prodarea":""}
	 * 
	 * ] }
	 * 
	 * @return MsgModel
	 */
	@ResponseBody
	@RequestMapping(value = "uploadGoodsDataOrg", method = RequestMethod.POST)
	public JSONObject uploadGoodsDataOrg() {
		MsgModel result = new MsgModel("", "", "");
		String json = param("jsondata", "");
		try {
			/*******************************
			 * 验证
			 *******************************/
			String logname = JSONObject.fromObject(json).getString("logname");
			String password = JSONObject.fromObject(json).getString("password");
			if (suOrgService.getByLogname(logname.toUpperCase()) == null) {
				result.setCode("1001");
				result.setMsg("用户不存在");
				throw new Exception("用户不存在");
			}
			SuOrg suOrg = suOrgService.getByLognamePassword(logname.toUpperCase(), password);
			if (suOrg == null) {
				result.setCode("1002");
				result.setMsg("密码错误");
				throw new Exception("密码错误");
			}
			String orgid = suOrg.getOrgid();
			/*******************************
			 * 解析参数
			 *******************************/
			JSONArray jsonGoods = JSONObject.fromObject(json).getJSONArray("data");
			/*******************************
			 * 处理SuGoodsOrg
			 *******************************/
			Integer i = 0;
			Integer u = 0;
			boolean ub = false;
			for (Object obj : jsonGoods) {

				String goodsid = JSONObject.fromObject(obj).get("goodsid").toString();
				String goodsname = JSONObject.fromObject(obj).get("goodsname").toString();
				String spell = JSONObject.fromObject(obj).get("spell").toString();
				String goodstype = JSONObject.fromObject(obj).get("goodstype").toString();
				String classcode = JSONObject.fromObject(obj).get("classcode").toString();
				String classname = JSONObject.fromObject(obj).get("classname").toString();
				String spec = JSONObject.fromObject(obj).get("spec").toString();
				String unit = JSONObject.fromObject(obj).get("unit").toString();
				String factory = JSONObject.fromObject(obj).get("factory").toString();
				String prodarea = JSONObject.fromObject(obj).get("prodarea").toString();
				System.out.println("正在处理:[" + goodsname + "]");
				SuGoodsOrg suGoodsOrg = suGoodsOrgService.getByOrgidGoodsid(orgid, goodsid);
				ub = false;
				if (suGoodsOrg != null) {
					if (!Objects.equals(suGoodsOrg.getGoodsname(), goodsname)) {
						suGoodsOrg.setGoodsname(goodsname);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getSpell() == null ? "" : suGoodsOrg.getSpell(), spell)) {
						suGoodsOrg.setSpell(spell);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getGoodstype() == null ? "" : suGoodsOrg.getGoodstype(),
							goodstype)) {
						suGoodsOrg.setGoodstype(goodstype);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getClasscode() == null ? "" : suGoodsOrg.getClasscode(),
							classcode)) {
						suGoodsOrg.setClasscode(classcode);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getClassname() == null ? "" : suGoodsOrg.getClassname(),
							classname)) {
						suGoodsOrg.setClassname(classname);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getSpec() == null ? "" : suGoodsOrg.getSpec(), spec)) {
						suGoodsOrg.setSpec(spec);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getUnit() == null ? "" : suGoodsOrg.getUnit(), unit)) {
						suGoodsOrg.setUnit(unit);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getFactory() == null ? "" : suGoodsOrg.getFactory(), factory)) {
						suGoodsOrg.setFactory(factory);
						ub = true;
					}
					if (!Objects.equals(suGoodsOrg.getProdarea() == null ? "" : suGoodsOrg.getProdarea(), prodarea)) {
						suGoodsOrg.setProdarea(prodarea);
						ub = true;
					}
					if (ub) {
						suGoodsOrg.update();
						u++;
					}
				} else {
					suGoodsOrg = new SuGoodsOrg();
					suGoodsOrg = (SuGoodsOrg) JSONObject.toBean(JSONObject.fromObject(obj), suGoodsOrg.getClass());
					suGoodsOrg.setOrgid(orgid);
					suGoodsOrg.insert();
					i++;
				}
			}
			// 提交
			DBManager.commitAll();
			result.setContent("本次处理共" + jsonGoods.size() + "条,其中插入:" + i + "条,更新:" + u + "条");
			result.setCode("1");
			result.setMsg("同步平台货品字典成功!");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(e.getMessage());
			result.setCode("-1");
			result.setMsg("系统异常");
			e.printStackTrace();
		} finally {
			System.out.println("uploadOrderBillsOrg");
			return JSONObject.fromObject(result);
		}
	}
}
