package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.*;

import java.util.List;

public interface UserRepository {
	
	UserDetails getUserDetails(int id);
	
	List<UserDetails> getUsersDetails();
	
	List<UserDetails> getUsersDetails(boolean active);
	
	void saveUserDetails(UserDetails details);
	
	User getUser(int id);
	
	User getUserByEmail(String email);
	
	List<User> getUsers();
	
	List<User> getUsers(boolean enabled);
	
	void saveUser(User user);
	
	List<AdministrativeRole> getAdmRoles();
	
	AdministrativeRole getAdmRole(int id);
	
	void setUserPassword(int userId, String password);
}
