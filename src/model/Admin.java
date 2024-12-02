package model;

import java.util.List;

import repository.UserRepository;

public class Admin extends User {

	public Admin(String user_id, String user_email, String user_name, String user_password, String user_role) {
		super(user_id, user_email, user_name, user_password, user_role);
	}

	public Admin(String user_email, String user_name, String user_password, String user_role) {
		super(user_email, user_name, user_password, user_role);
	}
	
    public static List<User> getAllUsers() {
    	return UserRepository.findAll();
	}
    
    public static String deleteUser(String userId) {
    	return UserRepository.deleteUser(userId);
    }
}
    
