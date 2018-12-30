package pl.poznan.put.student.spacjalive.erp.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.TokenRepository;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenExpiredException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenNotFound;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
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
		User user = userRepository.getUserByEmail(email);
		Hibernate.initialize(user.getAdmRoles());
		return user;
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
		userRepository.saveUser(oldUser);
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
	
	@Override
	public void createAndSendToken(int userId, int tokenType, String serverAddress) throws MessagingException {
		String randomHash = UUID.randomUUID().toString();
		User user = userRepository.getUser(userId);
		String message = "Aby ponownie ustawić hasło kliknij poniższy link:\n" +
				serverAddress + "/setNewPassword?token=" + randomHash;
		
		EmailService es = EmailService.getInstance();
		es.sendEmail(user.getEmail(), "Reset Password Token", message);
		tokenRepository.deleteToken(user.getId(), Token.RESET_PASSWORD_TOKEN);
		Token token = new Token(user.getId(), Token.RESET_PASSWORD_TOKEN, randomHash,
				LocalDateTime.now().plusMinutes(Token.TOKEN_EXPIRATION_TIME));
		tokenRepository.saveToken(token);
	}
	
	@Override
	public void setUserPassword(String tokenHash, String newPassword) throws TokenNotFound, TokenExpiredException {
		Token token = tokenRepository.getTokenByHash(tokenHash);
		
		if(token == null) {
			throw new TokenNotFound();
		} else if (token.getExpirationDate().isBefore(LocalDateTime.now())) {
			throw new TokenExpiredException();
		}
		
		tokenRepository.deleteToken(token.getUserId(), token.getType());
		userRepository.setUserPassword(token.getUserId(), passwordEncoder.encode(newPassword));
	}
}
