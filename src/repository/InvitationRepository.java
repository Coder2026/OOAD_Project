package repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import model.User;

public class InvitationRepository {
	
    public static String createInvitation(String eventId, String userId, String role) {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String query = "INSERT INTO Invitation(event_id, user_id, status, role) VALUES (?, ?, 'Pending', ?)";
        
        try {
    
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
            	
                ps.setString(1, eventId);
                ps.setString(2, userId);
                ps.setString(3, role);
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "Failed to create invitation.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to create invitation. Error: " + e.getMessage();
        }
        return null;
    }
}
