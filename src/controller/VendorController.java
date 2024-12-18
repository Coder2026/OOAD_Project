package controller;

import java.util.List;

import model.Event;
import model.Invitation;
import model.User;
import model.Vendor;
import util.Response;

public class VendorController {

	public Response<Vendor> getVendor(String id){
		try {
			Vendor message = Vendor.manageVendor(id);
			
			return Response.success("Vendor data product retrived", message);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.failure("An error occurred during manage vendor. Please try again");
		}
	}
//	
//	
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
    
    public Response<Vendor> manageVendor(String description, String productName, String vendorId) {
        String checkInput = checkManageVendorInput(description, productName);
        if (!checkInput.equals("valid")) {
            return Response.failure(checkInput);
        }

        boolean success = Vendor.manageVendor(vendorId, productName, description, "");
        if (success) {
            Vendor updatedVendor = new Vendor(vendorId,"" ,"","","","",productName, description);
            return Response.success("Manage Vendor success", updatedVendor);
        } else {
            return Response.failure("Failed to manage vendor");
        }
    }



}
