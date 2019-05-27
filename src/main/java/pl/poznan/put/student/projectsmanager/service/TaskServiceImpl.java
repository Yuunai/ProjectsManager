package pl.poznan.put.student.projectsmanager.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.dao.*;
import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Task getTask(int id) {
		Task task = taskRepository.getTask(id);
		Hibernate.initialize(task.getComments());
		Hibernate.initialize(task.getUsers());
		return task;
	}
	
	@Override
	public List<Task> getTasks() {
		return taskRepository.getTasks();
	}
	
	@Override
	public void saveTask(Task task) {
		taskRepository.saveTask(task);
	}
	
	@Override
	public void deleteTask(int id) {
		taskRepository.deleteTask(id);
	}
	
	@Override
	public void saveComment(Comment comment) {
		taskRepository.saveComment(comment);
	}
	
	@Override
	public void deleteComment(int id) {
		taskRepository.deleteComment(id);
	}
	
	@Override
	public boolean checkUserRights(int projectId, int userId) throws NotFoundException {
		Project project = projectRepository.getProject(projectId);
		
		return project.getUsers().stream().anyMatch(u -> u.getId() == userId);
	}
}
