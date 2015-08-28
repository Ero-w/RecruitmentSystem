package com.rs.dao;

import java.util.List;

import com.rs.model.Apply;

public interface IApplyDAO {
	
	public void save(Apply transientInstance) ;

	public void delete(Apply persistentInstance) ;

	public Apply findById(java.lang.Integer id) ;

	public List findByExample(Apply instance) ;

	public List findByProperty(String propertyName, Object value) ;	

	public List findAll() ;

	public Apply merge(Apply detachedInstance) ;

}
