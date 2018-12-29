package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Event;

import java.util.List;

public interface EventService {
	
	List<Event> getEvents();
	
	List<Event> getEvents(boolean archived);
	
	void saveEvent(Event event);
	
	void deleteEvent(int id);
	
	Event getEvent(int id);
	
}
