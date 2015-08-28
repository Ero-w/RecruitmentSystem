package com.rs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rs.dao.impl.ApplyDAO;
import com.rs.dao.impl.ResumeDAO;
import com.rs.dao.impl.StaffDAO;
import com.rs.model.Apply;
import com.rs.model.Resume;
import com.rs.model.Staff;
import com.rs.service.IResumeService;

public class ResumeService implements IResumeService{
	private ResumeDAO resumeDAO;
	private StaffDAO staffDAO;
	private ApplyDAO applyDAO;
	private Map<String, Object> map=new HashMap<String, Object>();
	
	@Override
	public int createResume(JSONObject jsonObject) {
		if(jsonObject!=null){
			Resume resume = new Resume();
			resume.setEducation(jsonObject.getString("education"));
			resume.setExperience(jsonObject.getString("experience"));
			resume.setSkill(jsonObject.getString("skill"));
			resume.setCreateDate(new Date());
			
			Staff staff = new Staff();
			staff = staffDAO.findById(jsonObject.getInteger("userId"));
			if(staff!=null){
				resume.setStaff(staff);
				System.out.println(resume.getExperience() + "经验");
				resumeDAO.save(resume);
				return 1;
			}
			else{
				System.out.println("staff is null");
				return -2;
			}
		}
		else{
			System.out.println("jsonObject is null");
			return -1;
		}
	}
		
	private List readById() {
//		Integer sId = (Integer) ActionContext.getContext().getSession().get("sId");
		Integer sId = 1;
		return resumeDAO.findByStraffId("staff",1);
		
	}
	
	private List readByInterview() {
		System.out.println("readByInterview");
		List<Apply> list = applyDAO.findAll();
		Staff staff;
		List<Resume> listResume;
		List<Resume> resumeLists = new ArrayList<Resume>();
		if(!list.isEmpty()){
			for (int i=0;i<list.size();i++) {
				listResume = resumeDAO.findByStraffId("staff", ((list.get(i)).getStaff().getSid()));
				if(!listResume.isEmpty()){
					resumeLists.add((Resume)(listResume.get(0)));
				}
			}
		}
		
		System.out.println("resumelist length"+resumeLists.size());
		return resumeLists;
	}

	public Map<String, Object> readAllResume(){
		map.put("map", resumeDAO.findAll());
		return map;
	}
	
	private List readByManager() {
		System.out.println("readByManager");
		return resumeDAO.findAll();
	}
	
	@Override
	public List readResume(JSONObject jsonObject) {
		System.out.println("readResume" + jsonObject.getInteger("sign"));
		int sign = jsonObject.getInteger("sign");
		List list=null;
		switch (sign) {
		case 0:
			list = readById();
			break;
		case 1:
			list = readByManager();
			break;
		case 2:
			list = readByInterview();
			break;

		default:
			break;
		}
		return list;
	}
	
	@Override
	public int updateResume(JSONObject jsonObject) {
//		Integer sId = (Integer) ActionContext.getContext().getSession().get("sId");
		Integer sId = 1;
//		System.out.println("=========updateResume's id :" + jsonObject.getInteger("rid") + "==========");
//		Resume resume = resumeDAO.findById(jsonObject.getInteger("rid"));
		Resume resume = (Resume) ((resumeDAO.findByStraffId("staff", sId)).get(0));
		if(resume!=null){
			resume.setEducation(jsonObject.getString("education"));
			resume.setExperience(jsonObject.getString("experience"));
			resume.setSkill(jsonObject.getString("skill"));
			resume.setCreateDate(new Date());
			resumeDAO.attachDirty(resume);
			return 1;
		}
		else {
			return -1;
		}
	}
	
	@Override
	public int deleteResume(JSONObject jsonObject) {
		System.out.println("=======deleteResume============");
		Resume resume = resumeDAO.findById(jsonObject.getInteger("rid"));
		if(resume!=null){
			resumeDAO.delete(resume);
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public ResumeDAO getResumeDAO() {
		return resumeDAO;
	}
	public void setResumeDAO(ResumeDAO resumeDAO) {
		this.resumeDAO = resumeDAO;
	}

	public StaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

	public ApplyDAO getApplyDAO() {
		return applyDAO;
	}

	public void setApplyDAO(ApplyDAO applyDAO) {
		this.applyDAO = applyDAO;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}


