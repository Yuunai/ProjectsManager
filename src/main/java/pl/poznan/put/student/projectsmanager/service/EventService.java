package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

public interface EventService {
	
	List<Event> getEvents();
	
	List<Event> getEvents(Boolean archived, Boolean published);
	
	void saveEvent(Event event);
	
	void deleteEvent(int id);
	
	Event getEvent(int id) throws NotFoundException;
	
}
