package com.majid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFetcher {
	private final String query="Select name,email from userInfo";
	private  Statement statement;
	private ResultSet resultSet;
	
	public  ResultSet fetchUserData(Connection connection) throws ClassNotFoundException, SQLException {
			try{
		       statement=connection.createStatement();
		       resultSet=statement.executeQuery(query);

			}
			catch(SQLException ex) {
				ex.getMessage();
			}
			return resultSet;
	}
	public boolean alreadyExits(Connection connection,DataFetcher getData,String name,String email) throws SQLException {
		
		try {
			ResultSet rs = getData.fetchUserData(connection);
			while(rs.next()) {
				if(rs.getString("name").equals(name) && rs.getString("email").equals(email)) {
					return true;
				}
			}
		}
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	
}
