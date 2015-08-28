package com.rs.service.impl;

import com.rs.dao.IStaffDAO;
import com.rs.model.Staff;
import com.rs.service.IStaffService;

public class StaffService implements IStaffService{
	
	private IStaffDAO staffDAO;

	@Override
	public Staff getStaffById(int id) {
		return staffDAO.findById(id);
	}
	public IStaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(IStaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

}
