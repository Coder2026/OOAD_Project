package controller;

import model.User;
import util.SessionManager;
import util.Response;

public class UserController {

	public Response<String> register(String email, String name, String password, String role) {
		
	    String checkInput = checkRegisterInput(email,name,password,role);
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
    
    
    public Response<String> changeProfile(String email, String name, String oldPassword, String newPassword) {
        String checkInput = checkChangeProfileInput(email, name, oldPassword, newPassword);
        if (!checkInput.equals("valid")) {
            return Response.failure(checkInput);
        }

        User user = User.login(email, oldPassword);
        if (user != null) {
            user.setUser_name(name);
            user.setUser_email(email);

        
            boolean updateSuccess = User.changeProfle(user, newPassword);
            if (updateSuccess) {
            	SessionManager.getInstance().setCurrentUser(user);
                return Response.success("Profile updated successfully.",null);
            } else {
                return Response.failure("Failed to update profile. Please try again later.");
            }
        } else {
            return Response.failure("Invalid email or password.");
        }
    }
    
    public String checkRegisterInput(String email, String name, String password, String role) {
        
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
        
        if(role == null) {
        	return "Role must be choosed";
        }
        
        return "valid";
    }
    
    
    public String checkChangeProfileInput(String email,String name, String oldPassword, String newPassword) {
        if (email.isEmpty()) {
            return "Email cannot be empty!";
        }

        
        if (name.isEmpty()) {
            return "Name cannot be empty!";
        }
        
        if (oldPassword.isEmpty()) {
            return "Password cannot be empty!";
        }
        
        if (newPassword.isEmpty()) {
            return "Password cannot be empty!";
        }
        
        if (newPassword.length() < 5) {
            return "Password must be at least 5 characters!";
        }
        
        if(!newPassword.equals(oldPassword)) {
        	return "Old password must equal";
        }
        
        return "valid";
    }
    
    
}
