package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
	
	List<Reservation> getReservations();
	
	void saveReservation(Reservation reservation);
	
	void deleteReservation(int id);
	
	Reservation getReservation(int id);
	
	List<Reservation> getReservations(String dateSince, String timeSince, String dateTo, String timeTo);
	
}
