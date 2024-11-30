package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.User;

public class UserRepository {
    
    private DatabaseConnection db;

    public UserRepository() {
        db = DatabaseConnection.getInstance();
    }

    // Create a new user
    public boolean createUser(User user) {
        String query = String.format(
            "INSERT INTO User (user_id, email, name, password, role) VALUES ('%s', '%s', '%s', '%s', '%s')",
            user.getUser_id(), user.getUser_email(), user.getUser_name(), user.getUser_password(), user.getUser_role()
        );
        try {
            db.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Find a user by ID
    public User findById(String userId) {
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

    // Find all users
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                users.add(new User(
                    rs.getString("user_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update a user
    public boolean updateUser(User user) {
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

    // Delete a user by ID
    public boolean deleteUser(String userId) {
        String query = String.format("DELETE FROM User WHERE user_id = '%s'", userId);
        try {
            db.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
