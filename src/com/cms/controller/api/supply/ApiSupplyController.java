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
import com.cms.model.biz.supply.SuSupplyOrg;
import com.cms.service.biz.supply.ISuBillOrderService;
import com.cms.service.biz.supply.ISuBillSendService;
import com.cms.service.biz.supply.ISuGoodsOrgService;
import com.cms.service.biz.supply.ISuGoodsSupplyService;
import com.cms.service.biz.supply.ISuSupplyOrgService;
import com.cms.service.biz.supply.ISuSupplyService;
import com.cms.util.biz.model.MsgModel;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 平台对应各供应商提供的接口
 * 
 * @author qgn
 *
 *	所有的返回格式统一为MsgModel转化JSONObject格式
 */
@SuppressWarnings("finally")
@Controller
@RequestMapping("/api/supply")
public class ApiSupplyController extends BaseController  {
	
	
	@Autowired
	private ISuSupplyService suSupplyService;
	@Autowired
	private ISuGoodsSupplyService suGoodsSupplyService;
	@Autowired
	private ISuBillOrderService suBillOrderService;
	@Autowired
	private ISuBillSendService suBillSendService;
	@Autowired
	private ISuSupplyOrgService suSupplyOrgService;
	
	/**
	 * 获取订单信息
	 * @return 
	 * 
	 * {
		  "logname":"HBWS",
		  "password":"123456",
		  "credate":"",
		}
	 */
	
	@ResponseBody
	@RequestMapping(value="findOrderBillsSupply",method = RequestMethod.POST)
	public JSONObject findOrderBillsSupply() {
		MsgModel result=new MsgModel("","","");
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		String jsondata=param("jsondata","");
		JSONObject jsonInfo=JSONObject.fromObject(jsondata);
		try {
			/*******************************
			 * 解析参数
			 *******************************/
			String logname=jsonInfo.getString("logname");
			String password=jsonInfo.getString("password");
			String credate=jsonInfo.getString("credate");
			if(suSupplyOrgService.getByLogname(logname.toUpperCase())==null){ 
				result.setCode("1001");
				result.setMsg("用户不存在");
				throw new Exception("用户不存在");
			}
			SuSupplyOrg sSupplyOrg=suSupplyOrgService.getByLognamePassword(logname.toUpperCase(), password);
			if(sSupplyOrg==null){
				result.setCode("1002");
				result.setMsg("密码错误");
				throw new Exception("密码错误");
			}
			
			String orgid=sSupplyOrg.getOrgid();
			String supplyid=sSupplyOrg.getSupplyid();
			if(credate.equals("")){//默认查询当天
				credate=sdf3.format(date);
			}
			//生成data
			List<SuBillOrderDoc> listDoc=suBillOrderService.findByOrgidSupplyidStatus(orgid, supplyid,"1"); //获取已审核的单据
			JSONArray ja=new JSONArray();
			for (SuBillOrderDoc doc : listDoc) {
				List<SuBillOrderDtl> listDtl=suBillOrderService.findDtlByDocid(doc.getDocid());
				JSONObject jdoc=JSONObject.fromObject(doc);
				jdoc.put("dtl", listDtl);
				ja.add(jdoc);
			}
			result.setContent(ja.toString());
			//提交
			DBManager.commitAll();
			result.setCode("1");
			result.setMsg("获取订单成功!["+listDoc.size()+"条]");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(!result.getContent().equals("")?result.getContent():e.getMessage());
			result.setCode(!result.getCode().equals("")?result.getCode():"-1");
			result.setMsg(!result.getMsg().equals("")?result.getMsg():"系统异常");
			e.printStackTrace();
		}finally {
			System.out.println("findOrderBillsSupply");
			return JSONObject.fromObject(result);
		}
	}
	/**
	 * 上传送货单信息
	 * post参数结构:
	 * 参数名称:send
	 * 参数值(结构):
	 * 
	 * {
	  	"logname":"HBWS",
	  	"password":"123456",
		"doc":
			{
				"sendtype":"1","sendaddress":"中美医院库房","senddatetime":"2019-12-21","memo":"备注","orderid":"888","sourceid":"3000","sendid":"666",
				"dtl":
					[
						{"sortno":"1","sugoodsid":"7001","sendqty":"10","unit":"盒","unitprice":"100","total":"1000","memo":"备注1","batchno":"NO01","expirydate":"2020-12-20","orderdtlid":"12","senddtlid":"55"},                                                              
						{"sortno":"2","sugoodsid":"7002","sendqty":"20","unit":"箱","unitprice":"200","total":"4000","memo":"备注2","batchno":"NO02","expirydate":"2022-12-12","orderdtlid":"13","senddtlid":"66"},
						{"sortno":"3","sugoodsid":"7003","sendqty":"30","unit":"袋","unitprice":"300","total":"9000","memo":"备注3","batchno":"NO03","expirydate":"2023-12-12","orderdtlid":"14","senddtlid":"77"}
					]
			}
		}
	 * @return MsgModel
	 */
	@ResponseBody
	@RequestMapping(value="uploadSendBillsSupply",method = RequestMethod.POST)
	public JSONObject uploadSendBillsSupply() {
		MsgModel result=new MsgModel("","","");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String jsondata=param("jsondata","");
		JSONObject jsonSend=JSONObject.fromObject(jsondata);
		try {
			/*******************************
			 * 解析参数
			 *******************************/
			JSONObject jsonDoc=jsonSend.getJSONObject("doc");
			JSONArray jsonDtl=jsonDoc.getJSONArray("dtl");
			/*******************************
			 * 校验
			 *******************************/
			String logname=jsonSend.getString("logname");
			String password=jsonSend.getString("password");
			if(suSupplyOrgService.getByLogname(logname.toUpperCase())==null){
				result.setCode("1001");
				result.setMsg("用户不存在");
				throw new Exception("用户不存在");
			}
			SuSupplyOrg suSupplyOrg=suSupplyOrgService.getByLognamePassword(logname.toUpperCase(), password);
			if(suSupplyOrg==null){
				result.setCode("1002");
				result.setMsg("密码错误");
				throw new Exception("密码错误");
			}
			String orgid=suSupplyOrg.getOrgid();
			String supplyid=suSupplyOrg.getSupplyid();
			//如果sourceid不为空,总单的sourceid如果已经存在,不能重复进行上传
			//如果sourceid为空,则不作判断
			//在平台作废后可重新上传
			String sourceid=jsonDoc.getString("sourceid");
			int size=suBillSendService.findByOrgidSupplyidSourceid(orgid,supplyid, sourceid).size();
			if(!sourceid.equals("")&&size>0){
				result.setCode("1003");
				result.setMsg("单据重复上传!");
				result.setContent("单据sourceid["+jsonDoc.getString("sourceid")+"]已上传!");
				throw new Exception("单据重复上传!");
			}
			//明细中如果有在平台上未同步的供应商货品id,则不能上传订单.
			for (Object obj : jsonDtl) {
				String sugoodsid=JSONObject.fromObject(obj).get("sugoodsid").toString();
				SuGoodsSupply suGoodsSupply=suGoodsSupplyService.getBySupplyidOrgidSugoodsid(supplyid,orgid,sugoodsid);
				if(suGoodsSupply==null){
					result.setCode("1004");
					result.setMsg("货品未同步!");
					result.setContent("货品ID["+sugoodsid+"]未同步!");
					throw new Exception("供应商货品未同步!");
				}
				if(suGoodsSupply.getGoodsid()==null){
					result.setCode("1005");
					result.setMsg("货品未对照!");
					result.setContent("货品ID["+sugoodsid+"]未被对照!");
					throw new Exception("供应商货品未被对照!");
				}
			}
			/*******************************
			 * 处理doc
			 *******************************/
			SuBillSendDoc billSendDoc=new SuBillSendDoc();
			jsonDoc.remove("dtl");
			billSendDoc=(SuBillSendDoc) JSONObject.toBean(jsonDoc, billSendDoc.getClass());
			
			String newdocid=suBillSendService.newDocId().toString();
			billSendDoc.setDocid(newdocid);
			billSendDoc.setDocno("SEND"+sdf2.format(date)+"-"+newdocid);
			billSendDoc.setOrgid(orgid);
			billSendDoc.setSupplyid(supplyid);
			billSendDoc.setIntype("1");//数据来源:1接口2导入3录入
			billSendDoc.setStatus("0");//单据状态: -1作废,0新订单,1已审核,2已接收
			billSendDoc.setCredate(sdf.format(date));
			suBillSendService.insert(billSendDoc);
			
			/*******************************
			 * 处理dtl
			 *******************************/
			for (Object obj : jsonDtl) {
				SuBillSendDtl suBillSendDtl=new SuBillSendDtl();
				suBillSendDtl=(SuBillSendDtl) JSONObject.toBean(JSONObject.fromObject(obj), suBillSendDtl.getClass());
				suBillSendDtl.setDtlid(suBillOrderService.newDtlId().toString());
				suBillSendDtl.setDocid(newdocid); 
				//默认收货数量=送货数量
				suBillSendDtl.setRecieveqty(JSONObject.fromObject(obj).getString("sendqty"));
				suBillSendDtl.setDtlstatus("0");//明细状态:-1退回,0初始,1全部接收,2部分接收,3拒绝接收.
				//对应的平台货品id,没有对照则为空(此处原则上一定有对照,为对照不能上传)
				SuGoodsSupply suGoodsSupply=suGoodsSupplyService
						.getBySupplyidOrgidSugoodsid(supplyid, orgid, suBillSendDtl.getSugoodsid());
				suBillSendDtl.setGoodsid(suGoodsSupply==null?"":suGoodsSupply.getGoodsid());
				suBillSendService.insert(suBillSendDtl);
			}
			//提交
			DBManager.commitAll();
			result.setContent("上传送货单成功!");
			result.setCode("1");
			result.setMsg("上传送货单成功!");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(!result.getContent().equals("")?result.getContent():e.getMessage());
			result.setCode(!result.getCode().equals("")?result.getCode():"-1");
			result.setMsg(!result.getMsg().equals("")?result.getMsg():"系统异常");
			e.printStackTrace();
		}finally{
			System.out.println("uploadSendBillsSupply");
			return JSONObject.fromObject(result);
		}
	}

	/**
	 * 上传供应商端的货品字典数据
	 * post参数结构:
	 * 参数名称:order
	 * 参数值(结构):
	  	{
	  	"logname":"HBWS",
		"password":"123456",
		"data":
			[
				{"sugoodsid":"7001", "sugoodsname":"产品1", "spell":"CP1", "spec":"100件", "unit":"件", "classcode":"111", "classname":"低值耗材", "factory":"", "prodarea":""},
				{"sugoodsid":"7002", "sugoodsname":"产品2", "spell":"CP2", "spec":"/", "unit":"件", "classcode":"111", "classname":"低值耗材", "factory":"", "prodarea":""},
				{"sugoodsid":"7003", "sugoodsname":"产品3", "spell":"CP3", "spec":"100张", "unit":"张", "classcode":"111", "classname":"低值耗材", "factory":"", "prodarea":""},
				{"sugoodsid":"7004", "sugoodsname":"产品4", "spell":"CP4", "spec":"50本", "unit":"本", "classcode":"111", "classname":"低值耗材", "factory":"", "prodarea":""}
			]
		}
	 * @return MsgModel
	 */
	@ResponseBody
	@RequestMapping(value="uploadGoodsDataSupply",method = RequestMethod.POST)
	public JSONObject uploadGoodsDataSupply() {
		MsgModel result=new MsgModel("","","");
		String json=param("jsondata","");
		try {
			/*******************************
			 * 验证
			 *******************************/
			String logname=JSONObject.fromObject(json).getString("logname");
			String password=JSONObject.fromObject(json).getString("password");
			if(suSupplyOrgService.getByLogname(logname.toUpperCase())==null){
				result.setCode("1001");
				result.setMsg("用户不存在");
				throw new Exception("用户不存在");
			}
			SuSupplyOrg suSupplyOrg=suSupplyOrgService.getByLognamePassword(logname.toUpperCase(), password);
			if(suSupplyOrg==null){
				result.setCode("1002");
				result.setMsg("密码错误");
				throw new Exception("密码错误");
			}
			String orgid=suSupplyOrg.getOrgid();
			String supplyid=suSupplyOrg.getSupplyid();
			/*******************************
			 * 解析参数
			 *******************************/
			JSONArray jsonGoods=JSONObject.fromObject(json).getJSONArray("data");
			/*******************************
			 * 处理SuGoodsOrg
			 *******************************/
			Integer i=0;
			Integer u=0;
			boolean ub=false;
			for (Object obj : jsonGoods) {
                String sugoodsid=JSONObject.fromObject(obj).get("sugoodsid").toString();
                String sugoodsname=JSONObject.fromObject(obj).get("sugoodsname").toString();
                String spell=JSONObject.fromObject(obj).get("spell").toString();
                String spec=JSONObject.fromObject(obj).get("spec").toString();
                String unit=JSONObject.fromObject(obj).get("unit").toString();
                String classcode=JSONObject.fromObject(obj).get("classcode").toString();
                String classname=JSONObject.fromObject(obj).get("classname").toString();
                String factory=JSONObject.fromObject(obj).get("factory").toString();
                String prodarea=JSONObject.fromObject(obj).get("prodarea").toString();
				System.out.println("正在处理:["+sugoodsname+"]");
				SuGoodsSupply suGoodsSupply=suGoodsSupplyService.getBySupplyidOrgidSugoodsid(supplyid, orgid, sugoodsid);
				ub=false;
				if(suGoodsSupply != null){
					if(!Objects.equals(suGoodsSupply.getSugoodsname(),sugoodsname)){
						suGoodsSupply.setSugoodsname(sugoodsname);ub=true;}
					if(!Objects.equals(suGoodsSupply.getSpell()==null?"":suGoodsSupply.getSpell(),spell)){
						suGoodsSupply.setSpell(spell);ub=true;}
					if(!Objects.equals(suGoodsSupply.getSpec()==null?"":suGoodsSupply.getSpec(),spec)){
						suGoodsSupply.setSpec(spec);ub=true;}
					if(!Objects.equals(suGoodsSupply.getUnit()==null?"":suGoodsSupply.getUnit(),unit)){
						suGoodsSupply.setUnit(unit);ub=true;}
					if(!Objects.equals(suGoodsSupply.getClasscode()==null?"":suGoodsSupply.getClasscode(),classcode)){
						suGoodsSupply.setClasscode(classcode);ub=true;}
					if(!Objects.equals(suGoodsSupply.getClassname()==null?"":suGoodsSupply.getClassname(),classname)){
						suGoodsSupply.setClassname(classname);ub=true;}
					if(!Objects.equals(suGoodsSupply.getFactory()==null?"":suGoodsSupply.getFactory(),factory)){
						suGoodsSupply.setFactory(factory);ub=true;}
					if(!Objects.equals(suGoodsSupply.getProdarea()==null?"":suGoodsSupply.getProdarea(),prodarea)){
						suGoodsSupply.setProdarea(prodarea);ub=true;}
					if(ub){suGoodsSupply.update();u++;}
				}else{
					suGoodsSupply=new SuGoodsSupply();
					suGoodsSupply=(SuGoodsSupply) JSONObject.toBean(JSONObject.fromObject(obj), suGoodsSupply.getClass());
					suGoodsSupply.setOrgid(orgid);
					suGoodsSupply.setSupplyid(supplyid);
					suGoodsSupply.insert();
					i++;
				}
			}
			//提交
			DBManager.commitAll();
			result.setContent("本次处理共"+jsonGoods.size()+"条,其中插入:"+i+"条,更新:"+u+"条");
			result.setCode("1");
			result.setMsg("同步供应商货品字典成功!");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.setContent(e.getMessage());
			result.setCode("-1");
			result.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			System.out.println("uploadGoodsDataSupply");
			return JSONObject.fromObject(result);
		}
		
	}
}
