package pl.poznan.put.student.spacjalive.erp.controller;

import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listRoles(Model model) {
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);
		
		return "list-roles";
	}
	
	@GetMapping("/addRoleForm")
	public String addRoleForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		
		return "add-role-form";
	}
	
	@PostMapping("/addRole")
	public String addRole(@ModelAttribute("role") @Valid Role role, BindingResult result, Model model) {
		try {
			roleService.saveRole(role);
		} catch (JDBCConnectionException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (JDBCException e) {
			
			if (e.getSQLState().equalsIgnoreCase("12345")) {
				result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
			} else {
				result.reject(String.valueOf(e.getErrorCode()), "Niepoprawne dane!");
			}
			return "add-role-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Role rl = roleService.getRole(role.getId());
				model.addAttribute("role", rl);
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("role", role);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-role-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/role/list";
		}
		
		return "redirect:/role/list";
	}
	
	@GetMapping("/deleteRole")
	public String addRoleForm(@RequestParam("roleId") int id) {
		roleService.deleteRole(id);
		
		return "redirect:/role/list";
	}
	
	@GetMapping("/updateRoleForm")
	public String updateRoleForm(@RequestParam("roleId") int id, Model model) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);
		
		return "add-role-form";
	}
	
}
