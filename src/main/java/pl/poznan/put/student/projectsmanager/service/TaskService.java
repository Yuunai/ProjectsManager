package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.entity.Comment;
import pl.poznan.put.student.projectsmanager.entity.Task;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

public interface TaskService {

	Task getTask(int id);
	
	List<Task> getTasks();
	
	void saveTask(Task task);
	
	void deleteTask(int id);
	
	void saveComment(Comment comment);
	
	void deleteComment(int id);
	
	boolean checkUserRights(int projectId, int userId) throws NotFoundException;
	
}
