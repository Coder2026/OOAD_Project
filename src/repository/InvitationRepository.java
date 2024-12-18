package repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Event;
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
    
    public static List<Event> getAcceptedInvitations(String email) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.event_id, e.name, e.date, e.location, e.description, e.organizer_id " +
                       "FROM Invitation i " +
                       "JOIN User u ON i.user_id = u.user_id " +
                       "JOIN Event e ON i.event_id = e.event_id " +
                       "WHERE u.email = ? AND i.status = 'Accepted'";
        
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event(
                        rs.getString("event_id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("organizer_id")
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public static List<Event> getInvitations(String email) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.event_id, e.name, e.date, e.location, e.description, e.organizer_id " +
                "FROM Invitation i " +
                "JOIN User u ON i.user_id = u.user_id " +
                "JOIN Event e ON i.event_id = e.event_id " +
                "WHERE u.email = ? AND i.status = 'Pending'";
        
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event(
                            rs.getString("event_id"),
                            rs.getString("name"),
                            rs.getString("date"),
                            rs.getString("location"),
                            rs.getString("description"),
                            rs.getString("organizer_id")
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    
    
}

