package com.rs.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;

public class SaveQuestion4Interview extends ActionSupport {
	public String[][] question;
	public int count=0;
	public void save() throws ClassNotFoundException, SQLException, IOException{
	int sid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("sid").toString());
	HttpServletRequest request=ServletActionContext.getRequest();
	String str=request.getParameter("question");
	JSONArray mainArray=JSON.parseArray(str);
	question=new String[mainArray.size()][3];
	for(int i=0;i<mainArray.size();i++){
		JSONArray paramObject=(JSONArray)mainArray.get(i);
		int x=0;
		while(x<3){
		question[i][x]=paramObject.get(x).toString();
		x++;
		}
	}
	Class.forName("com.mysql.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/recruitment_system";
	String usernameMysql="root";
	String passwordMysql="root";
	Connection conn=DriverManager.getConnection(url,usernameMysql,passwordMysql);
	Statement stmt=conn.createStatement();
	String sql="delete from question where sid="+sid;
	stmt.executeUpdate(sql);
	for(int i=0;i<question.length;i++){
		sql="insert into question values("+question[i][0]+",'"+question[i][1]+"','"+question[i][2]+"',1,"+sid+")";
		stmt.executeUpdate(sql);
	}
	stmt.close();
	conn.close();
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write("ok");
	}
}
