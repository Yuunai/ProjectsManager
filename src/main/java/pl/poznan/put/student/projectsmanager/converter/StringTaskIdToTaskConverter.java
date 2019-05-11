package pl.poznan.put.student.projectsmanager.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.projectsmanager.entity.Task;
import pl.poznan.put.student.projectsmanager.service.TaskService;

public class StringTaskIdToTaskConverter implements Converter<String, Task> {
	
	private final TaskService taskService;
	
	public StringTaskIdToTaskConverter(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@Override
	public Task convert(String s) {
		return taskService.getTask(Integer.valueOf(s));
	}
}
