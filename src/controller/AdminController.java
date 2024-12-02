package controller;

import java.util.List;

import model.User;
import model.Admin;
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

	        return Response.success(message, null);
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	        
	        return Response.failure("An error occurred while trying to delete the user.");
	    }
	}
}
