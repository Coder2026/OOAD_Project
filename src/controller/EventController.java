package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import model.Event;
import util.Response;

public class EventController {
	
	public Response<String> createEvent(String eventName, String date, String location, String description, String organizerId) {
		
	    try {
	    	
	        String checkInput = checkCreateEventInput(eventName,date,location,description,organizerId);
		    if (!checkInput.equals("valid")) {
		        return Response.failure(checkInput);
		    }
		    
	    	String createEventMessege = Event.createEvent(eventName, date, location, description, organizerId);
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
	
	   
	public String checkCreateEventInput(String eventName, String date, String location, String description, String organizerId) {
	    
	    if (eventName.isEmpty()) {
	        return "Event Name cannot be empty!";
	    }
	   
	    if (date.isEmpty()) {
	        return "Event Date cannot be empty!";
	    }
	    
	    try {
	        LocalDate eventDate = LocalDate.parse(date); 
	        if (eventDate.isBefore(LocalDate.now())) { 
	            return "Event Date must be in the future!";
	        }
	    } catch (DateTimeParseException e) {
	        return "Invalid Event Date format!";
	    }
	   
	    if (location.isEmpty()) {
	        return "Event Location cannot be empty!";
	    }
	    
	   
	    if (location.length() < 5) {
	        return "Event Location must be at least 5 characters long!";
	    }
	    
	    
	    if (description.isEmpty()) {
	        return "Event Description cannot be empty!";
	    }
	    
	    
	    if (description.length() > 200) {
	        return "Event Description cannot exceed 200 characters!";
	    }
	    
	    return "valid";
	}
	
}
