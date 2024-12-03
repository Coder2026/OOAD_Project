package model;

import java.util.List;

import repository.EventRepository;

public class Event {
	private String event_id;
	private String event_name;
	private String event_date;
	private String event_location;
	private String event_description;
	private String organizer_id;
	
	
	
	public Event(String event_id, String event_name, String event_date, String event_location, String event_description,
			String organizer_id) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_date = event_date;
		this.event_location = event_location;
		this.event_description = event_description;
		this.organizer_id = organizer_id;
	}

	public static String createEvent(String eventName, String date, String locataion, String description, String organizerId) {
		return EventRepository.createEvent(eventName, date, locataion, description, organizerId);
	}
	
	public static List<Event> viewOrganizedEvents(String userId){
		return EventRepository.findEventsById(userId);
	}
}
