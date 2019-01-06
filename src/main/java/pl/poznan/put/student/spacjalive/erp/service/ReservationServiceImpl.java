package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.ReservationRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Reservation;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Transactional
@Service("lendingService")
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> getReservations() {
		return reservationRepository.getReservations();
	}
	
	@Override
	public void saveReservation(Reservation reservation) {
		reservationRepository.saveReservation(reservation);
	}
	
	@Override
	public void deleteReservation(int id) {
		reservationRepository.deleteReservation(id);
	}
	
	@Override
	public Reservation getReservation(int id) throws NotFoundException {
		return reservationRepository.getReservation(id);
	}
	
	@Override
	public List<Reservation> getEventReservations(int eventId) {
		return reservationRepository.getEventReservations(eventId);
	}
	
}
