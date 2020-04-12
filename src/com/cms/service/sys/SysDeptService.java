package com.cms.service.sys;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cms.model.sys.SysDept;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SysDeptService {
	
	/**
	 * 返回权限列表(全部) 树形json
	 * @return
	 */
	public JSONArray findAllJSONArray(){
		JSONArray ja1=new JSONArray();//一级菜单JSONArray
		List<SysDept> list1=SysDept.INSTANCE.query("( deptpid = '' or deptpid is null ) ");//一级菜单
		for (SysDept dept1 : list1) {
			String pid=dept1.getDeptid();
			JSONObject jo1=JSONObject.fromObject(dept1);
			List<SysDept> list2=SysDept.INSTANCE.query(" deptpid = ? ",pid);//二级菜单
			if(list2.size()>0){
				jo1.put("children", JSONArray.fromObject(list2));
			};
			ja1.add(jo1);
		}
		return ja1;
	}
	
	
}
