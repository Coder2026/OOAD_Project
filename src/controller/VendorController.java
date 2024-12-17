package controller;

import java.util.List;

import model.Invitation;
import model.User;
import model.Vendor;
import util.Response;

public class VendorController {

	public Response<Vendor> manageVendor(String description, String product_name, String id){
	    String checkInput = checkManageVendorInput(description, product_name);
	    if (!checkInput.equals("valid")) {
	        return Response.failure(checkInput);
	    }
		try {
			Vendor message = Vendor.getProduct(id);
			return Response.success("Vendor data product retrived", message);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.failure("An error occurred during manage vendor. Please try again");
		}
	}
	
	
	public String checkManageVendorInput(String description, String product_name) {
		if(description.isEmpty()) {
			return "Description cannot be empty";
		}
		
		if(product_name.isEmpty()) {
			return "Product name cannot be empty";
		}
		
        return "valid";
	}
	
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
