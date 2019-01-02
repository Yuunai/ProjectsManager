package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface RoleRepository {
	
	List<Role> getRoles();
	
	Role getRole(int id) throws NotFoundException;
	
}
