package test.sqlExe;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

public class JDBCTools {
	/* 
	   * 执行sql查询语句 
	   */
	public static <T> Vector<T> executeQuery(Class<T> clazz, String sql, Object... args) {
	    Connection conn = null;
	    PreparedStatement preparedstatement = null;
	    ResultSet rs = null;
	    Vector<T> vecRs = new Vector<T>();
	    T obj = null;
	    try {
	        conn = JDBCTools.getConnection();
	        preparedstatement = conn.prepareStatement(sql);
	        // 通过sql语句来判断选择了那些列 
	        for (int i = 0; i < args.length; i++) {
	            preparedstatement.setObject(i + 1, args[i]);
	        }
	         /* 利用sql查询获取结果集 
                                          利用反射创建实体类的对象 
	                                获取结果街的别名Stud_id 获取JDBC的元数据 
	                                获取结果集每一列的值，结合上一步得到一个Map键值对 
	                                键：列的别名 值：列的值 
	                                在利用反射对实体类对象的属性赋值 
	                                属性为Map的键 值为Map的值 */
	        rs = preparedstatement.executeQuery();
	        // 获取元数据 
	        ResultSetMetaData rsmd = rs.getMetaData();
	        Map<String, Object> mapMetaData = new HashMap<String, Object>();
	        // 打印一列的列名 
	        while (rs.next()) {
	            //获取数据表中满足要求的一行数据，并放入Map中 
	            for (int i = 0; i < rsmd.getColumnCount(); i++) {
	                String columnLabel = rsmd.getColumnLabel(i + 1);
	                Object columnValue = rs.getObject(columnLabel);
	                // System.out.println(columnLabel); 
	                mapMetaData.put(columnLabel, columnValue);
	            }
	            //将Map中的数据通过反射初始化T类型对象 
	            if (mapMetaData.size() > 0) {
	                obj = clazz.newInstance();
	                for (Map.Entry<String, Object> entry : mapMetaData.entrySet()) {
	                    String fieldkey = entry.getKey();
	                    Object fieldvalue = entry.getValue();
	                    System.out.println(fieldkey + ":" + fieldvalue); 
	                    //ReflectionUtils.setFieldValue(obj, fieldkey, fieldvalue);
	                    //通过反射赋值
	                }
	            }
	            //将对象装入Vector容器 
	            vecRs.add(obj);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    return vecRs;
	}
	
	/* 
	   * 获取数据库的连接 
	   */
	public static Connection getConnection() throws Exception {
	    Connection conn = null;
	    String driver = null;
	    String jdbcUrl = null;
	    String username = null;
	    String password = null;
	    // 获取Properties对象 
	    Properties properties = new Properties();
	    InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
	    properties.load(in);
	    driver = properties.getProperty("driver");
	    jdbcUrl = properties.getProperty("jdbcUrl");
	    username = properties.getProperty("user");
	    password = properties.getProperty("password");
	    Class.forName(driver);
	    conn = DriverManager.getConnection(jdbcUrl, username, password);
	    return conn;
	}
	
	//设置对象的属性 
	public static void setFieldValue(Object obj,String fieldName,Object value){
	    Field field=getDeclaredField(obj, fieldName);
	    if(field==null){
	        throw new IllegalArgumentException("Could not find field["+fieldName+"] on target ["+obj+"]");
	    }
	    makeAccessiable(field);
	    try{
	        field.set(obj, value);
	    }
	    catch(IllegalAccessException e){
	        System.out.println("不可能抛出的异常");
	    }
	}
	//判断field的修饰符是否是public,并据此改变field的访问权限 
	public static void makeAccessiable(Field field){
	    if(!Modifier.isPublic(field.getModifiers())){
	        field.setAccessible(true);
	    }
	}
	//获取field属性，属性有可能在父类中继承 
	public static Field getDeclaredField(Object obj,String fieldName){
	    for (Class<?> clazz=obj.getClass(); clazz!=Object.class; clazz=clazz.getSuperclass()){
	        try{
	            return clazz.getDeclaredField(fieldName);
	        }
	        catch(Exception e){
	        }
	    }
	    return null;
	}
}