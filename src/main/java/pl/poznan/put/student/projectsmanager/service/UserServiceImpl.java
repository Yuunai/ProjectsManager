package pl.poznan.put.student.projectsmanager.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.dao.UserRepository;
import pl.poznan.put.student.projectsmanager.entity.*;
import pl.poznan.put.student.projectsmanager.exceptions.*;
import pl.poznan.put.student.projectsmanager.exceptions.token.TokenNotFound;
import pl.poznan.put.student.projectsmanager.dao.TokenRepository;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.projectsmanager.exceptions.token.TokenExpiredException;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;
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
		UserDetails userDetails;
		try {
			userDetails = userRepository.getUserDetails(id);
		} catch (NotFoundException e) {
			userRepository.getUser(id);
			userDetails = new UserDetails();
			userDetails.setUserId(id);
		}
		return userDetails;
	}
	
	@Override
	public List<UserDetails> getUsersDetails() {
		return userRepository.getUsersDetails();
	}
	
	@Override
	public List<UserDetails> getUsersDetails(boolean enabled) {
		return userRepository.getUsersDetails(enabled);
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
	public Map<Integer, String> getUsersNamesMap(List<Integer> ids) {
		return userRepository.getUsersNamesMap(ids);
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
	public void updateUserAdmRolesStatusEmail(User user) throws NotFoundException {
		User oldUser = userRepository.getUser(user.getId());
		oldUser.setEmail(user.getEmail());
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
		String message = "<h2>Wiadomość wysłana z systemu SpacjaTV - reset hasła</h2><br>" +
				"Aby ponownie ustawić hasło kliknij poniższy link:<br>" +
				"<a href=\"" + serverAddress + "/setNewPassword?token=" + randomHash + "\" >Klik</a>" +
				"<br><br>If you want to change a password, click a link bellow:<br>" +
				"<a href=\"" + serverAddress + "/setNewPassword?token=" + randomHash + "\" >Click</a>";
		
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
