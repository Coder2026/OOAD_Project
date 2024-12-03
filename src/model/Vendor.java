package model;

import java.util.List;

import repository.UserRepository;

public class Vendor extends User{
	
	private String accepted_invitations;

	public Vendor(String user_id, String user_email, String user_name, String user_password, String user_role) {
		super(user_id, user_email, user_name, user_password, user_role);
		
	}
	
	public static List<User> getVendorsByTransaction(String eventId){
		return UserRepository.getVendors(eventId);
	}
}
