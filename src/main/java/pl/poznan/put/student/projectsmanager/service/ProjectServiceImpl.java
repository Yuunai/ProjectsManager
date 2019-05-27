package pl.poznan.put.student.projectsmanager.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.dao.ProjectRepository;
import pl.poznan.put.student.projectsmanager.dao.UserRepository;
import pl.poznan.put.student.projectsmanager.entity.Project;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Project getProject(int id, boolean initializeTasks, boolean initializeUsers) {
		Project result = projectRepository.getProject(id);
		
		if(initializeTasks)
			Hibernate.initialize(result.getTasks());
		
		if(initializeUsers)
			Hibernate.initialize(result.getUsers());
		
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
		if(!Hibernate.isInitialized(project.getUsers()))
			Hibernate.initialize(project.getUsers());
		if(project.getUsers() == null || project.getUsers().isEmpty())
			project.setUsers(userRepository.getAdminUsers());
		projectRepository.saveProject(project);
		
	}
	
	@Override
	public void deleteProject(int id) {
		projectRepository.deleteProject(id);
	}
}
