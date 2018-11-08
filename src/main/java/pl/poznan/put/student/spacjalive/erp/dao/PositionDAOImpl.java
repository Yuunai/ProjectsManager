package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Position;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Position> getPositions() {
		Session session = sessionFactory.getCurrentSession();
		Query<Position> query = session.createQuery("FROM Position p ORDER BY p.name", Position.class);
		
		return query.getResultList();
	}
	
	@Override
	public void savePosition(Position position) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(position);
	}
	
	@Override
	public void deletePosition(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM Position WHERE id=:positionId");
		query.setParameter("positionId", id);
		query.executeUpdate();
	}
	
	@Override
	public Position getPosition(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Position.class, id);
	}
}
