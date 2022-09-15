package com.majid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String url="jdbc:mysql://localhost:3306/users";
		String user="root";
		String password="k20sw003";
		HttpSession session=req.getSession();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fetch values of user
		String userName=req.getParameter("username");
		String User_Password=req.getParameter("password");
		
	
		
		try(Connection connection=DriverManager.getConnection(url,user,password)) {
			Statement statement=connection.createStatement();
			
			ResultSet resultSet=statement.executeQuery("Select name,password from userinfo");
			
			while(resultSet.next()) {
				if(resultSet.getString("name").equals(userName) && resultSet.getString("password").equals(User_Password)) {
					session.setAttribute("name", userName);
					res.sendRedirect("index.jsp");
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
