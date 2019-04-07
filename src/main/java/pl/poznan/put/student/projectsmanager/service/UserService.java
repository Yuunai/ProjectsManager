package pl.poznan.put.student.projectsmanager.service;

import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.*;
import pl.poznan.put.student.projectsmanager.exceptions.token.TokenNotFound;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.projectsmanager.exceptions.token.TokenExpiredException;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface UserService {
	
	UserDetails getUserDetails(int id) throws NotFoundException;
	
	List<UserDetails> getUsersDetails();
	
	List<UserDetails> getUsersDetails(boolean enabled);
	
	void saveUserDetails(UserDetails details);
	
	User getUser(int id) throws NotFoundException;
	
	User getUserByEmail(String email) throws NotFoundException;
	
	List<User> getUsers();
	
	Map<Integer, String> getUsersNamesMap(List<Integer> ids);
	
	List<User> getUsers(boolean enabled);
	
	void saveUser(User user);
	
	void updateUserAdmRolesStatusEmail(User user) throws NotFoundException;
	
	void saveNewUser(User user) throws EmailAlreadyTakenException;
	
	List<AdministrativeRole> getAdmRoles();
	
	AdministrativeRole getAdmRole(int id);
	
	void createAndSendToken(int userId, int tokenType, String contextPath) throws MessagingException, NotFoundException;
	
	void setUserPassword(String tokenHash, String newPassword) throws TokenNotFound, TokenExpiredException, SimplePasswordException;
	
	void setUserPassword(int userId, String newPassword) throws SimplePasswordException;
}
