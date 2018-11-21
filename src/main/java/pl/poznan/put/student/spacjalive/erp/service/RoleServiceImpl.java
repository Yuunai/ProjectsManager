package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.RoleRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> getRoles() {
		return roleRepository.getRoles();
	}
	
	@Override
	public void saveRole(Role role) {
		roleRepository.saveRole(role);
	}
	
	@Override
	public void deleteRole(int id) {
		roleRepository.deleteRole(id);
	}
	
	@Override
	public Role getRole(int id) {
		return roleRepository.getRole(id);
	}
}
