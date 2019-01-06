package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Reservation;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface ReservationService {
	
	List<Reservation> getReservations();
	
	List<Reservation> getActualReservations();
	
	void saveReservation(Reservation reservation);
	
	void deleteReservation(int id);
	
	Reservation getReservation(int id) throws NotFoundException;
	
	List<Reservation> getEventReservations(int eventId);
	
}
