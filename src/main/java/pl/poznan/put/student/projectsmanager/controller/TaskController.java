package pl.poznan.put.student.projectsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.projectsmanager.entity.Comment;
import pl.poznan.put.student.projectsmanager.entity.Task;
import pl.poznan.put.student.projectsmanager.service.TaskService;

@RequestMapping("/task")
@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/details")
	public String taskDetails(Model model, @RequestParam("tid") int taskId) {
		Task task = taskService.getTask(taskId);
		model.addAttribute("task", task);
		model.addAttribute("comment", new Comment());
		
		return "task-details";
	}
	
	@PostMapping("/save")
	public String saveTask(Model model, @RequestParam("task") Task task) {
		taskService.saveTask(task);
		
		return taskDetails(model, task.getId());
	}
	
	@GetMapping("/delete")
	public String deleteTask(Model model, @RequestParam("tid") int taskId) {
		Task task = taskService.getTask(taskId);
		taskService.deleteTask(taskId);
		
		return "redirect:/project/details?pid=" + task.getProject().getId();
	}
	
	@PostMapping("/comment")
	public String saveComment(Model model,
	                          @RequestParam("tid") int taskId,
	                          @ModelAttribute("comment") String comment) {
		Task task = taskService.getTask(taskId);
		task.addComment(new Comment(task, comment));
		taskService.saveTask(task);
		
		return taskDetails(model, taskId);
	}
	
}
