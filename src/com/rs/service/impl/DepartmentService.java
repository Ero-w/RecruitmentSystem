package com.rs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rs.dao.IDepartmentDAO;
import com.rs.model.Department;
import com.rs.service.IDepartmentService;

public class DepartmentService implements IDepartmentService{
	
	private IDepartmentDAO departmentDAO;
	
	
	public IDepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public List findAll() {
		//重新封装数据避免将不必要的数据传到前台
		List<Department> allDepartments = departmentDAO.findAll();
		Map<String,Object> depart;
		List Departments=new ArrayList();
		for(Department department:allDepartments){
			depart=new HashMap<String, Object>();
			depart.put("DId", department.getDid());
			depart.put("DName", department.getDname());
			depart.put("DTel", department.getTel());
			depart.put("DLocation", department.getLocation());
			Departments.add(depart);
		}
		return Departments;
	}

	public List<String> findAllName() {
		List<Department> allDepartments = departmentDAO.findAll();
		List<String> allDepartmentsName=new ArrayList<String>();
		for(Department department:allDepartments){
			allDepartmentsName.add(department.getDname());
		}
		return allDepartmentsName;
	}
	
	
	public boolean addDepartment(JSONObject jsonObject) {
		Department department=new Department();
		department.setDname(jsonObject.getString("DName"));
		department.setTel(jsonObject.getString("DTel"));
		department.setLocation(jsonObject.getString("DAddress"));
		boolean isAdd=departmentDAO.save(department);
		return isAdd;
	}
	
	public boolean deleteDepartment(JSONObject jsonObject){
		Integer integer=jsonObject.getInteger("DId");
		Department department=departmentDAO.findById(jsonObject.getInteger("DId"));
		boolean isDelete=departmentDAO.delete(department);
		return isDelete;
	}

	public Department findById(Integer id) {
		return departmentDAO.findById(id);
	}

	public Department findByDname(Object dname) {
		return (Department)(departmentDAO.findByDname(dname)).get(0);
	}
	

}
