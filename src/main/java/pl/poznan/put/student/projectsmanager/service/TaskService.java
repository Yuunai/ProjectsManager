package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.entity.Task;

import java.util.List;

public interface TaskService {

	Task getTask(int id);
	
	List<Task> getTasks();
	
	void saveTask(Task task);
	
	void deleteTask(int id);
}
