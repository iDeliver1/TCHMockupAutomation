package com.AP.qa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnect {
	
	static Connection con = null;
	
	// Object of Statement. It is used to create a Statement to execute the query
	 static Statement stmt = null;
	 
	//Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
	 static ResultSet resultSet = null;

	static {
		String JdbcURL = "jdbc:mysql://localhost:3306/sampledb?" + "autoReconnect=true&useSSL=false";
		   String Username = "root";
		   String password = "iDeliver1";
		   
		   try {
			   Class.forName("com.mysql.jdbc.Driver");
			   con = DriverManager.getConnection(JdbcURL, Username, password);
				 System.out.println("Your JDBC URL is as follows:" + JdbcURL);
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	}
	
	public String getdbdata(String cred) throws SQLException {
		
		String dbdata = null;
		// Execute a query
				 stmt = con.createStatement();
				 resultSet = stmt.executeQuery("select * from logindata");
				 
				 while(resultSet.next()) {
				    dbdata = resultSet.getString(cred);
				 	}
		return dbdata;		 
	}
	
	public void closedb() {
		if (resultSet != null) {
			 try {
			 resultSet.close();
			 } catch (Exception e) {}
			 }
			 if (stmt != null) {
			 try {
			 stmt.close();
			 } catch (Exception e) {}
			}
			if (con != null) {
			 try {
			 con.close();
			 } catch (Exception e) {}
			}
	}
}
