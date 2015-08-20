package com.rs.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

public class CheckQuestion extends ActionSupport {
	private int count=0;
	public void check() throws ClassNotFoundException, SQLException, IOException{
		
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/recruitment_system";
			String usernameMysql="root";
			String passwordMysql="root";
			Connection conn=DriverManager.getConnection(url,usernameMysql,passwordMysql);
			Statement stmt=conn.createStatement();
			String sql="select * from question";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())count++;
			String[][] question=new String[count][3];
			rs=stmt.executeQuery(sql);
			for(int i=0,k=count;k>0;k--,i++){
				rs.next();
				question[i][0]=String.valueOf(rs.getInt("qid"));
				question[i][1]=rs.getString("title");
				question[i][2]=rs.getString("answer");
			}
			rs.close();
			stmt.close();
			conn.close();
			JSONArray json=JSONArray.fromObject(question);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json.toString());
			
		
	}
}
