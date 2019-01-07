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
		
		List<Participation> participations = participationService.getParticipationsByUserId(userId);
		model.addAttribute("participations", participations);
  
		return "user-details";
	}

	@PostMapping("/updateUser")
	public String updateUser(@SessionAttribute("userId") int accessorId,
	                         @ModelAttribute("details") @Valid UserDetails details,
	                         BindingResult result, Model model)
			throws NoAccessGrantedException {
		checkAccess(accessorId, details.getUserId());
		if (result.hasErrors())
			return "user-details";
		
		userService.saveUserDetails(details);
		
		return "redirect:/user/userDetails?userId="+details.getUserId();
	}
	
	@PostMapping("/setNewPassword")
	public String setNewPassword(@SessionAttribute("userId") int userId, @RequestParam("password") String password,
	                              Model model) throws NotFoundException {
		
		try {
			userService.setUserPassword(userId, password);
			model.addAttribute("message", "msg.passChanged");
		} catch (SimplePasswordException e) {
			model.addAttribute("message", "error.passSimple");
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
