package pl.poznan.put.student.projectsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.dao.TaskRepository;
import pl.poznan.put.student.projectsmanager.entity.Task;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public Task getTask(int id) {
		return taskRepository.getTask(id);
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
}
