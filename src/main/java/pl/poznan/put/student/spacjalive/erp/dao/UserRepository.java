package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.*;

import java.util.List;

public interface UserRepository {
	
	public UserDetails getUserDetails(int id);
	
	public List<UserDetails> getUsersDetails();
	
	public List<UserDetails> getUsersDetails(boolean active);
	
	public void saveUserDetails(UserDetails details);
	
	public User getUser(int id);
	
	public User getUserByEmail(String email);
	
	public List<User> getUsers();
	
	public List<User> getUsers(boolean enabled);
	
	public void saveUser(User user);
	
	public void setUserEnabled(int id, boolean enabled);
	
	public List<AdministrativeRole> getAdmRoles();
	
	public AdministrativeRole getAdmRole(int id);
}
