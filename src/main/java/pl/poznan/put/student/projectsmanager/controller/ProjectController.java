package pl.poznan.put.student.projectsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.projectsmanager.entity.Project;
import pl.poznan.put.student.projectsmanager.service.ProjectService;

import java.util.List;

@RequestMapping("/project")
@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
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
	
}
