package com.rs.dao;

import java.util.List;

import com.rs.model.Account;

public interface IAccountDAO {
	
	public void save(Account transientInstance) ;

	public void delete(Account persistentInstance) ;

	public Account findById(java.lang.String id) ;

	public List findByExample(Account instance) ;

	public List findByProperty(String propertyName, Object value) ;

	public List findByPassowrd(Object passowrd) ;

	public List findAll() ;

	public Account merge(Account detachedInstance) ;

}
