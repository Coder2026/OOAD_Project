package repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Invitation;
import model.User;

public class InvitationRepository {
	
    public static String createInvitation(String eventId, String userId, String role) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        String checkQuery = "SELECT COUNT(*) AS count FROM Invitation WHERE event_id = ? AND user_id = ?";

        String query = "INSERT INTO Invitation(event_id, user_id, status, role) VALUES (?, ?, 'Pending', ?)";
        
        try {
        	
        	
            PreparedStatement checkPs = db.preparedStatement(checkQuery);
            if (checkPs != null) {
                checkPs.setString(1, eventId);
                checkPs.setString(2, userId);
                
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count");
                    if (count > 0) {
                        return role +" is already invited to this event.";
                    }
                }
            }
            
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
    
    public static String acceptInvitation(String eventId, String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String updateQuery = "UPDATE Invitation SET status = 'Accepted' WHERE event_id = ? AND user_id = ? AND status = 'Pending'";

        try {
            PreparedStatement ps = db.preparedStatement(updateQuery);
            if (ps != null) {
                ps.setString(1, eventId);
                ps.setString(2, userId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "No pending invitation found to accept.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to accept invitation. Error: " + e.getMessage();
        }
        return null;
    }
    
    public static List<Invitation> getAcceptedInvitations(String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Invitation> invitations = new ArrayList<>();
        String query = "SELECT * FROM Invitation WHERE user_id = ? AND status = 'Accepted'";
        
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, userId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Invitation invitation = new Invitation(
                    	rs.getString("invitation_id"),
                        rs.getString("event_id"),
                        rs.getString("user_id"),
                        rs.getString("status"),
                        rs.getString("role")
                    );
                    invitations.add(invitation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invitations;
    }
    
    
    
}

