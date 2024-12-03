package controller;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.Invitation;
import model.User;
import model.Vendor;
import repository.EventRepository;
import repository.InvitationRepository;
import util.Response;

public class EventOrganizerController {
	
	
	public Response<List<Event>> viewOrganizedEvents(String userId) {
	    try {
	        List<Event> events = EventOrganizer.viewOrganizedEvents(userId);

	        if (events != null && !events.isEmpty()) {
	        	return Response.success("Events retrieved successfully.", events);
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
	
    public Response<String> editEventName(String eventId, String eventName) {
        
    	String checkInput= checkInputEditName(eventName);
    	if(!checkInput.equals("valid")) {
    		return Response.failure(checkInput);
    	}
    	
    	try {
            String message = Event.editEventName(eventId, eventName);
            
            if(message.equals("success")) {
            	return Response.success("Event with ID " + eventId + " was successfully updated to name: " + eventName + ".",null);
            }
            return Response.failure(message);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure("An error occurred while editing the event name.");
        }
    }

   
    public String checkInputEditName(String eventName) {
        if (eventName == null || eventName.trim().isEmpty()) {
            return "Event name cannot be null or empty.";
        }
        
        return "valid";
    }
    
    public static Response<String> sendInvitation(String eventId, String userId, String status, String role) {
        try {
          
            String message = Invitation.sendInvitation(eventId, userId, status, role);
            
           
            if (message.equalsIgnoreCase("success")) {
                return Response.success(
                    "Invitation for event with ID " + eventId + " was successfully sent to user ID: " + userId + ".", 
                    null
                );
            }
           
            return Response.failure(message);
        } catch (Exception e) {
           
            e.printStackTrace();
            return Response.failure("An error occurred while sending the invitation: " + e.getMessage());
        }
    }
}
	
