package controller;

import model.Event;
import util.Response;

public class EventController {
	
	public static Response<String> createEvent(String eventName, String date, String locataion, String description, String organizerId) {
		
	    try {
	    	String createEventMessege = Event.createEvent(eventName, date, locataion, description, organizerId);
	        if (createEventMessege.equals("success")) {
	            return Response.success("Event creation successful", null);
	        } else {
	     
	            return Response.failure(createEventMessege);
	        }
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        return Response.failure("An error occurred during event creation. Please try again later.");
	    }
	}
}
