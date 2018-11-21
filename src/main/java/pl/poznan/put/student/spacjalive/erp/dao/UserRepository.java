package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.UserNotFoundException;

import java.util.List;

public interface UserRepository {
	
	public List<User> getUsers();
	
	public List<User> getUsers(boolean enabled);
	
	public void saveUser(User user);
	
	public void deleteUser(int id);
	
	public User getUser(int id);
	
	public User getUserByEmail(String email);
}
