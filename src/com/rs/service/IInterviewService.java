package com.rs.service;

import java.util.List;

import com.rs.model.Interview;
import com.rs.model.Staff;

public interface IInterviewService {
	
	public List findAll();	//显示所有面试人信息
	
	public Interview findById(java.lang.Integer id);//查找面试人
	
	public void saveInterview(Interview interview);//增加面试人
	
	public void deleteInterview(Interview interview);//删除面试人
	
	public void attachDirty(Interview interview);//更新面试人信息
	
	public Staff getStaff(java.lang.Integer id);

}
