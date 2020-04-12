package com.cms.controller.api.supply.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
 
public class HttpRequestTest {
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
    	String param="{                                                                                                                                                                                                                                                                                                          "+
    			"'logname':'ZMJT',                                                                                                                                                                                                                                                                                          "+
    			"'password':'123456',                                                                                                                                                                                                                                                                                       "+
    			"'data':                                                                                                                                                                                                                                                                                                    "+
    			"[                                                                                                                                                                                                                                                                                                          "+
    			
//				"{'goodsid':'7017','goodsname':'2%葡萄糖氯已定皮肤消毒液','spell':'2%PTTLYDPFXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'60ml','unit':'瓶','factory':'上海利康消毒高科技有限公司','prodarea':''},                                                                               "+
				//"{'goodsid':'7030','goodsname':'5%水合氯醛溶液','spell':'5%SHLQRY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'20ml','unit':'瓶','factory':'国家化学试剂质检中心','prodarea':''},                                                                                                    "+
				//"{'goodsid':'7033','goodsname':'75%酒精消毒液','spell':'75%JJXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'60ml外翻盖','unit':'瓶','factory':'山东利尔康医疗科技股份有限公司','prodarea':''},                                                                                     "+
				//"{'goodsid':'6152','goodsname':'安捷2%戊二醛消毒液','spell':'AJ2%WEQXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'2500ml','unit':'桶','factory':'德州安捷高科消毒制品有限公司','prodarea':''},                                                                                     "+
				//"{'goodsid':'6154','goodsname':'75%乙醇消毒液','spell':'75%YCXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'100ml','unit':'瓶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                             "+
				//"{'goodsid':'6155','goodsname':'75%乙醇消毒液','spell':'75%YCXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'2500ml','unit':'桶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                            "+
				//"{'goodsid':'6156','goodsname':'75%乙醇消毒液','spell':'75%YCXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'500ml','unit':'瓶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                             "+
				//"{'goodsid':'6157','goodsname':'安捷75%乙醇消毒液','spell':'AJ75%YCXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'60ml','unit':'瓶','factory':'德州安捷高科消毒制品有限公司','prodarea':''},                                                                                        "+
				//"{'goodsid':'6158','goodsname':'安捷95%复方酒精消毒液','spell':'AJ95%FFJJXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'2.5L/桶','unit':'桶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                               "+
				//"{'goodsid':'6159','goodsname':'95%复方酒精消毒液','spell':'95%FFJJXDY','goodstype':'','classcode':'61','classname':'医疗器械','spec':'500ml','unit':'瓶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                       "+
				//"{'goodsid':'6218','goodsname':'3%过氧化氢抗菌洗剂','spell':'3%GYHQKJXJ','goodstype':'','classcode':'61','classname':'医疗器械','spec':'500ml','unit':'瓶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                      "+
				//"{'goodsid':'6444','goodsname':'柠檬酸消毒液','spell':'NMSXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'5L；20%','unit':'瓶','factory':'德国费森尤斯集团','prodarea':''},                                                                                                         "+
				//"{'goodsid':'6466','goodsname':'2%强化戊二醛消毒液','spell':'2%QHWEQXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'2500ml','unit':'瓶','factory':'山东利尔康医疗科技股份有限公司','prodarea':''},                                                                                  "+
				//"{'goodsid':'6467','goodsname':'医用酒精','spell':'YYJJ','goodstype':'','classcode':'717','classname':'消毒材料','spec':'75%；100ml','unit':'瓶','factory':'山东利尔康医疗科技股份有限公司','prodarea':''},                                                                                              "+
				//"{'goodsid':'6468','goodsname':'医用酒精','spell':'YYJJ','goodstype':'','classcode':'717','classname':'消毒材料','spec':'75%；60ml','unit':'瓶','factory':'山东安捷高科消毒科技有限公司','prodarea':''},                                                                                                 "+
				//"{'goodsid':'6469','goodsname':'医用酒精','spell':'YYJJ','goodstype':'','classcode':'717','classname':'消毒材料','spec':'95%:500ml','unit':'瓶','factory':'山东利尔康医疗科技股份有限公司','prodarea':''},                                                                                               "+
				//"{'goodsid':'9290','goodsname':'3%次氯酸钠','spell':'3%CLSN','goodstype':'','classcode':'1095','classname':'口腔器械','spec':'贝康/200ml/瓶','unit':'瓶','factory':'汕头市贝康生物科技有限公司','prodarea':''},                                                                                          "+
				//"{'goodsid':'9299','goodsname':'氟化泡沫','spell':'FHPM','goodstype':'','classcode':'1095','classname':'口腔器械','spec':'38g-1.23%A型','unit':'瓶','factory':'北京鑫源恒信医疗器械有限公司','prodarea':''},                                                                                             "+
				//"{'goodsid':'9895','goodsname':'75%酒精消毒液','spell':'75%JJXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'50ml','unit':'瓶','factory':'山东利尔康医疗科技股份有限公司','prodarea':''},                                                                                           "+
				//"{'goodsid':'10101','goodsname':'聚维酮碘消毒液','spell':'JWTDXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'10ml 5%','unit':'瓶','factory':'山西诺成制药有限公司','prodarea':''},                                                                                                 "+
				//"{'goodsid':'9175','goodsname':'ABO血型反定型试剂盒','spell':'ABOXXFDXSJH','goodstype':'','classcode':'691','classname':'检验试剂','spec':'浓度0.8%（货号：BX2001-5-3)10mL','unit':'盒','factory':'长春博讯生物技术有限责任公司','prodarea':''},                                                         "+
				//"{'goodsid':'8284','goodsname':'75%乙醇消毒液','spell':'75%YCXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'500ml','unit':'瓶','factory':'衡阳原野实业有限公司消毒洗涤剂厂分装','prodarea':''},                                                                                    "+
				//"{'goodsid':'8286','goodsname':'95%乙醇消毒液','spell':'95%YCXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'500ml','unit':'瓶','factory':'衡阳原野实业有限公司消毒洗涤剂厂分装','prodarea':''},                                                                                    "+
				//"{'goodsid':'8286','goodsname':'95%乙醇消毒液','spell':'95%YCXDY','goodstype':'','classcode':'717','classname':'消毒材料','spec':'500ml','unit':'瓶','factory':'衡阳原野实业有限公司消毒洗涤剂厂分装','prodarea':''},                                                                                    "+
//				"{'goodsid':'7643','goodsname':'一次性使用静脉血样采集容器（非无菌）','spell':'YCXSYJMXYCJRQ（FWJ）','goodstype':'','classcode':'716','classname':'一次性卫生材料','spec':'玻璃酸钠4NC(3.8%) 1.6ml','unit':'支','factory':'河北鑫乐医疗器械科技股份有限公司 ','prodarea':''},                            "+
//				"{'goodsid':'7647','goodsname':'一次性使用静脉血样采集容器（非无菌）','spell':'YCXSYJMXYCJRQ（FWJ）','goodstype':'','classcode':'716','classname':'一次性卫生材料','spec':'柠檬酸钠4NC（3.8%）1.6ml','unit':'支','factory':'河北鑫乐医疗器械科技股份有限公司 ','prodarea':''},                           "+
//				"{'goodsid':'7648','goodsname':'一次性使用静脉血样采集容器（非无菌）','spell':'YCXSYJMXYCJRQ（FWJ）','goodstype':'','classcode':'716','classname':'一次性卫生材料','spec':'柠檬酸钠9NC（3.2%）3ml','unit':'支','factory':'河北鑫乐医疗器械科技股份有限公司 ','prodarea':''},                             "+
    			    			
    			
    			
    			"{'goodsid':'10944','goodsname':'一次性使用垫单','spell':'YCXSYDD','goodstype':'','classcode':'716','classname':'一次性卫生材料','spec':'50×60','unit':'个','factory':'北京中北博健科贸有限公司','prodarea':''}                                                                                         "+                                                                                                                                                                                 


    			"]                                                                                                                                                                                                                                                                                                          "+
    			"}                                                                                                                                                                                                                                                                                                          ";                                                                                                                                                                                                                                                                                  
    	JSONObject json=JSONObject.fromObject(param);
    	System.out.println(json.getJSONArray("data").size());
//    	System.out.println(json.toString());
    	String sr=HttpRequestTest.sendPost("http://zmsj.hnymyy.com.cn:8888/spd/api/org/uploadGoodsDataOrg", "jsondata="+json);
        System.out.println(sr);
    }
}
