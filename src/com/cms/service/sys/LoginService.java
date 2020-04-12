package com.cms.service.sys;

import org.springframework.stereotype.Service;

import com.cms.model.biz.supply.SuUser;
import com.cms.model.sys.SysUser;

@Service
public class LoginService {
	
	public SysUser getUserByName(String username){
		return SysUser.INSTANCE.queryOne("username=?", username);
	}
	
	public SuUser getSuUserByName(String username){
		SysUser user=getUserByName(username);
		if(user==null){
			return null;
		}else{
			return SuUser.INSTANCE.queryOne("userid=?", user.getUserid());
		}
	}
}
