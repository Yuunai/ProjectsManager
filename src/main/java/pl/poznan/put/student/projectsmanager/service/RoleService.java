package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

public interface RoleService {
	
	List<Role> getRoles();
	
	Role getRole(int id) throws NotFoundException;
}
