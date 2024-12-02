package model;
import java.util.List;

import repository.UserRepository;

public class User {
	private String user_id;
	private String user_email;
	private String user_name;
	private String user_password;
	private String user_role;
		
	public User(String user_id,String user_email, String user_name, String user_password, String user_role) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_role = user_role;
	}
	
	 public static String register( String email, String name, String password, String role) {
	        return UserRepository.createUser(email,name,password,role);
	 }
	 
	 public static User login(String email, String password) {
		 return UserRepository.getUserIdByEmailAndPassword(email, password);
	 }
	 
	 public static List<User> getAllUser(){
		 return UserRepository.findAll();
	 }	
}
