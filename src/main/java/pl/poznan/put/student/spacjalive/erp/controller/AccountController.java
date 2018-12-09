package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.AdministrativeRole;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.exceptions.EmailAlreadyTakenException;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	public String usersList(Model model){
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		
		return "list-accounts";
	}
	
	@GetMapping("/newAccount")
	public String newAccount(Model model) {
		User user = new User();
		model.addAttribute(user);
		
		return "new-account";
	}
	
	@PostMapping("/createAccount")
	public String createAccount(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "new-account";
		}
		
		try {
			userService.saveNewUser(user);
		} catch (EmailAlreadyTakenException e) {
			model.addAttribute("message", "Email zajęty!");
			return "new-account";
		}
		
		return "redirect:/account/list";
	}
	
	@GetMapping("/editAccount")
	public String editAccount(@RequestParam(name = "userId") int userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		List<AdministrativeRole> admRoles = userService.getAdmRoles();
		admRoles.removeAll(user.getAdmRoles());
		model.addAttribute("admRoles", admRoles);
		
		return "update-account";
	}
	
	@PostMapping("/updateAccount")
	public String updateAccount(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<AdministrativeRole> admRoles = userService.getAdmRoles();
			admRoles.removeAll(user.getAdmRoles());
			model.addAttribute("admRoles", admRoles);
			return "update-account";
		}
		userService.updateUserAdmRolesAndStatus(user);
		
		return "redirect:/account/accountDetails?userId=" + user.getId();
	}
	
	@GetMapping("/setEnabled")
	public String setAccountEnabled(@RequestParam(name = "userId") int id, @RequestParam(name = "enabled") String
			enabled) {
		userService.setUserEnabled(id, Boolean.valueOf(enabled));
		
		return "redirect:/account/list";
	}
	
	@GetMapping("/accountDetails")
	public String accountDetails(@RequestParam("userId") int userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		return "account-details";
	}
	
}
