package com.cms.pubsrv;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper {
	/**
	 * add by wlm 2019年12月17日 获取当前系统时间
	 * 
	 * @return
	 */
	public static String getSysdateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	
	public static String getSysdate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
}
