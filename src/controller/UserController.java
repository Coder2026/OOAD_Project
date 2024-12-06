package controller;

import model.User;
import util.SessionManager;
import util.Response;

public class UserController {

	public Response<String> register(String email, String name, String password, String role) {
		
	    String checkInput = checkRegisterInput(email,name,password);
	    if (!checkInput.equals("valid")) {
	        return Response.failure(checkInput);
	    }

	    try {
	    	String registerMessege = User.register(email, name, password, role);
	        if (registerMessege.equals("success")) {
	            return Response.success("Registration was successful", null);
	        } else {
	        	
	            return Response.failure(registerMessege);
	        }
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        return Response.failure("An error occurred during registration. Please try again");
	    }
	}
    
    public Response<String> login(String email, String password) {
    	
        	
    	 try {
    		 
             User user = User.login(email, password);

             if (user != null) {
                 SessionManager.getInstance().setCurrentUser(user);
                 return Response.success("Login successful!",null);
             } else {
                 return Response.failure("Invalid email or password.");
             }
         } catch (Exception e) {
        	 
             e.printStackTrace(); 
             return Response.failure("An error occurred. Please try again later.");
         }
    }
    
    public String checkRegisterInput(String email, String name, String password) {
        
        if (email.isEmpty()) {
            return "Email cannot be empty!";
        }
        
        if (name.isEmpty()) {
            return "Name cannot be empty!";
        }
        
        if (password.isEmpty()) {
            return "Password cannot be empty!";
        }
        
        if (password.length() < 5) {
            return "Password must be at least 5 characters!";
        }
        
        return "valid";
    }
}
