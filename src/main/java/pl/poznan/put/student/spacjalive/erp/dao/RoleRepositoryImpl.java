package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery("FROM Role", Role.class);
		
		return query.getResultList();
	}
	
	@Override
	public void saveRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
	}
	
	@Override
	public void deleteRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Role WHERE id=:roleId");
		query.setParameter("roleId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public Role getRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Role.class, id);
	}
}