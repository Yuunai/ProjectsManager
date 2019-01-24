package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Override
	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}
	
	@Override
	public List<Event> getEvents(Boolean archived, Boolean published) {
		return eventRepository.getEvents(archived, published);
	}
	
	@Override
	public void saveEvent(Event event) {
		eventRepository.saveEvent(event);
	}
	
	@Override
	public void deleteEvent(int eventId) {
		List<Reservation> reservations = reservationRepository.getEventReservations(eventId);
		for(Reservation reservation : reservations) {
			reservationRepository.deleteReservation(reservation.getId());
		}
		participationRepository.deleteEventParticipations(eventId);
		eventRepository.deleteEvent(eventId);
	}
	
	@Override
	public Event getEvent(int id) throws NotFoundException {
		return eventRepository.getEvent(id);
	}
}
