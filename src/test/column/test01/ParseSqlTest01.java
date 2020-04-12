package test.column.test01;

import java.util.HashMap;  
import java.util.IdentityHashMap;  
import java.util.Iterator;  
import java.util.Map;  
  
public class ParseSqlTest01 {  
  
      
//  解析用户发送sql语句，获得用户所查询的表。以及获得所需要的列  
    public void parserSQL(String sql){  
//      mapTable获得表名和别名的一个map  
        Map mapTable = new HashMap();  
        String tableName ;  
//      select p.name,o.officedesc from person as p,office as o where p.officeID=o.id  
//      select * from person  
        if(sql.contains("as")){  
//          System.out.println("1:"+sql.substring(sql.indexOf("from")+4,sql.indexOf("where")).trim());  
              
            String s = sql.substring(sql.indexOf("from")+4,sql.indexOf("where")).trim();  
            String str[] = s.split(",");  
            for(String st:str){  
//              string数组为获得表,map存表和简写对应关系  
                mapTable.put(st.substring(st.indexOf("as")+2, st.length()).trim(),st.substring(0, st.indexOf("as")-1));  
//              System.out.println("get the table: "+st.substring(0,st.indexOf("as")-1));  
            }  
        }else{  
//          System.out.println(sql.substring(sql.indexOf("from")+4,sql.length()).trim());  
            tableName = sql.substring(sql.indexOf("from")+4,sql.length()).trim();  
            System.out.println("表名："+tableName);  
        }  
          
//      System.out.println("table size is : "+mapTable.size());  
          
          
        Iterator it = mapTable.entrySet().iterator();   
            while(it.hasNext()){   
                Map.Entry m=(Map.Entry)it.next();   
                System.out.println(m.getKey()+"    "+m.getValue());  
            }   
          
//      解析sql语句中所含有的列名  
//      select p.*,o.officedesc from person as p,office as o where p.officeID=o.id  
//      if(sql.contains("*")){  
//          System.out.println(sql.substring(sql.indexOf("select")+6,sql.indexOf("from")).trim());  
            if(sql.contains(".")){  
                String tableMsg = sql.substring(sql.indexOf("select")+6,sql.indexOf("from")).trim();  
                String s [] = tableMsg.split(",");  
//              mapColumns获得表别名和所需要的列名,使用IdentityHashMap的方法，对于有重复的KEY进行处理。  
//              如果采用hashmap会对其进行覆盖。  
                Map mapColumns = new IdentityHashMap();  
                for(String s1:s){  
                    System.out.println("000000");  
                    mapColumns.put(s1.substring(0, s1.indexOf(".")), s1.substring(s1.indexOf(".")+1,s1.length()));  
                }  
                System.out.println(mapColumns);  
//              System.out.println("columns size : " + mapColumns.size());  
                /*for(Object obj : mapColumns.keySet()){ 
                      Object value = mapColumns.get(obj ); 
                      System.out.println("values:"+value); 
                }*/  
                  
                /*for(Map.Entry entry:mapColumns.entrySet()){ 
                    System.out.println(entry.getKey()+"="+entry.getValue()); 
                    }*/  
                  
                System.out.println(mapColumns.size());  
                Iterator iterator = mapColumns.entrySet().iterator();   
                    while(iterator.hasNext()){   
                    Map.Entry m=(Map.Entry)iterator.next();   
                    System.out.println("table :"+mapTable.get(m.getKey()));  
//                  System.out.println(m.getKey()+"    "+m.getValue());  
                    System.out.println("columns:"+m.getValue());  
                   }   
            }  
//      }else{  
//          System.out.println(sql.substring(sql.indexOf("select")+6,sql.indexOf("from")).trim());  
//            
//      }  
          
          
    }  
    public static void main(String[] args) {  
//      new ParseSql().parserSQL("select p.name,o.officedesc from person as p,office as o where p.officeID=o.id");  
//      new ParseSql().parserSQL("select * from person");  
//      System.out.println("---------");  
        new ParseSqlTest01().parserSQL(""
        		+ "select "
        		+ "    p.id,"
        		+ "    p.file,"
        		+ "    p.name,"
        		+ "    o.officedesc "
        		+ "from "
        		+ "    person as p,"
        		+ "    office as o "
        		+ "where "
        		+ "    p.officeID=o.id and p,id=?");  
          
    }  
}  
