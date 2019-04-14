package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.entity.Project;

import java.util.List;

public interface ProjectService {
	
	Project getProject(int id, boolean initializeTasks);
	
	List<Project> getProjects(boolean initializeTasks);
	
	void saveProject(Project project);
	
	void deleteProject(int id);
	
}
