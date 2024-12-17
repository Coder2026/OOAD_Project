package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
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
