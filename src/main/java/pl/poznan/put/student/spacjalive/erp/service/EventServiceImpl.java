package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.EventRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}
	
	@Override
	public List<Event> getEvents(boolean archived) {
		return eventRepository.getEvents(archived);
	}
	
	@Override
	public void saveEvent(Event event) {
		eventRepository.saveEvent(event);
	}
	
	@Override
	public void deleteEvent(int id) {
		eventRepository.deleteEvent(id);
	}
	
	@Override
	public Event getEvent(int id) throws NotFoundException {
		return eventRepository.getEvent(id);
	}
}
