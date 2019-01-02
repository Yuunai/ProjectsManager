package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface RoleService {
	
	List<Role> getRoles();
	
	Role getRole(int id) throws NotFoundException;
}
