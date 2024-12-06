package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseConnection {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE_NAME = "stellarfest";
	
	private final String HOST = "localhost";
	private final String PORT = "3306";
	
	private final String CONNECTION = String.format("jdbc:mysql://%s:%s/%s", 
			HOST,
			PORT,
			DATABASE_NAME);
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement ps;
	
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con  = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static DatabaseConnection instance = null;
	
	public static DatabaseConnection getInstance() {
		
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
		
	}

	public ResultSet executeQuery(String query) {
		try {
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement preparedStatement(String query) {
	    try {
	    	ps = con.prepareStatement(query);
	    } catch (SQLException e) {
	        e.printStackTrace();
	       
	    }
	    return ps;
	}
}
