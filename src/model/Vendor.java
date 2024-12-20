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
	
	

	public String getAccepted_invitations() {
		return accepted_invitations;
	}



	public void setAccepted_invitations(String accepted_invitations) {
		this.accepted_invitations = accepted_invitations;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	public String getProduct_desc() {
		return product_desc;
	}



	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}



	public Vendor(String user_id, String user_email, String user_name, String user_password, String user_role) {
		super(user_id, user_email, user_name, user_password, user_role);
		
	}
	
	public static List<User> getVendorsByTransaction(String eventId){
		return VendorRepository.getVendors(eventId);
	}
	
	public static List<User> getVendors(){
		for (User user : VendorRepository.getVendors()) {
			System.out.println(user.getUser_name());
		}
		return VendorRepository.getVendors();
	}
	
	public static Vendor manageVendor(String user_id) {
		
		return VendorRepository.getProduct(user_id);
	}
	
	public static boolean manageVendor(String vendorId, String name, String description, String product) {
	    return VendorRepository.upsertVendor(vendorId, name, description, product);
	}


	
}
