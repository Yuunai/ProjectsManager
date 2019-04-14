package pl.poznan.put.student.projectsmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.projectsmanager.entity.Project;

import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Project getProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Project.class, id);
	}
	
	@Override
	public List<Project> getProjects() {
		Session session = sessionFactory.getCurrentSession();
		Query<Project> query = session.createQuery("FROM Project ");
		return query.getResultList();
	}
	
	@Override
	public void saveProject(Project project) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(project);
	}
	
	@Override
	public void deleteProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM Project where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
