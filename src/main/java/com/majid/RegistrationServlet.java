package com.majid;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ModuleDescriptor.Requires;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	DataFetcher datafetcher;
	@Override
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		PrintWriter out=res.getWriter();
		
		//fetch values from user
		 String name=req.getParameter("name");
		 String email=req.getParameter("email");
		 String u_password=req.getParameter("pass");
		 String mobile_no=req.getParameter("contact");
		 
		 datafetcher =new DataFetcher();
		 
	
	String url="jdbc:mysql://localhost:3306/users";
	String user="root";
	String password="k20sw003";
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		try(Connection connection=DriverManager.getConnection(url,user,password)) {
			
			ResultSet set=datafetcher.fetchUserData(connection);
			boolean isRegistered=datafetcher.alreadyExits(connection,datafetcher, name, email);
		
			if(isRegistered) {
				out.print("You are already registered user so please try to login");
				res.sendRedirect("login.jsp");
				
			}else {
				PreparedStatement statement=connection.prepareStatement("insert into userinfo(name,email,password,mobile_no) values(?,?,?,?);");
				statement.setString(1,name);
				statement.setString(2, email);
				statement.setString(3, u_password);
				statement.setString(4, mobile_no);
				int roweffected=statement.executeUpdate();
				out.print("You are successfully registered!!!");
				res.sendRedirect("login.jsp");
			}
		}catch(SQLException | ClassNotFoundException ex){
			out.print(ex.getMessage());
			ex.getMessage();
		}
		
		
		

	
	}

}
