package com.rs.action.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.service.impl.ApplyService;

public class ApplyAPI extends ActionSupport{
	
	private ApplyService applyService;
	
	public ApplyService getApplyService() {
		return applyService;
	}
	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}
	private JSONObject jsonObject;
	
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
		try {//转换json对象
			jsonObject=JSONObject.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=null;
		}
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
