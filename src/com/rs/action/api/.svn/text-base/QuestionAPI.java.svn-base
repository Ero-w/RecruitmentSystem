package com.rs.action.api;

import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.service.impl.QuestionService;

public class QuestionAPI extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private QuestionService service;
	private Map<String, Object> map;
	
	public String readAllQuestion(){
		map = service.readAllQuestion();
		return SUCCESS;
	}
	
	public QuestionService getService() {
		return service;
	}
	
	public void setService(QuestionService service) {
		this.service = service;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
