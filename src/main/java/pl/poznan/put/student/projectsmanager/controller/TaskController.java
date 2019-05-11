package pl.poznan.put.student.projectsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.service.ProjectService;
import pl.poznan.put.student.projectsmanager.service.TaskService;

@RequestMapping("/task")
@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/new")
	public String newTask(Model model, @RequestParam("pid") int projectId) {
		Project project = projectService.getProject(projectId, false, false);
		
		Task task = new Task();
		task.setProject(project);
		model.addAttribute("task", task);
		
		return "new-task";
	}
	
	@GetMapping("/details")
	public String taskDetails(Model model, @RequestParam("tid") int taskId) {
		Task task = taskService.getTask(taskId);
		model.addAttribute("task", task);
		
		Comment comment = new Comment();
		comment.setTask(task);
		model.addAttribute("comment", comment);
		
		return "task-details";
	}
	
	@PostMapping("/save")
	public String saveTask(Model model, @ModelAttribute("task") Task task) {
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
	public String saveComment(Model model, @ModelAttribute("comment") Comment comment) {
		taskService.saveComment(comment);
		
		return taskDetails(model, comment.getTask().getId());
	}
	
}
