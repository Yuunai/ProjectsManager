package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Reservation;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Reservation> getReservations() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Reservation> query = session.createQuery("FROM Reservation order by dateSince, timeSince",
				Reservation.class);
		return query.getResultList();
	}
	
	@Override
	public void saveReservation(Reservation reservation) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reservation);
	}
	
	@Override
	public void deleteReservation(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Reservation WHERE id=:reservationId");
		query.setParameter("reservationId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public Reservation getReservation(int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Reservation reservation = session.get(Reservation.class, id);
		if(reservation == null)
			throw new NotFoundException();
		return reservation;
	}
	
	@Override
	public List<Reservation> getReservations(String dateSince, String timeSince, String dateTo, String timeTo) {
		Session session = sessionFactory.getCurrentSession();
		Query<Reservation> query = session.getNamedQuery("callSelectReservations")
				.setParameter("dSince", dateSince)
				.setParameter("tSince", timeSince)
				.setParameter("dTo", dateTo)
				.setParameter("tTo", timeTo);
		
		return query.getResultList();
	}
	
	@Override
	public List<Reservation> getEventReservations(int eventId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Reservation> query = session.createQuery("FROM Reservation WHERE event.id=:eventId ORDER BY timeSince");
		query.setParameter("eventId", eventId);
		
		return query.getResultList();
	}
}
