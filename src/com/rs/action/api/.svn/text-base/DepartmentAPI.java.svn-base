package com.rs.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.model.Department;
import com.rs.service.IDepartmentService;

public class DepartmentAPI extends ActionSupport{
	
	private JSONObject jsonObject;
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	//在下拉框中需要获取所有的部门名称，故需要departmentService
	private IDepartmentService departmentService;
	
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
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String getDepartments(){
		List<Department> departments=departmentService.findAll();
		map.put("allDepartmes", departments);
		return SUCCESS;
	}
	
	public String addDepartment(){
		boolean isAdd = departmentService.addDepartment(jsonObject);
		if(isAdd){
			map.put("isAdd", 1);
		}
		else{
			map.put("isAdd", 0);
		}
		return SUCCESS;
	}
	
	public String deleteDepartment(){
		boolean isDelete = departmentService.deleteDepartment(jsonObject);
		if(isDelete){
			map.put("isDelete", 1);
		}
		else{
			map.put("isDelete", 0);
		}
		return SUCCESS;
	}
}
