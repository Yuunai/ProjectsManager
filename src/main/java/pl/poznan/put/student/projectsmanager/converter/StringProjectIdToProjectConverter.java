package pl.poznan.put.student.projectsmanager.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.projectsmanager.entity.Project;
import pl.poznan.put.student.projectsmanager.service.ProjectService;

public class StringProjectIdToProjectConverter implements Converter<String, Project> {
	
	private final ProjectService projectService;
	
	public StringProjectIdToProjectConverter(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Override
	public Project convert(String s) {
		return projectService.getProject(Integer.valueOf(s), false, false);
	}
}
