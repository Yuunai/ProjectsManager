package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.controller.ReservationController;
import pl.poznan.put.student.spacjalive.erp.dao.ReservationRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Reservation;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Reservation> getActualReservations() {
		LocalDateTime actualBorder = LocalDateTime.now().minusDays(2);
		return getReservations().stream()
				.filter(e -> actualBorder.isBefore(LocalDateTime.parse(e.getDateTo() + " " + e.getTimeTo(),
						ReservationController.dateTimeFormatter))).collect(Collectors.toList());
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
