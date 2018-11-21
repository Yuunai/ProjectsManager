package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;
import pl.poznan.put.student.spacjalive.erp.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
	
	public List<User> getUsers();
	
	public List<User> getUsers(boolean active);
	
	public void saveNewUser(User user) throws EmailAlreadyTakenException;
	
	public void saveUser(User user);
	
	public void deleteUser(int id);
	
	public User getUser(int id);
	
	public User getUserByEmail(String email);
}
