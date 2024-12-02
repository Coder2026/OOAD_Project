package controller;
import java.util.List;
import model.Event;
import model.EventOrganizer;
import model.User;
import repository.EventRepository;
import util.Response;

public class EventOrganizerController {
	
	public Response<List<Event>> viewOrganizedEvents(String userId){
		
		 List<Event> events = EventOrganizer.viewOrganizedEvents(userId);

		    if (events != null && !events.isEmpty()) {
		        return Response.success("Users fetched successfully.", events);
		    }
		    
		    return Response.failure("No events found or fetch operation failed.");
	}
}
