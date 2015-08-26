package com.rs.action.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.rs.model.Account;
import com.rs.service.IAccountService;

public class AccountStaffAPI extends ActionSupport {
	
	private JSONObject jsonObject;
	
	//json接收前台传过来的字符串，在setJson()时解析成jsonObject;
	private String json;
	//map为返回给前台的json
	private Map<String, Object> map=new HashMap<String, Object>();
	private IAccountService accountService;
	
	//http://localhost:8080/rs/as_login?json={user:'123',password:'123'}
	public String login() throws ClassNotFoundException, SQLException{
		
		if(jsonObject==null){//传入参数为空
			map.put("state", -100);
		}else{
			int state=accountService.login(new Account(jsonObject.getString("user"), jsonObject.getString("password")));
			map.put("state", state);
			if(state==1){
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/recruitment_system";
				String usernameMysql="root";
				String passwordMysql="root";
				Connection conn=DriverManager.getConnection(url,usernameMysql,passwordMysql);
				Statement stmt=conn.createStatement();
				String sql="select * from staff natural join account where aid='"+jsonObject.getString("user")+"'";
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()){
					ServletActionContext.getRequest().getSession().setAttribute("username", rs.getString("sname"));
					ServletActionContext.getRequest().getSession().setAttribute("sid", rs.getInt("sid"));
					ServletActionContext.getRequest().getSession().setAttribute("auth", rs.getInt("priority"));
				}
				rs.close();
				stmt.close();
				conn.close();
				
			}
		}
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
	public IAccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

}
