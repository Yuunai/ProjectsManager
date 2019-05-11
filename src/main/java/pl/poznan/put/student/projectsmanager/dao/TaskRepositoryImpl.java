package pl.poznan.put.student.projectsmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.projectsmanager.entity.Comment;
import pl.poznan.put.student.projectsmanager.entity.Task;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Task getTask(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Task.class, id);
	}
	
	@Override
	public List<Task> getTasks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Task> query = session.createQuery("FROM Task order by priority");
		return query.getResultList();
	}
	
	@Override
	public void saveTask(Task task) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(task);
	}
	
	@Override
	public void deleteTask(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM Task WHERE id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public void deleteComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM Comment WHERE id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public void saveComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);
	}
}
