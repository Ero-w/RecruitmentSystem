package com.rs.service.impl;

import java.util.List;

import com.rs.dao.IInterviewDAO;
import com.rs.dao.impl.InterviewDAO;
import com.rs.dao.impl.StaffDAO;
import com.rs.model.Interview;
import com.rs.model.Staff;
import com.rs.service.IInterviewService;

public class InterviewService implements IInterviewService{

	private IInterviewDAO interviewDAO;
	
	@Override
	public Interview findById(Integer id) {
		// TODO Auto-generated method stub
		return interviewDAO.findById(id);
	}


	public IInterviewDAO getInterviewDAO() {
		return interviewDAO;
	}


	public void setInterviewDAO(IInterviewDAO interviewDAO) {
		this.interviewDAO = interviewDAO;
	}


	@Override
	public void saveInterview(Interview interview) {
		// TODO Auto-generated method stub
		interviewDAO.save(interview);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		//System.out.println("InterviewService");
		List list = interviewDAO.findAll();
		//System.out.println("list.size() = "+list.size());
		if(list.size() == 0)
			return null;
		else return list;
	}


	@Override
	public void deleteInterview(Interview interview) {
		// TODO Auto-generated method stub
		interviewDAO.delete(interview);
	}

	@Override
	//使用关联映射获得面试人对应的面试人信息。
	public Staff getStaff(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("getStaff(1)");		
		return interviewDAO.findById(id).getStaff();
	}


	@Override
	public void attachDirty(Interview interview) {
		// TODO Auto-generated method stub
		interviewDAO.attachDirty(interview);
	}

}
