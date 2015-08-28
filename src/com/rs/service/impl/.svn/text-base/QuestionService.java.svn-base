package com.rs.service.impl;

import java.util.HashMap;
import java.util.Map;
import com.rs.dao.impl.QuestionDAO;

public class QuestionService {
	private QuestionDAO questionDAO;
	private Map<String, Object> map=new HashMap<String, Object>();
	
	public Map<String, Object> readAllQuestion(){
		map.put("map", questionDAO.findAll());
		return map;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

}
