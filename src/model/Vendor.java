package model;

import java.util.List;

import repository.UserRepository;
import repository.VendorRepository;

public class Vendor extends User{
	
	private String accepted_invitations;
	private String product_name;
	private String product_desc;
	
	

	public Vendor(String user_id, String user_email, String user_name, String user_password, String user_role,
			String accepted_invitations, String product_name, String product_desc) {
		super(user_id, user_email, user_name, user_password, user_role);
		this.accepted_invitations = accepted_invitations;
		this.product_name = product_name;
		this.product_desc = product_desc;
	}

	public Vendor(String user_id, String user_email, String user_name, String user_password, String user_role) {
		super(user_id, user_email, user_name, user_password, user_role);
		
	}
	
	public static List<User> getVendorsByTransaction(String eventId){
		return UserRepository.getVendors(eventId);
	}
	
	public static List<User> getVendors(){
		return UserRepository.getVendors();
	}
	
	public static Vendor getProduct(String user_id) {
		
		return VendorRepository.getProduct(user_id);
	}
	
	
}
