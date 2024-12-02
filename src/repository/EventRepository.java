package repository;

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
}
