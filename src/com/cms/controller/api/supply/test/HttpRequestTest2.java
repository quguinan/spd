package com.cms.controller.api.supply.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
public class HttpRequestTest2 {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
 
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果："+result);
        return result;
    }
    
    public static void main(String[] args) {
        //发送 GET 请求
//        String s=HttpRequestTest.sendGet("http://localhost:8080/spd/api/org/uploadGoodsDataOrgTest", "key=123&v=456");
//        System.out.println(s);
    	//发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
        
        //发送 POST 请求
    	//String param="{'logname':'ZMJT','password':'123456','data':[{'goodsid':'10536','goodsname':'全效多酶清洗液AQ-504L','spell':'QXDMQXYAQ-504L','goodstype':'','classcode':'717','classname':'消毒材料','spec':'5L/瓶','unit':'瓶','factory':'广东中山','prodarea':''},{'goodsid':'10539','goodsname':'D-二聚体排除实验试剂盒II','spell':'D-EJTPCSYSJHII','goodstype':'','classcode':'713','classname':'化验材料','spec':'60T/盒','unit':'盒','factory':'法国梅里埃','prodarea':''},{'goodsid':'10541','goodsname':'穿刺活检针（单钩术前定位针）','spell':'CCHJZ（DGSQDWZ）','goodstype':'','classcode':'718','classname':'介入材料','spec':'BL18/10','unit':'支','factory':'北京德迈科技发展有限公司','prodarea':''},{'goodsid':'10542','goodsname':'活检针（多用途千叶针）','spell':'HJZ（DYTQYZ）','goodstype':'','classcode':'718','classname':'介入材料','spec':'PA23/08','unit':'支','factory':'北京德迈科技发展有限公司','prodarea':''},{'goodsid':'10543','goodsname':'一次性活检针','spell':'YCXHJZ','goodstype':'','classcode':'718','classname':'介入材料','spec':'SC16/16','unit':'支','factory':'北京德迈科技发展有限公司','prodarea':''}]}";
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

		doc.put("dtl", jsonarray);
		returnJson.put("doc", doc);
		
//    	System.out.println(json.getJSONArray("data").size());
//    	System.out.println(json.toString());
    	String sr=HttpRequestTest2.sendPost("http://10.0.42.238/spd/api/org/uploadOrderBillOrg", "jsondata="+returnJson);
        System.out.println(sr);
    }
}
