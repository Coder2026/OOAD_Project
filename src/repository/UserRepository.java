package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Guest;
import model.User;
import model.Vendor;

public class UserRepository {
    
   
	public static String createUser(String email, String name, String password, String role) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    String checkQuery = String.format(
	            "SELECT email, name FROM User WHERE email = '%s' OR name = '%s'", 
	            email, name
	        );
	    

	    try {
	    		
	        ResultSet rs = db.executeQuery(checkQuery);
	        if (rs.next()) { 
	            if (rs.getString("email").equals(email)) {
	                return "Email is already registered!";
	            }
	            if (rs.getString("name").equals(name)) {
	                return "Name is already registered!";
	            }
	        }

	        String insertQuery = String.format(
	            "INSERT INTO User (email, name, password, role) VALUES ('%s', '%s', '%s', '%s')",
	            email,name,password,role
	        );
	        System.out.println(insertQuery);

	        db.executeUpdate(insertQuery);
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred while creating the user. Please try again.";
	    }
	}
  
    public User getUserById(String userId) {
    	
    	DatabaseConnection db = DatabaseConnection.getInstance();
    	
        String query = String.format("SELECT * FROM User WHERE user_id = '%s'", userId);
        try {
            ResultSet rs = db.executeQuery(query);
            if (rs.next()) {
                return new User(
                    rs.getString("user_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static User getUserIdByEmailAndPassword(String email, String password) {
    	DatabaseConnection db = DatabaseConnection.getInstance();
        String query = String.format(
            "SELECT user_id FROM User WHERE email = '%s' AND password = '%s'",
            email, password
        );

        try {
            ResultSet rs = db.executeQuery(query);
            if (rs.next()) {
                return new User(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        rs.getString("role")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public static List<User> getAllUser() {
    	
    	DatabaseConnection db = DatabaseConnection.getInstance();
    	
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                users.add(new User(
                    rs.getString("user_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    null,
                    null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static String deleteUser(String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = String.format("DELETE FROM User WHERE user_id = '%s'", userId);

        try {
            db.executeUpdate(query); 
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); 
            return "An error occurred while trying to delete the user with ID " + userId + ".";
        }
    }
    
	public static List<User> getGuests(String eventId){
		DatabaseConnection db = DatabaseConnection.getInstance();
		List<User> participants = new ArrayList<>();
		String query = String.format("SELECT users.*\n"
				+ "FROM User users\n"
				+ "INNER JOIN Event events\n"
				+ "ON users.user_id = events.organized_id\n"
				+ "WHERE events.event_id = '%s' and users.role = 'Guest'",eventId);
		
		try {
	        ResultSet rs = db.executeQuery(query);
	        while (rs.next()) {
	            participants.add(new Guest(
		                rs.getString("user_id"),
		                rs.getString("email"),
		                rs.getString("name"),
		                null,
		                null
		            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return participants;
	}
	
	public static List<User> getVendors(String eventId){
		DatabaseConnection db = DatabaseConnection.getInstance();
		List<User> participants = new ArrayList<>();
		String query = String.format("SELECT users.*\n"
				+ "FROM User users\n"
				+ "INNER JOIN Event events\n"
				+ "ON users.user_id = events.organized_id\n"
				+ "WHERE events.event_id = '%s' and users.role = 'Vendor'",eventId);
		
		try {
	        ResultSet rs = db.executeQuery(query);
	        while (rs.next()) {
	            participants.add(new Vendor(
		                rs.getString("user_id"),
		                rs.getString("email"),
		                rs.getString("name"),
		                null,
		                null
		            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return participants;
	}
}
