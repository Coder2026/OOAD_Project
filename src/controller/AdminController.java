package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Vendor;
import model.Admin;
import model.Event;
import model.EventOrganizer;
import model.Guest;
import util.Response;


public class AdminController {
	
	public Response<List<User>> getAllUser() {
	    List<User> users = User.getAllUser();

	    if (users != null && !users.isEmpty()) {
	        return Response.success("Users fetched successfully.", users);
	    }
	    return Response.failure("No users found or fetch operation failed.");
	}
	
	public Response<String> deleteUser(String userId) {
	    try {
	    	
	        String message = Admin.deleteUser(userId);
	        
	        if(message.equals("success")) {
	        	return Response.success("User with ID " + userId + " was successfully deleted.", null);
	        }

	        return Response.failure(message);
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	        
	        return Response.failure("An error occurred while trying to delete the user.");
	    }
	}
	
	public Response<List<User>> viewEventDetails(String eventId) {
	    try {
	        List<User> guests = Guest.getGuestByTransaction(eventId);
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
	
	public Response<List<Event>> viewAllEvents() {
	    try {
	        List<Event> events = Event.viewAllEvents();

	        if (events != null && !events.isEmpty()) {
	        	return Response.success("Events retrieved successfully.", events);
	        }
	        
	        return Response.failure("No events found or fetch operation failed.");
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return Response.failure("An error occurred while fetching events.");
	    }
	}
	
	public Response<String> deleteEvent(String eventId) {
	    try {
	    	
	        String message = Event.deleteEvent(eventId);
	        
	        if(message.equals("success")) {	
	        	return Response.success("Event with ID " + eventId + " was successfully deleted.", null);
	        }
	        
	        return Response.failure(message);
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	        
	        return Response.failure("An error occurred while trying to delete the event.");
	    }
	}
	
}
