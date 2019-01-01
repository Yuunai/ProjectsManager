package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenExpiredException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenNotFound;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
	
	UserDetails getUserDetails(int id);
	
	List<UserDetails> getUsersDetails();
	
	List<UserDetails> getUsersDetails(boolean active);
	
	void saveUserDetails(UserDetails details);
	
	User getUser(int id);
	
	User getUserByEmail(String email);
	
	List<User> getUsers();
	
	List<User> getUsers(boolean enabled);
	
	void saveUser(User user);
	
	void updateUserAdmRolesAndStatus(User user);
	
	void saveNewUser(User user) throws EmailAlreadyTakenException;
	
	List<AdministrativeRole> getAdmRoles();
	
	AdministrativeRole getAdmRole(int id);
	
	void createAndSendToken(int userId, int tokenType, String contextPath) throws MessagingException;
	
	void setUserPassword(String tokenHash, String newPassword) throws TokenNotFound, TokenExpiredException;
	
}
