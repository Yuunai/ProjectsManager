package pl.poznan.put.student.projectsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;
import pl.poznan.put.student.projectsmanager.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@RequestMapping("/task")
@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/new")
	public String newTask(Model model,
	                      @RequestParam("pid") int projectId,
	                      @SessionAttribute("userId") int userId) throws NotFoundException {
		if(!taskService.checkUserRights(projectId, userId))
			return "redirect:/project/list";
		
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
	public String saveTask(Model model,
	                       @ModelAttribute("task") Task task,
	                       @SessionAttribute("userId") int userId) throws NotFoundException {
		if(!taskService.checkUserRights(task.getProject().getId(), userId))
			return taskDetails(model, task.getId());
		
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
	                          @ModelAttribute("comment") Comment comment,
	                          @SessionAttribute("userId") int userId) throws NotFoundException {
		User user = userService.getUser(userId);
		comment.setUser(user);
		comment.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss")));
		taskService.saveComment(comment);
		
		return taskDetails(model, comment.getTask().getId());
	}
	
	@PostMapping("/user")
	public String addUser(Model model,
	                      @RequestParam("taskId") int taskId,
	                      @SessionAttribute("userId") int userId) throws NotFoundException {
		User user = userService.getUser(userId);
		Task task = taskService.getTask(taskId);
		if(!taskService.checkUserRights(task.getProject().getId(), userId))
			return "redirect:/project/list";
		
		if(task.getUsers().stream().anyMatch(u -> u.getId() == user.getId())) {
			task.getUsers().removeIf(tempUser -> tempUser.getId() == user.getId());
		} else {
			task.getUsers().add(user);
		}
		taskService.saveTask(task);
		
		return taskDetails(model, task.getId());
	}
	
}
