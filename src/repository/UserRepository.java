package repository;

import java.sql.PreparedStatement;
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

	    String checkQuery = "SELECT * FROM User WHERE email = ? OR name = ?";

	    try {
	        PreparedStatement ps = db.preparedStatement(checkQuery);
	        if (ps != null) {
	            ps.setString(1, email);
	            ps.setString(2, name);

	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) { 
	                if (rs.getString("email").equals(email)) {
	                    return "Email is already registered!";
	                }
	                if (rs.getString("name").equals(name)) {
	                    return "Name is already registered!";
	                }
	            }
	        }

	        String insertQuery = "INSERT INTO User (email, name, password, role) VALUES (?, ?, ?, ?)";
	        PreparedStatement insertPs = db.preparedStatement(insertQuery);
	        if (insertPs != null) {
	            insertPs.setString(1, email);
	            insertPs.setString(2, name);
	            insertPs.setString(3, password);
	            insertPs.setString(4, role);

	            int rowsAffected = insertPs.executeUpdate();
	            if (rowsAffected > 0) {
	                return "success";
	            } else {
	                return "Failed to create user.";
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "An error occurred while creating the user. Please try again.";
	    }
	    return null;
	}

    public static User getUserIdByEmailAndPassword(String email, String password) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, email);
                ps.setString(2, password);
                

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new User(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getAllUser() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role != 'admin'";
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new User(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static List<User> getGuests() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role = 'Guest'";
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new Guest(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static List<User> getVendors() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role = 'Vendor'";
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new Vendor(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static String deleteUser(String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = "DELETE FROM User WHERE user_id = ?";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, userId);
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "No user found with the provided ID.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred while trying to delete the user with ID " + userId + ".";
        }
        return null;
    }

    public static List<User> getGuests(String eventId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<User> participants = new ArrayList<>();
        String query = "SELECT users.* FROM User users INNER JOIN Invitation invitations ON users.user_id = invitations.user_id WHERE invitations.event_id = ? and users.role = 'Guest'"
        		+ "AND invitations.status = 'Accepted'";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, eventId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    participants.add(new Guest(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    public static List<User> getVendors(String eventId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<User> participants = new ArrayList<>();
        String query = "SELECT users.* FROM User users INNER JOIN Invitation invitations ON users.user_id = invitations.user_id WHERE invitations.event_id = ? and users.role = 'Vendor'"
        		+ "AND invitations.status = 'Accepted'";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, eventId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    participants.add(new Vendor(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }
}
