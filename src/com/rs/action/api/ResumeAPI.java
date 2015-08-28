package com.rs.action.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.model.Resume;
import com.rs.service.IResumeService;

public class ResumeAPI extends ActionSupport {
	private JSONObject jsonObject;
	
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	private IResumeService resumeService;
	
	public String readAllResume(){
		map = resumeService.readAllResume();
		return SUCCESS;
	}
	
	//http://localhost:8080/rs/re_createResume?json={userId:'1',education:'本科',skill:'C++',experience:'经历'}
	public String createResume(){
		System.out.println(jsonObject);
		int state = resumeService.createResume(jsonObject);
		System.out.println(state);
		System.out.println("resumeAPI is over");
		map.put("state", state);
		return SUCCESS;
	}
	
	public String readResume(){
		System.out.println("====================re_readResume===============");
		List Resumelist = resumeService.readResume(jsonObject);
		if(!Resumelist.isEmpty()){
			List list = new ArrayList();
			for(int i=0;i<Resumelist.size();i++){
				Resume resume = new Resume();
				resume.setRid(((Resume)(Resumelist.get(i))).getRid());
				resume.setCreateDate(((Resume)(Resumelist.get(i))).getCreateDate());
				resume.setEducation(((Resume)(Resumelist.get(i))).getEducation());
				resume.setExperience(((Resume)(Resumelist.get(i))).getExperience());
				resume.setSkill(((Resume)(Resumelist.get(i))).getSkill());
				resume.setCreateDate(((Resume)(Resumelist.get(i))).getCreateDate());
				list.add(resume);
			}
			map.put("list", list);
			map.put("state", 1);
		}
		else{
			map.put("state", -1);
		}
		return SUCCESS;
	}
	
	public String updateResume(){
		
		int state = resumeService.updateResume(jsonObject);
		map.put("state", state);
		return SUCCESS;
	}
	
	public String deleteResume(){
		
		int state = resumeService.deleteResume(jsonObject);
		map.put("state", state);
		return SUCCESS;
	}
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
		try {//转换json对象
			jsonObject=JSONObject.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject=null;
		}
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public IResumeService getResumeService() {
		return resumeService;
	}
	public void setResumeService(IResumeService resumeService) {
		this.resumeService = resumeService;
	}
	
	
}
