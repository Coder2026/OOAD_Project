package model;
import java.util.List;

import repository.EventRepository;
import repository.UserRepository;

public class User {
	private String user_id;
	private String user_email;
	private String user_name;
	private String user_password;
	private String user_role;
	
	
	public User(String user_email, String user_name, String user_password, String user_role) {
		
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_role = user_role;
	}
	
		
		
	public User(String user_id,String user_email, String user_name, String user_password, String user_role) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_role = user_role;
	}



	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_password() {
		return user_password;
	}


	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public String getUser_role() {
		return user_role;
	}


	


	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	
	

	public static String register( String email, String name, String password, String role) {
	        return UserRepository.createUser(email,name,password,role);
	 }
	 
	 public static User login(String email, String password) {
		 return UserRepository.getUserIdByEmailAndPassword(email, password);
	 }
	 
	 public static List<User> getAllUser(){
		 return UserRepository.getAllUser();
	 }
	 
	 public static String changeProfle(User user, String newPassword) {
		 return UserRepository.updateUser(user, newPassword);
 	 }

	 public static String deleteUser(String userId) {
		return UserRepository.deleteUser(userId);
	}
}
