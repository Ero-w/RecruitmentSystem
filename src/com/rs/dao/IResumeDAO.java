package com.rs.dao;

import java.util.List;
import com.rs.model.Resume;

public interface IResumeDAO {
	
	public void save(Resume transientInstance) ;

	public void delete(Resume persistentInstance) ;

	public Resume findById(java.lang.Integer id) ;

	public List findByExample(Resume instance) ;

	public List findByProperty(String propertyName, Object value) ;

	public List findByEducation(Object education) ;

	public List findByExperience(Object experience) ;
	
	public List findBySkill(Object skill) ;

	public List findAll() ;

	public Resume merge(Resume detachedInstance) ;

	public void attachDirty(Resume instance);

	public void attachClean(Resume instance);
}
