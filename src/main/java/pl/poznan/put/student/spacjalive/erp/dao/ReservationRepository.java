package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Reservation;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface ReservationRepository {
	
	List<Reservation> getReservations();
	
	void saveReservation(Reservation reservation);
	
	void deleteReservation(int id);
	
	Reservation getReservation(int id) throws NotFoundException;
	
	List<Reservation> getReservations(String dateSince, String timeSince, String dateTo, String timeTo);
	
}
