package pl.poznan.put.student.projectsmanager.dao;

import pl.poznan.put.student.projectsmanager.entity.Project;

import java.util.List;

public interface ProjectRepository {
	
	Project getProject(int id);
	
	List<Project> getProjects();
	
	void saveProject(Project project);
	
	void deleteProject(int id);
	
}
