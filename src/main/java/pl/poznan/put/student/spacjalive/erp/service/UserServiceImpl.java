package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;
import pl.poznan.put.student.spacjalive.erp.exceptions.UserNotFoundException;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	@Override
	public List<User> getUsers(boolean active) {
		return userRepository.getUsers(active);
	}
	
	@Override
	public void saveNewUser(User user) throws EmailAlreadyTakenException {
		if(userRepository.getUserByEmail(user.getEmail()) != null)
			throw new EmailAlreadyTakenException();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.saveUser(user);
	}
	
	@Override
	public void saveUser(User user) {
		userRepository.saveUser(user);
	}
	
	@Override
	public void deleteUser(int id) {
		userRepository.deleteUser(id);
	}
	
	@Override
	public User getUser(int id) {
		return userRepository.getUser(id);
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
}
