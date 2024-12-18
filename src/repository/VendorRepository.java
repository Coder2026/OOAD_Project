package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Vendor;

public class VendorRepository {

	public static Vendor getProduct(String vendorId) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    String query = "SELECT * FROM Vendor WHERE vendor_id = ?";

	    try {
	        PreparedStatement ps = db.preparedStatement(query);
	        if (ps != null) {
	            ps.setString(1, vendorId);

	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) { 
	            	Vendor currVendor = new Vendor(
	            			rs.getString("vendor_id"),
	            			"",
	            			"",
	            			"",
	            			"",
	            			"",
	            			rs.getString("name"),
	            			rs.getString("description")
	            			
	            		);
	            	 return currVendor;
	            }
	           
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
		return null;
	}
	
	public static boolean upsertVendor(String vendorId, String name, String description, String product) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    // Query untuk memeriksa apakah vendor_id sudah ada
	    String checkQuery = "SELECT COUNT(*) FROM Vendor WHERE vendor_id = ?";
	    // Query untuk insert data baru
	    String insertQuery = "INSERT INTO Vendor (vendor_id, name, description, product) VALUES (?, ?, ?, ?)";
	    // Query untuk update data yang sudah ada
	    String updateQuery = "UPDATE Vendor SET name = ?, description = ?, product = ? WHERE vendor_id = ?";

	    try {
	        PreparedStatement checkStmt = db.preparedStatement(checkQuery);
	        if (checkStmt != null) {
	            checkStmt.setString(1, vendorId);
	            ResultSet rs = checkStmt.executeQuery();
	            
	            if (rs.next() && rs.getInt(1) > 0) {
	                // Jika vendor_id sudah ada, lakukan update
	                PreparedStatement updateStmt = db.preparedStatement(updateQuery);
	                if (updateStmt != null) {
	                    updateStmt.setString(1, name);
	                    updateStmt.setString(2, description);
	                    updateStmt.setString(3, product);
	                    updateStmt.setString(4, vendorId);
	                    
	                    int rowsAffected = updateStmt.executeUpdate();
	                    return rowsAffected > 0;
	                }
	            } else {
	                // Jika vendor_id belum ada, lakukan insert
	                PreparedStatement insertStmt = db.preparedStatement(insertQuery);
	                if (insertStmt != null) {
	                    insertStmt.setString(1, vendorId);
	                    insertStmt.setString(2, name);
	                    insertStmt.setString(3, description);
	                    insertStmt.setString(4, product);
	                    
	                    int rowsAffected = insertStmt.executeUpdate();
	                    return rowsAffected > 0;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return false;
	}

	
	public static boolean createProduct(String vendorId, String name, String description, String product) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    String query = "INSERT INTO Vendor (vendor_id, name, description, product) VALUES (?, ?, ?, ?)";

	    try {
	        PreparedStatement ps = db.preparedStatement(query);
	        if (ps != null) {
	            ps.setString(1, vendorId);
	            ps.setString(2, name);
	            ps.setString(3, description);
	            ps.setString(4, product);

	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return false;
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

}
