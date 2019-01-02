package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface EventRepository {
	
	List<Event> getEvents();
	
	List<Event> getEvents(boolean archived);
	
	void saveEvent(Event event);
	
	void deleteEvent(int id);
	
	Event getEvent(int id) throws NotFoundException;
	
}
