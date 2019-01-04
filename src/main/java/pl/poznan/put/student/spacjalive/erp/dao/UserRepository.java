package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface UserRepository {
	
	UserDetails getUserDetails(int id) throws NotFoundException;
	
	List<UserDetails> getUsersDetails();
	
	List<UserDetails> getUsersDetails(boolean enabled);
	
	void saveUserDetails(UserDetails details);
	
	User getUser(int id) throws NotFoundException;
	
	User getUserByEmail(String email) throws NotFoundException;
	
	List<User> getUsers();
	
	List<User> getUsers(boolean enabled);
	
	void saveUser(User user);
	
	List<AdministrativeRole> getAdmRoles();
	
	AdministrativeRole getAdmRole(int id);
	
	void setUserPassword(int userId, String password);
}
