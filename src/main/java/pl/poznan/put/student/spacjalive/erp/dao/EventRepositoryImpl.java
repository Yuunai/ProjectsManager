package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Event;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Event> getEvents() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Event> query = session.createQuery("FROM Event e ORDER BY e.date ASC ", Event.class);
		List<Event> events = query.getResultList();
		
		return events;
	}
	
	@Override
	public List<Event> getEvents(int archived) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Event> query = session.createQuery("FROM Event WHERE archived=:archived", Event.class);
		query.setParameter("archived", archived);
		
		return query.getResultList();
	}
	
	@Override
	public void saveEvent(Event event) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(event);
	}
	
	@Override
	public void deleteEvent(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Event WHERE id=:eventId");
		query.setParameter("eventId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public Event getEvent(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Event.class, id);
	}
}
