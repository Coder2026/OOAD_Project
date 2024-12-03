package repository;
import java.sql.ResultSet;

import database.DatabaseConnection;
import model.User;

public class InvitationRepository {
	
	public static String createInvitation(String eventId, String userId, String status, String role) {
	    DatabaseConnection db = DatabaseConnection.getInstance();

	    String query = String.format("INSERT INTO Invitation(event_id, user_id, status, role) "
	                                + "VALUES('%s', '%s', 'Pending', '%s')", eventId, userId, role);
	    
	    try {
	        db.executeUpdate(query);
	        return "Success";
	    } catch (Exception e) {
	        
	        return "Failed to create invitation. Error: " + e.getMessage();
	    }
	}
}
