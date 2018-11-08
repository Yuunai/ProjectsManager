package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Employee> query = session.createQuery("FROM Employee e ORDER BY e.firstName, e.lastName");
		return query.getResultList();
	}
	
	@Override
	public List<Employee> getEmployees(boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Employee> query =
				session.createQuery("FROM Employee e WHERE e.enabled=:enabled ORDER BY e.firstName, e.lastName");
		if (enabled) {
			query.setParameter("enabled", 1);
		} else {
			query.setParameter("enabled", 0);
		}
		
		return query.getResultList();
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(employee);
	}
	
	@Override
	public void deleteEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Employee WHERE id=:employeeId");
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public Employee getEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Employee.class, id);
	}
}
