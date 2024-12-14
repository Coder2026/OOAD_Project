package model;

import java.util.List;

import repository.EventRepository;
import util.Response;

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
		return EventRepository.getEventsById(userId);
	}
	
	public static List<Event> viewAllEvents(){
		return EventRepository.getAllEvents();
	}
	
	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getOrganizer_id() {
		return organizer_id;
	}

	public void setOrganizer_id(String organizer_id) {
		this.organizer_id = organizer_id;
	}

	public static String deleteEvent(String eventId) {
		return EventRepository.deleteEvent(eventId);
	}
	
	public static String editEventName(String eventId,String eventName) {
		return EventRepository.updateEventNameById(eventId, eventName);
	}
}
