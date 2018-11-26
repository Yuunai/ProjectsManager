package pl.poznan.put.student.spacjalive.erp.controller;

import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.service.UserService;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
	
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
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		
		return "list-users";
	}
	
	@GetMapping("/userDetails")
	public String userDetails(@RequestParam("userId") int userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		List<Participation> participations = participationService.getParticipationsByUserId(userId);
		model.addAttribute("participations", participations);
		
		return "user-details";
	}
	
	@GetMapping("/addUserForm")
	public String addUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "add-user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors())
			return "add-user-form";
		
		try {
			userService.saveNewUser(user);
		} catch (JDBCConnectionException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (GenericJDBCException e) {
			
			if (e.getSQLState().equalsIgnoreCase("12345")) {
				result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
			} else {
				result.reject(String.valueOf(e.getErrorCode()), "Niepoprawne dane!");
			}
			return "add-user-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				User emp = userService.getUser(user.getId());
				model.addAttribute("user", emp);
				
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("user", user);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-user-form";
		} catch (Exception e) {
		
		}
		return "redirect:/user/list";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int userId, Model model) {
		try {
			userService.deleteUser(userId);
		} catch (JDBCConnectionException e) {
			model.addAttribute("message", "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			model.addAttribute("message", "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (JDBCException e) {
			List<User> users = userService.getUsers();
			
			model.addAttribute("users", users);
			
			if (e.getSQLState().equalsIgnoreCase("12345")) {
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("message", e.getSQLException().getMessage());
			}
			return "list-users";
		}
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/updateUserForm")
	public String updateUser(@RequestParam("userId") int userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		return "add-user-form";
	}
	
}
