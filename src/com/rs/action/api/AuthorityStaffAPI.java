package com.rs.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.model.Department;
import com.rs.model.Staff;
import com.rs.service.impl.AuthorityService;

public class AuthorityStaffAPI extends ActionSupport {
	private JSONObject jsonObject;
	
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	private AuthorityService authorityService;
	
	public String search(){
		if(jsonObject==null){//传入参数为空
			map.put("size", 0);
		}else{
			List<Staff> staff=authorityService.findStaffBySname(jsonObject.getString("sname"));
			if(staff.size()==0){
				map.put("size", 0);
			}else{
				map.put("size", staff.size());
				int authority=staff.get(0).getPriority();
				map.put("sid",staff.get(0).getSid());
				map.put("sname",staff.get(0).getSname());
				map.put("dname",staff.get(0).getDepartment().getDname());
				map.put("authority",staff.get(0).getPriority());
			}
		}
		return SUCCESS;
	}

	//=======addByIven======
	
	public String readAllStaff(){
		map = authorityService.readAllStaff();
		return SUCCESS;
	}
	
	//=======addByIven======
	
	public String save(){
		//int sid=Integer.parseInt(jsonObject.getString("sid"));
		String sname=jsonObject.getString("sname");
		int authority=Integer.parseInt(jsonObject.getString("authority"));
		List<Staff> staff=authorityService.findStaffBySname(sname);
		//Department dp=staff.get(0).getDepartment();
		if(staff.get(0).getPriority()==authority){
			map.put("state", 0);
			return SUCCESS;
		}
		staff.get(0).setPriority(authority);
		//System.out.println(dp);
		try{
			authorityService.saveStaff(staff.get(0));
			map.put("state",1);
		}catch(Exception e){
			map.put("state",-1);
		}
		return SUCCESS;
	}
	
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

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	

}
