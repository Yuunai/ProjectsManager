package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.RoleDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}
	
	@Override
	public void saveRole(Role role) {
		roleDAO.saveRole(role);
	}
	
	@Override
	public void deleteRole(int id) {
		roleDAO.deleteRole(id);
	}
	
	@Override
	public Role getRole(int id) {
		return roleDAO.getRole(id);
	}
}
