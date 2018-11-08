package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;

import java.util.List;

@Repository
public class ParticipationDAOImpl implements ParticipationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Participation> getParticipationsByEventId(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Participation> query = session.createQuery("FROM Participation WHERE event.id=:eventId");
		query.setParameter("eventId", id);
		
		return query.getResultList();
	}
	
	@Override
	public List<Participation> getParticipationsByEmployeeId(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Participation> query = session.createQuery("FROM Participation WHERE employee.id=:employeeId");
		query.setParameter("employeeId", id);
		
		return query.getResultList();
	}
	
	@Override
	public List<Participation> getParticipationsByRoleId(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Participation> query = session.createQuery("FROM Participation WHERE role.id=:roleId");
		query.setParameter("roleId", id);
		
		return query.getResultList();
	}
	
	@Override
	public void deleteParticipation(int eventId, int roleId, int employeeId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Participation WHERE event.id=:eventId AND role.id=:roleId AND employee.id=:employeeId");
		query.setParameter("eventId", eventId);
		query.setParameter("roleId", roleId);
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();
	}
	
	@Override
	public void addParticipation(Participation participation) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(participation);
	}
}
