package com.rs.dao;

import java.util.List;

import com.rs.model.Interview;
import com.rs.model.Staff;

public interface IInterviewDAO {

	public void save(Interview transientInstance) ;

	public void delete(Interview persistentInstance) ;

	public Interview findById(java.lang.Integer id) ;

	public List findByExample(Interview instance);

	public List findByProperty(String propertyName, Object value) ;	

	public List findAll() ;

	public Interview merge(Interview detachedInstance) ;
	
	public void attachDirty(Interview instance);
	
}
