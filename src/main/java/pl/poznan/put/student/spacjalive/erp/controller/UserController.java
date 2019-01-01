package pl.poznan.put.student.spacjalive.erp.controller;

import org.hibernate.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NoAccessGrantedException;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;
import pl.poznan.put.student.spacjalive.erp.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/user")
@Controller
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class.getSimpleName());
	
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
	public String userDetails(@RequestParam("userId") int userId, Model model) {
		UserDetails details = userService.getUserDetails(userId);
		model.addAttribute("details", details);
		
		return "user-details";
	}
	
	@GetMapping("/updateUserForm")
	public String updateUser(@SessionAttribute("userId") int accessorId,
			@RequestParam("userId") int userId, Model model) throws NoAccessGrantedException {
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
		
		try {
			userService.saveUserDetails(details);
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
			return "update-user-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				UserDetails userDetails = userService.getUserDetails(details.getUserId());
				model.addAttribute("user", userDetails);
				
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("user", details);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "update-user-form";
		} catch (Exception e) {
			logger.warning(e.getStackTrace().toString());
		}
		return "redirect:/user/list";
	}
	
	private boolean checkAccess(int accessingUserId, int userId) throws NoAccessGrantedException {
		if(accessingUserId == userId) {
			return true;
		} else {
			User user = userService.getUser(accessingUserId);
			if (user.getAdmRoles().stream().anyMatch(e -> e.getId() == AdministrativeRole.ADMIN))
				return true;
		}
		throw new NoAccessGrantedException();
	}
	
//	@GetMapping("/deleteUser")
//	public String deleteUser(@RequestParam("userId") int userId, Model model) {
//		try {
//			userService.deleteUser(userId);
//		} catch (JDBCConnectionException e) {
//			model.addAttribute("message", "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
//		} catch (SQLGrammarException e) {
//			model.addAttribute("message", "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
//		} catch (JDBCException e) {
//			List<User> users = userService.getUsers();
//
//			model.addAttribute("users", users);
//
//			if (e.getSQLState().equalsIgnoreCase("12345")) {
//				model.addAttribute("message", e.getSQLException().getMessage());
//			} else {
//				model.addAttribute("message", e.getSQLException().getMessage());
//			}
//			return "list-users";
//		}
//
//		return "redirect:/user/list";
//	}
}
