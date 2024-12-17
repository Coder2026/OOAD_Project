package controller;

import java.util.List;

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
    
    public Response<List<Invitation>> viewAcceptedInvitations(String userId) {
        try {
            List<Invitation> invitations = Invitation.getAcceptedInvitations(userId);

            if (!invitations.isEmpty()) {
                return Response.success("Accepted invitations retrieved successfully.", invitations);
            } else {
                return Response.failure("No accepted invitations found for user ID: " + userId + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure("An error occurred while retrieving accepted invitations: " + e.getMessage());
        }
    }
}
