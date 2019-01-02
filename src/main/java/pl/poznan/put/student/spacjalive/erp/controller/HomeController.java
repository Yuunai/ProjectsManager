package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenExpiredException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenNotFound;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Event> events = eventService.getEvents(false);
		model.addAttribute("events", events);
		
		return "home";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(Model model) {
		return "login-page";
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request, Model model, @RequestParam("email") String email) {
		try {
			User user = userService.getUserByEmail(email);
			String serverAddress = request.getRequestURL().toString();
			serverAddress = serverAddress.substring(0, serverAddress.length() - request.getRequestURI().length());
			userService.createAndSendToken(user.getId(), Token.RESET_PASSWORD_TOKEN, serverAddress);
			model.addAttribute("message", "Link do zmiany hasła wysłany na podany adres email! Link będzie ważny " +
					"przez kolejne " + Token.TOKEN_EXPIRATION_TIME + "minut.");
		} catch (MessagingException | NotFoundException e) {
			model.addAttribute("message", "Niepoprawny lub nieznany adres email!");
			e.printStackTrace();
		}
		
		return loginPage(model);
	}
	
	@GetMapping("/setNewPassword")
	public String setNewPassword() {
		return "set-new-password";
	}
	
	@PostMapping("/setNewPassword")
	public String setNewPassword(Model model, @RequestParam("token") String token,
	                             @RequestParam("newPassword") String newPassword) {
		try {
			userService.setUserPassword(token, newPassword);
			model.addAttribute("message", "Hasło zostało zmienione! Możesz się zalogować.");
			return loginPage(model);
		} catch (TokenNotFound tokenNotFound) {
			model.addAttribute("message", "Nieznany token!");
		} catch (TokenExpiredException e) {
			model.addAttribute("message", "Token wygasł!");
		} catch (SimplePasswordException e) {
			model.addAttribute("message", "Hasło musi mieć przynajmniej jedną cyfrę, wielką literę, małą literę " +
					"oraz znak specjalny. Hasło nie może zawierać znaków białych oraz nie może być krótsze niż 8 " +
					"znaków");
		}
		
		return "set-new-password";
	}
	
}
