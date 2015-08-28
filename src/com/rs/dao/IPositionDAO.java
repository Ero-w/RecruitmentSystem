package com.rs.dao;

import java.util.Date;
import java.util.List;

import com.rs.model.Position;

public interface IPositionDAO {
	
	public boolean save(Position transientInstance);
	
	public boolean attachDirty(Position instance);
	
	public List findAll();
	
	public Position findById(java.lang.Integer id);
	
	public void delete(Position persistentInstance);
	
	public List findByCount(Object count);
	
	public List findByPname(Object pname);
	
	public List findByDateline(Date dateline);
	
	public List findByDId(Integer DId);
}
