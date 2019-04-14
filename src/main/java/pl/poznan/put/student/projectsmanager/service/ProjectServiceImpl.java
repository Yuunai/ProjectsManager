package pl.poznan.put.student.projectsmanager.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.dao.ProjectRepository;
import pl.poznan.put.student.projectsmanager.entity.Project;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project getProject(int id, boolean initializeTasks) {
		Project result = projectRepository.getProject(id);
		if(initializeTasks)
			Hibernate.initialize(result.getTasks());
		
		return result;
	}
	
	@Override
	public List<Project> getProjects(boolean initializeTasks) {
		List<Project> results = projectRepository.getProjects();
		if(initializeTasks)
			results.stream().forEach(e -> Hibernate.initialize(e.getTasks()));
		
		return results;
	}
	
	@Override
	public void saveProject(Project project) {
		projectRepository.saveProject(project);
	}
	
	@Override
	public void deleteProject(int id) {
		projectRepository.deleteProject(id);
	}
}
