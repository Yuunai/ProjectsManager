package pl.poznan.put.student.projectsmanager.dao;

import pl.poznan.put.student.projectsmanager.entity.Comment;
import pl.poznan.put.student.projectsmanager.entity.Task;

import java.util.List;

public interface TaskRepository {
	
	Task getTask(int id);
	
	List<Task> getTasks();
	
	void saveTask(Task task);
	
	void deleteTask(int id);
	
	void deleteComment(int id);
	
	void saveComment(Comment comment);
	
}
