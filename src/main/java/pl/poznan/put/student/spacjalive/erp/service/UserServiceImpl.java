package pl.poznan.put.student.spacjalive.erp.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.AdministrativeRole;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.entity.UserDetails;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails getUserDetails(int id) {
		return userRepository.getUserDetails(id);
	}
	
	@Override
	public List<UserDetails> getUsersDetails() {
		return userRepository.getUsersDetails();
	}
	
	@Override
	public List<UserDetails> getUsersDetails(boolean active) {
		return userRepository.getUsersDetails(active);
	}
	
	@Override
	public void saveUserDetails(UserDetails details) {
		userRepository.saveUserDetails(details);
	}
	
	@Override
	public User getUser(int id) {
		User user = userRepository.getUser(id);
		Hibernate.initialize(user.getAdmRoles());
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	
	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	@Override
	public List<User> getUsers(boolean enabled) {
		return userRepository.getUsers(enabled);
	}
	
	@Override
	public void saveUser(User user) {
		userRepository.saveUser(user);
	}
	
	@Override
	public void updateUserAdmRolesAndStatus(User user) {
		User oldUser = userRepository.getUser(user.getId());
		oldUser.setAdmRoles(user.getAdmRoles());
		oldUser.setEnabled(user.isEnabled());
		userRepository.saveUser(user);
	}
	
	@Override
	public void saveNewUser(User user) throws EmailAlreadyTakenException {
		if(userRepository.getUserByEmail(user.getEmail()) != null)
			throw new EmailAlreadyTakenException();

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.saveUser(user);
	}
	
	@Override
	public void setUserEnabled(int id, boolean enabled) {
		userRepository.setUserEnabled(id, enabled);
	}
	
	@Override
	public List<AdministrativeRole> getAdmRoles() {
		return userRepository.getAdmRoles();
	}
	
	@Override
	public AdministrativeRole getAdmRole(int id) {
		return userRepository.getAdmRole(id);
	}
}
