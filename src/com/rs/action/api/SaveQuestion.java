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

public class SaveQuestion extends ActionSupport {
	public String[][] question;
	public int count=0;
	public void save() throws ClassNotFoundException, SQLException, IOException{
	HttpServletRequest request=ServletActionContext.getRequest();
	String str=request.getParameter("question");
	JSONArray mainArray=JSON.parseArray(str);
	question=new String[mainArray.size()][3];
	//取得要保存的问题列表
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
	String sql="select * from question";
	ResultSet rs=stmt.executeQuery(sql);
	//统计现有问题数
	while(rs.next())count++;
	String[][] a=new String[count][2];
	rs=stmt.executeQuery(sql);
	for(int x=0;x<count;x++){
		rs.next();
		a[x][0]=String.valueOf(rs.getInt("qid"));
		a[x][1]="1";
	}
	//比对发现被删除的问题
	for(int z=0;z<a.length;z++){
		int y=0;
		for(;y<question.length;y++){
			if(a[z][0].equals(question[y][0]))
				break;
			}
		if(y>=question.length)a[z][1]="0";
	}
	//对所有问题进行更新
	for(int i=0;i<question.length;i++){
		sql="update question set title='"+question[i][1]+"',answer='"+question[i][2]+"' where qid="+question[i][0];
		stmt.executeUpdate(sql);
	}
	//把被删除的问题从数据库移除
	for(int i=0;i<a.length;i++){
		if(a[i][1]=="0"){
			sql="delete from question where qid='"+a[i][0]+"'";
			stmt.executeUpdate(sql);
		}
	}
	rs.close();
	stmt.close();
	conn.close();
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write("ok");
	}
}
