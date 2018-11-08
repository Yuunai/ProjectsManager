package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.EventDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Event;

import java.util.List;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventDAO eventDAO;
	
	@Override
	public List<Event> getEvents() {
		return eventDAO.getEvents();
	}
	
	@Override
	public List<Event> getEvents(int archived) {
		return eventDAO.getEvents(archived);
	}
	
	@Override
	public void saveEvent(Event event) {
		eventDAO.saveEvent(event);
	}
	
	@Override
	public void deleteEvent(int id) {
		eventDAO.deleteEvent(id);
	}
	
	@Override
	public Event getEvent(int id) {
		return eventDAO.getEvent(id);
	}
}
