package com.rs.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.service.IDepartmentService;
import com.rs.service.IPositionService;

public class PositionAPI extends ActionSupport{
	
	private JSONObject jsonObject;
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	//在下拉框中需要获取所有的部门名称，故需要departmentService
	private IDepartmentService departmentService;
	
	private IPositionService positionService;
	
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
	
	public IPositionService getPositionService() {
		return positionService;
	}
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	//加载所有的部门名称，供填写信息时选择
	public String getDepartmentName(){
		List departments=departmentService.findAll();
		map.put("allDepartments", departments);
		return SUCCESS;
	}
	
	public String InsertPos(){//添加用0做标志,表示要插入数据
		boolean isAdd=positionService.save(jsonObject,0);
		if(isAdd){
			map.put("isAdd", 1);
		}
		else{
			map.put("isAdd", 0);
		}
		return SUCCESS;
	}
	
	public String getAllPos(){//获取所有的职位
		List allPos=positionService.getAllPos();
		map.put("allPos", allPos);
		return SUCCESS;
	}
	public String updatePos(){//更新用1做标志，表示要更新
		boolean isUpdate=positionService.save(jsonObject, 1);
		if(isUpdate){
			map.put("isUpdate", 1);
		}
		else{
			map.put("isUpdate", 0);
		}
		return SUCCESS;
	}
	public String delPos(){//删除指定职位
		boolean isDel=positionService.delete(jsonObject);
		if(isDel){
			map.put("isDel", 1);
		}
		else{
			map.put("isDel", 0);
		}
		return SUCCESS;
	}
	
	public String Search(){//根据关键字搜索
		List sear_pos=positionService.Search(jsonObject);//搜索关键字得到的结果
		map.put("sear_pos", sear_pos);
		return SUCCESS;
	}
}
