package pl.poznan.put.student.spacjalive.erp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.*;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
	
	Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParticipationService participationService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listUsers(Model model) {
		List<UserDetails> users = userService.getUsersDetails();
		model.addAttribute("users", users);
		
		return "list-users";
	}
	
	@GetMapping("/userDetails")
	public String userDetails(@RequestParam("userId") int userId, Model model) throws NotFoundException {
		UserDetails details = userService.getUserDetails(userId);
		model.addAttribute("details", details);
		
		return "user-details";
	}
	
	@GetMapping("/updateUserForm")
	public String updateUser(@SessionAttribute("userId") int accessorId,
			@RequestParam("userId") int userId, Model model) throws NoAccessGrantedException, NotFoundException {
		checkAccess(accessorId, userId);
		UserDetails userDetails = userService.getUserDetails(userId);
		if(userDetails == null) {
			userDetails = new UserDetails();
			userDetails.setUserId(userId);
		}
		model.addAttribute("details", userDetails);
		model.addAttribute("administrativeRoles", userService.getAdmRoles());
		
		return "update-user-form";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@SessionAttribute("userId") int accessorId,
	                         @ModelAttribute("details") @Valid UserDetails details,
	                         BindingResult result, Model model)
			throws NoAccessGrantedException {
		checkAccess(accessorId, details.getUserId());
		if (result.hasErrors())
			return "update-user-form";
		
		userService.saveUserDetails(details);
		
		return "redirect:/user/list";
	}
	
	@PostMapping("/setNewPassword")
	public String setNewPassword(@SessionAttribute("userId") int userId, @RequestParam("Password") String password,
	                              Model model) throws NotFoundException {
		
		try {
			userService.setUserPassword(userId, password);
			model.addAttribute("message", "Hasło zostało zmienione!");
		} catch (SimplePasswordException e) {
			model.addAttribute("message", "Hasło musi mieć przynajmniej jedną cyfrę, wielką literę, małą literę " +
					"oraz znak specjalny. Hasło nie może zawierać znaków białych oraz nie może być krótsze niż 8 " +
					"znaków");
		}
		
		
		return userDetails(userId, model);
	}
	
	private boolean checkAccess(int accessingUserId, int userId) throws NoAccessGrantedException {
		if(accessingUserId == userId) {
			return true;
		} else {
			User user = null;
			try {
				user = userService.getUser(accessingUserId);
			} catch (NotFoundException e) {
				logger.error("It shouldn't happen! Logged user have to exist", e);
			}
			if (user.getAdmRoles().stream().anyMatch(e -> e.getId() == AdministrativeRole.ADMIN))
				return true;
		}
		throw new NoAccessGrantedException();
	}
}
