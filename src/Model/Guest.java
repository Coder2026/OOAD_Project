package model;

import java.util.List;

import repository.GuestRepository;

public class Guest extends User {
	
	private String accepted_invitations;

	public Guest(String user_id, String user_email, String user_name, String user_password, String user_role) {
		super(user_id, user_email, user_name, user_password, user_role);
		
	}
	
	public static List<User> viewOrganizedEventDetails(String eventId){
		return GuestRepository.getGuests(eventId);
	}
	
	public static List<User> getGuestByTransaction(String eventId){
		return GuestRepository.getGuests(eventId);
	}
	
	public static List<User> getGuests(){
		return GuestRepository.getGuests();
	}

}
