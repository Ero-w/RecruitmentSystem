package com.rs.service.impl;

import com.rs.dao.IApplyDAO;
import com.rs.service.IApplyService;

public class ApplyService implements IApplyService{
	
	private IApplyDAO applyDAO;

	public IApplyDAO getApplyDAO() {
		return applyDAO;
	}

	public void setApplyDAO(IApplyDAO applyDAO) {
		this.applyDAO = applyDAO;
	}

}
