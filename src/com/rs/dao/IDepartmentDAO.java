package com.rs.dao;

import java.util.List;

import com.rs.model.Department;

public interface IDepartmentDAO {
	
	public boolean save(Department transientInstance);
	
	public boolean delete(Department persistentInstance);
	
	public Department findById(java.lang.Integer id);
	
	public List findByDname(Object dname);
	
	public List findAll();
	
	public void attachDirty(Department instance);
	
	public void attachClean(Department instance);
}
