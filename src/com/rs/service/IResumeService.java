package com.rs.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IResumeService {

	int createResume(JSONObject jsonObject);

	List readResume(JSONObject jsonObject);

	int updateResume(JSONObject jsonObject);

	int deleteResume(JSONObject jsonObject);

	Map<String, Object> readAllResume();

}
