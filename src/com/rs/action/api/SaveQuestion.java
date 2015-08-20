package com.rs.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SaveQuestion extends ActionSupport {
	public String[][] question;
	public int count=0;
	public void save() throws ClassNotFoundException, SQLException, IOException{
	Class.forName("com.mysql.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/recruitment_system";
	String usernameMysql="root";
	String passwordMysql="root";
	Connection conn=DriverManager.getConnection(url,usernameMysql,passwordMysql);
	Statement stmt=conn.createStatement();
	String sql="select * from question";
	ResultSet rs=stmt.executeQuery(sql);
	while(rs.next())count++;
	String[][] a=new String[count][1];
	rs=stmt.executeQuery(sql);
	for(int x=0;x<count;x++){
		rs.next();
		a[x][0]=String.valueOf(rs.getInt("qid"));
		a[x][1]="1";
	}
	for(int z=0;z<question.length;z++){
		int y=0;
		for(;y<a.length;y++){
			if(question[z][0]==a[y][0])break;
			}
		if(y>=a.length)a[y][1]="0";
	}
	for(int i=0;i<question.length;i++){
		sql="update question set title='"+question[i][1]+"',answer='"+question[i][2]+"' where qid="+question[i][0];
		stmt.executeUpdate(sql);
	}
	for(int i=0;i<a.length;i++){
		if(a[i][1]=="0"){
			sql="delete from question where qid='"+a[i][0]+"'";
			stmt.executeUpdate(sql);
		}
	}
	stmt.close();
	conn.close();
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write("ok");
	}
}
