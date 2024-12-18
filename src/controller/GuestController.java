package controller;

import java.util.List;

import model.Event;
import model.Invitation;
import util.Response;

public class GuestController {

    public Response<String> acceptInvitation(String eventId, String userId) {
        try {
            String message = Invitation.acceptInvitation(eventId, userId);

            if (message.equalsIgnoreCase("success")) {
                return Response.success(
                    "Invitation for event with ID " + eventId + " was successfully accepted by user ID: " + userId + ".", 
                    null
                );
            }

            return Response.failure(message);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure("An error occurred while accepting the invitation: " + e.getMessage());
        }
    }
    
    public Response<List<Event>> viewAcceptedInvitations(String email) {
        try {
            List<Event> events = Invitation.getAcceptedInvitations(email);

            if (!events.isEmpty()) {
                return Response.success("Accepted events retrieved successfully.", events);
            } else {
                return Response.failure("No accepted events found for email: " + email + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure("An error occurred while retrieving accepted events: " + e.getMessage());
        }
    }

    
    public Response<List<Event>> viewInvitations(String email) {
        try {
            List<Event> events = Invitation.getInvitations(email);

            if (!events.isEmpty()) {
                return Response.success("Accepted events retrieved successfully.", events);
            } else {
                return Response.failure("No accepted events found for email: " + email + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure("An error occurred while retrieving accepted events: " + e.getMessage());
        }
    }
}
