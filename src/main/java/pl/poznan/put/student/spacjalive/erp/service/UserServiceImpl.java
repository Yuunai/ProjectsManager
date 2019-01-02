package pl.poznan.put.student.spacjalive.erp.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.TokenRepository;
import pl.poznan.put.student.spacjalive.erp.dao.UserRepository;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenExpiredException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenNotFound;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

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
	public UserDetails getUserDetails(int id) throws NotFoundException {
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
	public User getUser(int id) throws NotFoundException {
		User user = userRepository.getUser(id);
		Hibernate.initialize(user.getAdmRoles());
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws NotFoundException {
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
	public void updateUserAdmRolesAndStatus(User user) throws NotFoundException {
		User oldUser = userRepository.getUser(user.getId());
		oldUser.setAdmRoles(user.getAdmRoles());
		oldUser.setEnabled(user.isEnabled());
		userRepository.saveUser(oldUser);
	}
	
	@Override
	public void saveNewUser(User user) throws EmailAlreadyTakenException {
		try {
			userRepository.getUserByEmail(user.getEmail());
			throw new EmailAlreadyTakenException();
		} catch (NotFoundException e) {
//		    Actually, this is ok.
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.saveUser(user);
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
	public void createAndSendToken(int userId, int tokenType, String serverAddress) throws MessagingException, NotFoundException {
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
	public void setUserPassword(String tokenHash, String newPassword) throws TokenNotFound, TokenExpiredException, SimplePasswordException {
		Token token = tokenRepository.getTokenByHash(tokenHash);
		
		if(token == null) {
			throw new TokenNotFound();
		} else if (token.getExpirationDate().isBefore(LocalDateTime.now())) {
			throw new TokenExpiredException();
		}
		checkPasswordComplexity(newPassword);
		tokenRepository.deleteToken(token.getUserId(), token.getType());
		userRepository.setUserPassword(token.getUserId(), passwordEncoder.encode(newPassword));
	}
	
	@Override
	public void setUserPassword(int userId, String newPassword) throws SimplePasswordException {
		checkPasswordComplexity(newPassword);
		userRepository.setUserPassword(userId, passwordEncoder.encode(newPassword));
	}
	
	private void checkPasswordComplexity(String password) throws SimplePasswordException {
		Matcher matcher = User.passwordPattern.matcher(password);
		if(!matcher.matches())
			throw new SimplePasswordException();
	}
	
}
