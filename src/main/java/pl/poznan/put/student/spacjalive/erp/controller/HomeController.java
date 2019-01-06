package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenExpiredException;
import pl.poznan.put.student.spacjalive.erp.exceptions.token.TokenNotFound;
import pl.poznan.put.student.spacjalive.erp.service.*;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ParticipationViewModel;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Event> events = eventService.getEvents(false, null);
		model.addAttribute("events", events);
		model.addAttribute("participation", new ParticipationViewModel());
		model.addAttribute("roles", roleService.getRoles());
		
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
            model.addAttribute("message", "msg.passMailSend");
		} catch (MessagingException | NotFoundException e) {
            model.addAttribute("message", "error.invalidEmail");

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
			model.addAttribute("message", "msg.passChanged");
			return loginPage(model);
		} catch (TokenNotFound tokenNotFound) {
			model.addAttribute("message", "error.invalidToken");
		} catch (TokenExpiredException e) {
			model.addAttribute("message", "error.expiredToken");
		} catch (SimplePasswordException e) {
			model.addAttribute("message", "error.passSimple");
		}
		
		return "set-new-password";
	}

	@GetMapping("/public")
	public String publicUI (Model model) {
		List<Event> events = eventService.getEvents(false, true);
		model.addAttribute("events", events);

		return "public";
	}
	
}
