package com.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.model.Testaaa;

import my.web.BaseController;
@Controller
@RequestMapping("/cms")
public class TestController extends BaseController{
	@RequestMapping("test")
	public String goodsSelect(Model m) {
		
		List<Testaaa> rslt=new ArrayList<Testaaa>();
		
		List<Testaaa> list=Testaaa.INSTANCE.query(" order by a,c");
		
		int i=0;
		while(i<list.size()){
			Testaaa test=new Testaaa();
			
			if(		list.get(i).getH().equals(list.get(i+1).getH())&&
					list.get(i).getI().equals(list.get(i+1).getI())&&
					list.get(i).getJ().equals(list.get(i+1).getJ())&&
					list.get(i).getK().equals(list.get(i+1).getK())&&
					list.get(i).getL().equals(list.get(i+1).getL())){
				
			}else{
				
			}
			
			rslt.add(test);
			i++;
		}
		
		
		
		return "test";
	}
	
	public Testaaa test(List<Testaaa> list){
		Testaaa test=new Testaaa();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getH().equals(list.get(i+1).getH())&&
					list.get(i).getI().equals(list.get(i+1).getI())&&
					list.get(i).getJ().equals(list.get(i+1).getJ())&&
					list.get(i).getK().equals(list.get(i+1).getK())&&
					list.get(i).getL().equals(list.get(i+1).getL()))
			{
			}else{
				
			}
		}
		
		return test;
	}
}
