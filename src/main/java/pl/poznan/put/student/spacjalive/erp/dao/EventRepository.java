package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Event;

import java.util.List;

public interface EventRepository {
	
	public List<Event> getEvents();
	
	public List<Event> getEvents(int archived);
	
	public void saveEvent(Event event);
	
	public void deleteEvent(int id);
	
	public Event getEvent(int id);
	
}
