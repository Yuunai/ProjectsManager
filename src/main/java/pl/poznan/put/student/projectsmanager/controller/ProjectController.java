package pl.poznan.put.student.projectsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.projectsmanager.entity.Project;
import pl.poznan.put.student.projectsmanager.entity.User;
import pl.poznan.put.student.projectsmanager.service.ProjectService;
import pl.poznan.put.student.projectsmanager.service.UserService;

import java.util.List;

@RequestMapping("/project")
@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listProjects(Model model) {
		List<Project> projects = projectService.getProjects(true);
		model.addAttribute("projects", projects);
		
		return "list-projects";
	}
	
	@GetMapping("/details")
	public String projectDetails(Model model, @RequestParam("pid") int projectId) {
		Project project = projectService.getProject(projectId, true, true);
		model.addAttribute("project", project);
		
		return "project-details";
	}
	
	@GetMapping("/new")
	public String newProject(Model model) {
		model.addAttribute("project", new Project());
		
		return "new-project";
	}
	
	@PostMapping("/save")
	public String saveProject(Model model, @ModelAttribute(name = "project") Project project) {
		projectService.saveProject(project);
		
		return listProjects(model);
	}
	
	@GetMapping("/delete")
	public String deleteProject(Model model, @RequestParam(name = "id") int id) {
		projectService.deleteProject(id);
		
		return listProjects(model);
	}
	
	
	@GetMapping("/projectRights")
	public String getProjectRightsPage(Model model,
	                                   @RequestParam("projectId") int projectId) {
		Project project = projectService.getProject(projectId, false, true);
		List<User> users = userService.getUsers(true);
		model.addAttribute("users", users);
		model.addAttribute("project", project);
		
		
		return "project-rights";
	}
	
	@PostMapping("/projectRights")
	public String saveProjectRights(Model model,
	                                @ModelAttribute("project") Project project) {
		projectService.saveProject(project);
		
		return getProjectRightsPage(model, project.getId());
	}
}
