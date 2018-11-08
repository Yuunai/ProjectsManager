package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

public interface RoleDAO {
	
	public List<Role> getRoles();
	
	public void saveRole(Role role);
	
	public void deleteRole(int id);
	
	public Role getRole(int id);
	
}
