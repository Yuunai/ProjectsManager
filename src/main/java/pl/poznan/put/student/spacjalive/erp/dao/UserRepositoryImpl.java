package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.UserNotFoundException;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("FROM User e ORDER BY e.firstName, e.lastName");
		return query.getResultList();
	}
	
	@Override
	public List<User> getUsers(boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query =
				session.createQuery("FROM User e WHERE e.enabled=:enabled ORDER BY e.firstName, e.lastName");
		if (enabled) {
			query.setParameter("enabled", 1);
		} else {
			query.setParameter("enabled", 0);
		}
		
		return query.getResultList();
	}
	
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	
	@Override
	public void updateUserData(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("UPDATE User SET firstName=:firstName, lastName=:lastName, email=:email, " +
				"phoneNumber=:phoneNumber, studentIndex=:studentIndex, car=:car WHERE id=:userId");
		query.setParameter("userId", user.getId());
		query.setParameter("firstName", user.getFirstName());
		query.setParameter("lastName", user.getLastName());
		query.setParameter("email", user.getEmail());
		query.setParameter("phoneNumber", user.getPhoneNumber());
		query.setParameter("studentIndex", user.getStudentIndex());
		query.setParameter("car", user.isCar());
		
		query.executeUpdate();
	}
	
	@Override
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM User WHERE id=:employeeId");
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public User getUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	
	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM User where email=:email", User.class);
		query.setParameter("email", email);
		List<User> result = query.getResultList();
		if(result.isEmpty())
			return null;
		
		
		return result.get(0);
	}
}
