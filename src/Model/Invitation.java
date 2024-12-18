package model;

import java.util.List;

import repository.InvitationRepository;

public class Invitation {
	private String invitation_id;
	private String event_id;
	private String user_id;
	private String invitation_status;
	private String invitation_role;
	
	
	public Invitation(String invitation_id, String event_id, String user_id, String invitation_status,
			String invitation_role) {
		super();
		this.invitation_id = invitation_id;
		this.event_id = event_id;
		this.user_id = user_id;
		this.invitation_status = invitation_status;
		this.invitation_role = invitation_role;
	}
	
	
	public static String sendInvitation(String eventId, String userId, String role) {
		return InvitationRepository.createInvitation(eventId, userId, role);
	}
	
	public static String acceptInvitation(String eventId, String userId) {
		return InvitationRepository.acceptInvitation(eventId, userId);
	}
	
    public static List<Event> getAcceptedInvitations(String email) {
        return InvitationRepository.getAcceptedInvitations(email);
    }
	
    public static List<Event> getInvitations(String email) {
        return InvitationRepository.getInvitations(email);
    }
	
}
