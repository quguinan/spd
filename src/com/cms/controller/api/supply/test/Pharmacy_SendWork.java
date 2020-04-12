package com.cms.controller.api.supply.test;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Category;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Pharmacy_SendWork {
	Category logger = Category.getInstance(Pharmacy_SendWork.class);
	String url;
	String jsonpojo;
	int connect_timeout = 6000;
	int read_timeout = 6000;
	Map<String, String> params;

	public Pharmacy_SendWork(String url) {
		this.url = url;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getServerResp() {
		return this.jsonpojo;
	}

	public void sendReq() throws Exception {
		URL urlobj = new URL(this.url);
		HttpURLConnection httpUrlCon = null;
		InputStream in = null;
		try {
			httpUrlCon = (HttpURLConnection) urlobj.openConnection();
			httpUrlCon.setConnectTimeout(this.connect_timeout);
			httpUrlCon.setReadTimeout(this.read_timeout);
			httpUrlCon.setDoInput(true);
			httpUrlCon.setDoOutput(true);
			httpUrlCon.setUseCaches(false);
			httpUrlCon.setRequestMethod("POST");
			httpUrlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			httpUrlCon.addRequestProperty("Connection", "close");
			httpUrlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT ");

			PrintWriter printWriter = new PrintWriter(httpUrlCon.getOutputStream());
			String param = "";
			for (String key : this.params.keySet()) {
				if (param==null)
					param = key + "=" + URLEncoder.encode((String) this.params.get(key), "UTF-8");
				else {
					param = param + "&" + key + "=" + URLEncoder.encode((String) this.params.get(key), "UTF-8");
				}
			}
			this.logger.info("请求参数:" + param);
			System.err.println(param);
			printWriter.write(param);
			printWriter.flush();
			printWriter.close();
			if (httpUrlCon.getResponseCode() == 200) {
				in = httpUrlCon.getInputStream();
				PharmacyResponse resp = new PharmacyResponse();
				resp.ReadData(in);
				System.out.println(resp.getResult());
				this.logger.info("请求服务返回的json数据:" + resp.getResult());
				this.jsonpojo = resp.getResult();
				in.close();
				in = null;
			} else {
				throw new Exception(
						"服务器端响应错误码:" + httpUrlCon.getResponseCode() + ",响应错误信息:" + httpUrlCon.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("错误信息:" + e.getMessage());
		} catch (Error e) {
			e.printStackTrace();
			this.logger.error("错误信息:" + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/spd/api/org/uploadOrderBillOrg";

		Pharmacy_SendWork sendwork = new Pharmacy_SendWork(url);
		JSONObject returnJson = new JSONObject();
		JSONObject doc = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		returnJson.put("loginname", "ZMJT");
		returnJson.put("password", "123456");

		JSONObject dtl1 = new JSONObject();

		dtl1.put("sortno", "1");
		dtl1.put("goodsid", "10003");
		dtl1.put("orderqty", "30");
		dtl1.put("unit", "盒");
		dtl1.put("unitprice", "45");
		dtl1.put("total", "3000");
		dtl1.put("memo", "3000");
		jsonarray.add(dtl1);

		doc.put("supplyid", "1050");
		doc.put("ordtype", "2");
		doc.put("signaddress", "3");
		doc.put("signman", "3");
		doc.put("validbegdate", "1");
		doc.put("validenddate", "1");
		doc.put("settletype", "5");
		doc.put("prepay", "250");
		doc.put("total", "232232");
		doc.put("memo", "222");
		doc.put("storerid", "1");
		doc.put("sourceid", "2000");

		doc.put("dtl", dtl1);
		returnJson.put("doc", doc);

		// JSONObject jsonobject = JSONObject.fromObject(map);
		// System.out.println(returnJson);
		Map<String, String> map = new HashMap<String, String>();
		map.put("jsondate", returnJson.toString());
		sendwork.setParams(map);
		sendwork.sendReq();
//
		String responsejson = sendwork.getServerResp();
		System.out.println(responsejson);
//		    JSONObject json = JSON.parseObject(responsejson);
//		    JSONArray resultinfo = json.getJSONArray("resultInfo");
//		    for (int i = 0; i < resultinfo.size(); i++) {
//		      JSONObject element = resultinfo.getJSONObject(i);
//		      String id = element.getString("id");
//		      String types = element.getString("types");
//		      String updatedate = element.getString("updatedate");
//		      String version = element.getString("version");
//		      String catalog = element.getString("catalog");
//		      String interfacetypeid = element.getString("interfacetypeid");
//		      String interfacetypename = element.getString("interfacetypename");
//		      System.out.println(id + "," + types + "," + updatedate + "," + version + "," + catalog + "," + interfacetypeid + "," + interfacetypename);
//		    }
		// }
	}
	
	
}
