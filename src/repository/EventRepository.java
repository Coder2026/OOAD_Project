package repository;
import model.Event;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class EventRepository {
	
	 

	public static String createEvent(String eventName, String date, String location, String description, String organizerId) {
		DatabaseConnection db = DatabaseConnection.getInstance();
		
		String query = String.format("INSERT INTO Event(name,date,location,description,organizer_id) VALUES('%s','%s','s','%s','%s')",
				eventName,date,location,description, organizerId
				);
            try {
                db.executeUpdate(query);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
	}
	
	public static List<Event> findEventsById(String userId){
		DatabaseConnection db = DatabaseConnection.getInstance();
		
		List<Event> events = new ArrayList<>();
		
		String query = String.format("SELECT * FROM Event WHERE user_id = '%s'",userId);
		
		try {
			
			 ResultSet rs = db.executeQuery(query);
			 
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
	           
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	
	
}