package com.rs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rs.dao.impl.StaffDAO;
import com.rs.model.Staff;
import com.rs.service.IAuthorityService;

public class AuthorityService implements IAuthorityService {
	private StaffDAO staffDAO;
	private Map<String, Object> map=new HashMap<String, Object>(); //////
	
	public StaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

	@Override
	public List findStaffBySname(Object Sname) {
		// TODO Auto-generated method stub
		List list=staffDAO.findBySname(Sname);
		return list;
	}

	@Override
	public void saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		staffDAO.attachDirty(staff);
	}
	
	//=======addByIven======
	
	public Map<String, Object> readAllStaff(){
		map.put("map", staffDAO.findAll());
		return map;
	}
	
	//=======addByIven======

}
