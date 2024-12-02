package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.User;

public class UserRepository {
    
   
	public static String createUser(User user) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    String checkQuery = String.format("SELECT COUNT(*) FROM User WHERE email = '%s'", user.getUser_email());
	    System.out.println(checkQuery);

	    try {
	        ResultSet rs = db.executeQuery(checkQuery);
	        if (rs.next() && rs.getInt(1) > 0) {
	            return "Email is already registered!";
	        }

	        String insertQuery = String.format(
	            "INSERT INTO User (email, name, password, role) VALUES ('%s', '%s', '%s', '%s')",
	            user.getUser_email(), user.getUser_name(), user.getUser_password(), user.getUser_role()
	        );
	        System.out.println(insertQuery);

	        db.executeUpdate(insertQuery);
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred while creating the user. Please try again.";
	    }
	}
  
    public User findUserById(String userId) {
    	
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

   
    public static List<User> findAll() {
    	
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

    
    public static boolean updateUser(User user) {
    	DatabaseConnection db = DatabaseConnection.getInstance();
    	
        String query = String.format(
            "UPDATE User SET email = '%s', name = '%s', password = '%s', role = '%s' WHERE user_id = '%s'",
            user.getUser_email(), user.getUser_name(), user.getUser_password(), user.getUser_role(), user.getUser_id()
        );
        try {
            db.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String deleteUser(String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = String.format("DELETE FROM User WHERE user_id = '%s'", userId);

        try {
            db.executeUpdate(query); 
            return "User with ID " + userId + " was successfully deleted.";
        } catch (Exception e) {
            e.printStackTrace(); 
            return "An error occurred while trying to delete the user with ID " + userId + ".";
        }
    }
}
