package com.rs.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.DetailEntry;

import org.hibernate.mapping.Array;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rs.dao.IPositionDAO;
import com.rs.dao.impl.DepartmentDAO;
import com.rs.model.Department;
import com.rs.model.Position;
import com.rs.service.IDepartmentService;
import com.rs.service.IPositionService;

public class PositionService implements IPositionService{
	
	IPositionDAO positionDAO;
	IDepartmentService departmentService;
	
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IPositionDAO getPositionDAO() {
		return positionDAO;
	}

	public void setPositionDAO(IPositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}

	public boolean save(JSONObject jsonObject,int flag) {//flag用来标志添加还是更新
		Position position;
		//获取数据
		if(flag==1){
			Integer PId=jsonObject.getInteger("PId");
			position=positionDAO.findById(PId);
		}
		else{
			position=new Position();
		}
		Integer DId=jsonObject.getInteger("DId");
		String pos_Name=jsonObject.getString("position");
		Integer number=jsonObject.getInteger("number");
		Date deadline=jsonObject.getDate("date");
		String requirement=jsonObject.getString("departmentRequire");
		
		//获取部门
		Department department=departmentService.findById(DId);
		
		//插入数据
		position.setDepartment(department);
		position.setDeadline(deadline);
		position.setCount(number);
		position.setPname(pos_Name);
		position.setRequirement(requirement);
		
		boolean isAdd=positionDAO.attachDirty(position);
		
		return isAdd;
	}

	public List<Map<String,Object>> getAllPos() {
		List<Map<String,Object>> all=positionDAO.findAll();
		List<Map<String,Object>> allPos=new ArrayList<Map<String,Object>>();
		Map<String,Object> map;
		for(int i=0;i<all.size();i++){
			map=new HashMap<String,Object>();
			Position position=(Position)all.get(i);
			map.put("pId",position.getPid());
			map.put("DName", position.getDepartment().getDname());
			map.put("DId", position.getDepartment().getDid());
			map.put("Pname", position.getPname());
			map.put("Count", position.getCount());
			map.put("DeadLine", position.getDeadline());
			map.put("requirement",position.getRequirement());
			allPos.add(map);
		}
		return allPos;
	}

	//删除数据
	public boolean delete(JSONObject jsonObject) {
		int i;
		boolean flag=false;
		//获取所有要删除的id
		JSONArray delPIds=jsonObject.getJSONArray("delPos");
		for(i=0;i<delPIds.size();i++){
			int PId=delPIds.getInteger(i);
			Position position=positionDAO.findById(PId);
			positionDAO.delete(position);
		}
		if(i==delPIds.size()){
			flag=true;
		}
		return flag;
	}
	

	public List Search(JSONObject jsonObject) {
		List<Map<String,Object>> searPos;//从数据库中取出来的数据
		List<Map<String,Object>> sear_pos=new ArrayList<Map<String,Object>>();//封装好的数据
		Map<String,Object> map;
		int search_way=jsonObject.getIntValue("search_way");
		if(search_way==1){//按部门查找
			Integer DId=jsonObject.getInteger("key");
			searPos=positionDAO.findByDId(DId);
		}
		else if(search_way==2){//按人数查找
			Integer count=jsonObject.getInteger("key");
			searPos=positionDAO.findByCount(count);
		}
		else if(search_way==3){//按截止日期查找
			Date deadline=jsonObject.getDate("key");//数据库的字符串格式
			searPos=positionDAO.findByDateline(deadline);
		}
		else{//4:按照职位名称查找
			String pname=jsonObject.getString("key");
			searPos=positionDAO.findByPname(pname);
		}		
		for(int i=0;i<searPos.size();i++){
			map=new HashMap<String,Object>();
			Position position=(Position)searPos.get(i);
			map.put("pId",position.getPid());
			map.put("DName", position.getDepartment().getDname());
			map.put("DId", position.getDepartment().getDid());
			map.put("Pname", position.getPname());
			map.put("Count", position.getCount());
			map.put("DeadLine", position.getDeadline());
			map.put("requirement",position.getRequirement());
			sear_pos.add(map);
		}
		return sear_pos;
	}
}

