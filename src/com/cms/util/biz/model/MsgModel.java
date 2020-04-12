package com.cms.util.biz.model;

public class MsgModel {
	private String code;
	private String msg;
	private String content;
	
	
	
	public MsgModel(String code, String msg, String content) {
		super();
		this.code = code;
		this.msg = msg;
		this.content = content;
	}
	public MsgModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
