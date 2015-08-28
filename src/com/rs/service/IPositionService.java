package com.rs.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.rs.model.Position;

public interface IPositionService {
	
	public boolean save(JSONObject jsonObject,int flag);//flag用来标志添加还是更新
	
	public List getAllPos();
	
	public boolean delete(JSONObject jsonObject);
	
	public List Search(JSONObject jsonObject);
}
