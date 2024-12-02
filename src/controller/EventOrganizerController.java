package controller;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.User;
import model.Vendor;
import repository.EventRepository;
import util.Response;

public class EventOrganizerController {
	
	
	public Response<List<Event>> viewOrganizedEvents(String userId) {
	    try {
	        List<Event> events = EventOrganizer.viewOrganizedEvents(userId);

	        if (events != null && !events.isEmpty()) {
	            return Response.success("Users fetched successfully.", events);
	        }
	        
	        return Response.failure("No events found or fetch operation failed.");
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return Response.failure("An error occurred while fetching events.");
	    }
	}
	
	public Response<List<User>> viewOrganizedEventDetails(String eventId) {
	    try {
	        List<User> guests = Guest.viewOrganizedEventDetails(eventId);
	        List<User> vendors = Vendor.getVendorsByTransaction(eventId);

	        List<User> participants = new ArrayList<>();
	        participants.addAll(guests);
	        participants.addAll(vendors);

	        return Response.success("Participants retrieved successfully", participants);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return Response.failure("An error occurred while fetching event participants.");
	    }
	}
}
	
