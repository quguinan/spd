package com.cms.util;

import java.io.File;

public class DeleteFolderFileUtil {
	//删除文件夹  
	public static boolean delFolder(String folderPath) {  
	     try {  
	        delAllFile(folderPath); //删除完里面所有内容  
	        String filePath = folderPath;  
	        filePath = filePath.toString();  
	        java.io.File myFilePath = new java.io.File(filePath);  
	        myFilePath.delete(); //删除空文件夹  
	     } catch (Exception e) {  
	       return false;  
	     }  
	     return true;  
	}  
	
	public static boolean delAllFile(String path) {  
       boolean flag = false;  
       File file = new File(path);  
       if (!file.exists()) {  
         return flag;  
       }  
       if (!file.isDirectory()) {  
         return flag;  
       }  
       String[] tempList = file.list();  
       File temp = null;  
       for (int i = 0; i < tempList.length; i++) {  
          if (path.endsWith(File.separator)) {  
             temp = new File(path + tempList[i]);  
          } else {  
              temp = new File(path + File.separator + tempList[i]);  
          }  
          if (temp.isFile()) {  
             temp.delete();  
          }  
          if (temp.isDirectory()) {  
             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
             delFolder(path + "/" + tempList[i]);//再删除空文件夹  
             flag = true;  
          }  
       }  
       return flag;  
     }  
	public static boolean delFile(String fullFileName) {  
	   boolean flag = false;  
       File file = new File(fullFileName);  
       if (!file.exists()) {  
         return flag;  
       }  
       File temp = new File(fullFileName);
       if (temp.isFile()) {  
           temp.delete();  
           flag = true;  
        } 
	   return flag;
	}
}  
