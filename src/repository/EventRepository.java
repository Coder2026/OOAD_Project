package repository;
import model.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class EventRepository {

    public static String createEvent(String eventName, String date, String location, String description, String organizerId) {
        DatabaseConnection db = DatabaseConnection.getInstance();

        String query = "INSERT INTO Event(name, date, location, description, organizer_id) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
               
                ps.setString(1, eventName);
                ps.setString(2, date);
                ps.setString(3, location);
                ps.setString(4, description);
                ps.setString(5, organizerId);

             
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "Failed to create event.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return null;
    }

    public static List<Event> getEventsById(String userId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        List<Event> events = new ArrayList<>();
        
        String query = "SELECT * FROM Event WHERE organizer_id = ?";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, userId);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    events.add(new Event(
                        rs.getString("event_id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("organizer_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static List<Event> getAllEvents() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        List<Event> events = new ArrayList<>();
        
        String query = "SELECT * FROM Event";
        
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    events.add(new Event(
                        rs.getString("event_id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("organizer_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static String deleteEvent(String eventId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = "DELETE FROM Event WHERE event_id = ?";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, eventId);

                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "Failed to delete event.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred while trying to delete the event with ID " + eventId + ".";
        }
        return null;
    }

    public static String updateEventNameById(String eventId, String eventName) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String query = "UPDATE Event SET name = ? WHERE event_id = ?";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, eventName);
                ps.setString(2, eventId);

         
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return "success";
                } else {
                    return "Failed to update event.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred while trying to update the event with ID " + eventId + ".";
        }
        return null;
    }
}