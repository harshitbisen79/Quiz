package com.quiz.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class DatabaseConnection {
	
	private static Connection con;

	public static void registerUser(String query) throws SQLException, ClassNotFoundException{
		getConnection();
		// 3. create statement
		Statement statement = con.createStatement();
		// 4. submit SQL statement to database
		statement.execute(query);

		con.close();
		statement.close();
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// 1. Loading the driver class
		Class.forName("com.mysql.jdbc.Driver");
		// 2. Establish the connection
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");
		 return con;
	}
	
	
	public static boolean loginUserConnection(String query) throws SQLException{

		// 3. prepared SQL statement
		PreparedStatement ps = con.prepareStatement(query);
		// 4. use executeQuery() method
		ResultSet rs = (ResultSet) ps.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			System.out.println("Invalid login credentials");
			return false;
		}

	}
	
}
