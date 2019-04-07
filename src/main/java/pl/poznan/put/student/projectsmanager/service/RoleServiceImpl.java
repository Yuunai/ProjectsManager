package pl.poznan.put.student.projectsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

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
	public Role getRole(int id) throws NotFoundException {
		return roleRepository.getRole(id);
	}
}
