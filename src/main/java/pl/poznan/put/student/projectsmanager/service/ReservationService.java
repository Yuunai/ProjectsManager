package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

public interface ReservationService {
	
	List<Reservation> getReservations();
	
	List<Reservation> getActualReservations();
	
	void saveReservation(Reservation reservation);
	
	void deleteReservation(int id);
	
	Reservation getReservation(int id) throws NotFoundException;
	
	List<Reservation> getEventReservations(int eventId);
	
}
