package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.User;
import database.DatabaseConnection;
import model.Vendor;

public class VendorRepository {

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
	            			rs.getString("user_id"),
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
//	    return null;
		return null;
	}
}
