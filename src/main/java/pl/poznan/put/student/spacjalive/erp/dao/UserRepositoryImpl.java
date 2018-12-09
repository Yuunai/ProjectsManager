package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.*;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public UserDetails getUserDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(UserDetails.class, id);
	}
	
	@Override
	public List<UserDetails> getUsersDetails() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserDetails ORDER BY firstName");
		
		return query.getResultList();
	}
	
	@Override
	public List<UserDetails> getUsersDetails(boolean active) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserDetails WHERE active=:active ORDER BY firstName");
		query.setParameter("active", active);
		
		return query.getResultList();
	}
	
	@Override
	public void saveUserDetails(UserDetails details) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(details);
	}
	
	@Override
	public User getUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	
	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("FROM User WHERE email=:email");
		query.setParameter("email", email);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User");
		
		return query.getResultList();
	}
	
	@Override
	public List<User> getUsers(boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE enabled=:enabled");
		query.setParameter("enabled", enabled);
		
		return query.getResultList();
	}
	
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	
	@Override
	public void setUserEnabled(int id, boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE User SET enabled=:enabled");
		query.setParameter("enabled", enabled);
		query.executeUpdate();
	}
	
	@Override
	public List<AdministrativeRole> getAdmRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AdministrativeRole ORDER BY id");
		
		return query.getResultList();
	}
	
	@Override
	public AdministrativeRole getAdmRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(AdministrativeRole.class, id);
	}
}
