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
import pl.poznan.put.student.spacjalive.erp.entity.User;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Lending;
import pl.poznan.put.student.spacjalive.erp.service.UserService;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.LendingService;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/lending")
public class LendingController {
	
	Logger logger = Logger.getLogger(LendingController.class.getSimpleName());
	
	@Autowired
	LendingService lendingService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listLendings(Model model) {
		//TODO exception handling
		List<Lending> lendings = lendingService.getLendings();
		model.addAttribute("lendings", lendings);
		
		return "list-lendings";
	}
	
	@GetMapping("/addLendingForm")
	public String addLendingForm(Model model) {
		Lending lending = new Lending();
		model.addAttribute("lending", lending);
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment();
		model.addAttribute("equipmentList", equipmentList);
		
		List<Event> events = eventService.getEvents(0);
		model.addAttribute("events", events);
		
		List<User> users = userService.getUsers(true);
		model.addAttribute("employees", users);
		
		return "add-lending-form";
	}
	
	@PostMapping("/addLending")
	public String addLending(@ModelAttribute("lending") @Valid Lending lending, BindingResult result, Model model) {
		logger.info(lending.toString());
		
		if (result.hasErrors()) {
			model.addAttribute("lending", lending);
			
			List<Equipment> equipmentList = equipmentService.getFreeEquipment();
			model.addAttribute("equipmentList", equipmentList);
			
			List<Event> events = eventService.getEvents(0);
			model.addAttribute("events", events);
			
			List<User> users = userService.getUsers(true);
			model.addAttribute("employees", users);
			
			return "add-lending-form";
		}
		
		try {
			lendingService.saveLending(lending);
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
			return "add-lending-form";
		} catch (HibernateJdbcException e) {
			
			List<Equipment> equipmentList = equipmentService.getFreeEquipment();
			model.addAttribute("equipmentList", equipmentList);
			
			List<Event> events = eventService.getEvents(0);
			model.addAttribute("events", events);
			
			List<User> users = userService.getUsers(true);
			model.addAttribute("employees", users);
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Lending len = lendingService.getLending(lending.getId());
				model.addAttribute("lending", len);
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("lending", lending);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-lending-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/lending/list";
		}
		
		return "redirect:/lending/list";
	}
	
	@GetMapping("/updateLendingForm")
	public String updateLendingForm(Model model, @RequestParam("lendingId") int lendingId) {
		Lending lending = lendingService.getLending(lendingId);
		model.addAttribute("lending", lending);
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment();
		model.addAttribute("equipmentList", equipmentList);
		
		List<Event> events = eventService.getEvents(0);
		events.remove(lending.getEvent());
		model.addAttribute("events", events);
		
		List<User> users = userService.getUsers(true);
		users.remove(lending.getUser());
		model.addAttribute("employees", users);
		
		return "add-lending-form";
	}
	
	@GetMapping("/deleteLending")
	public String deleteLending(@RequestParam("lendingId") int lendingId) {
		lendingService.deleteLending(lendingId);
		
		return "redirect:/lending/list";
	}
	
}
