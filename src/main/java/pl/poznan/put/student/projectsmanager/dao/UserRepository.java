package pl.poznan.put.student.projectsmanager.dao;

import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public interface UserRepository {
	
	UserDetails getUserDetails(int id) throws NotFoundException;
	
	List<UserDetails> getUsersDetails();
	
	List<UserDetails> getUsersDetails(boolean enabled);
	
	void saveUserDetails(UserDetails details);
	
	User getUser(int id) throws NotFoundException;
	
	User getUserByEmail(String email) throws NotFoundException;
	
	List<User> getUsers();
	
	List<User> getUsers(boolean enabled);
	
	Map<Integer, String> getUsersNamesMap(List<Integer> ids);
	
	void saveUser(User user);
	
	List<User> getAdminUsers();
	
	List<AdministrativeRole> getAdmRoles();
	
	AdministrativeRole getAdmRole(int id);
	
	void setUserPassword(int userId, String password);
}
