package controller;

import model.Invitation;
import util.Response;

public class InvitationController {
    public Response<String> sendInvitation(String eventId, String userId, String role) {
        try {
          
            String message = Invitation.sendInvitation(eventId, userId,role);
            
           
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
