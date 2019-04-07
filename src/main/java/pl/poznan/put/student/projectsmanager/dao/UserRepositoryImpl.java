package pl.poznan.put.student.projectsmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public UserDetails getUserDetails(int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		UserDetails details = session.get(UserDetails.class, id);
		if(details == null)
			throw new NotFoundException();
		return details;
	}
	
	@Override
	public List<UserDetails> getUsersDetails() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDetails> query = session.createQuery("FROM UserDetails ORDER BY firstName");
		
		return query.getResultList();
	}
	
	@Override
	public List<UserDetails> getUsersDetails(boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDetails> query = session.createQuery("SELECT ud FROM UserDetails ud, User u " +
				"where ud.userId = u.id AND u.enabled=:enabled ORDER BY ud.firstName");
		query.setParameter("enabled", enabled);
		
		return query.getResultList();
	}
	
	@Override
	public void saveUserDetails(UserDetails details) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(details);
	}
	
	@Override
	public User getUser(int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		if(user == null)
			throw new NotFoundException();
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("FROM User WHERE email=:email");
		query.setParameter("email", email);
		List<User> users = query.getResultList();
		
		if(users.isEmpty()) {
			throw new NotFoundException();
		} else {
			return query.getSingleResult();
		}
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
	public Map<Integer, String> getUsersNamesMap(List<Integer> ids) {
		if (ids.isEmpty())
			return new HashMap<>();
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserDetails WHERE userId IN (:ids)");
		query.setParameterList("ids", ids);
		List<UserDetails> details = query.getResultList();
		
		return details.stream()
				.collect(Collectors.toMap(UserDetails::getUserId, e -> e.getLastName() + " " + e.getFirstName()));
	}
	
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
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
	
	@Override
	public void setUserPassword(int userId, String password) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.setPassword(password);
		session.saveOrUpdate(user);
	}
}
