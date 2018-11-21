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
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.service.UserService;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;
import pl.poznan.put.student.spacjalive.erp.service.RoleService;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ParticipationViewModel;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ParticipationService participationService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/addEventForm")
	public String addEventForm(Model model) throws ParseException {
		Event event = new Event();
		model.addAttribute("event", event);
		
		return "add-event-form";
	}
	
	@PostMapping("/addEvent")
	public String addEvent(@ModelAttribute("event") @Valid Event event, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-event-form";
		}
		
		try {
			eventService.saveEvent(event);
		} catch (JDBCConnectionException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (JDBCException e) {
			result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
			return "add-event-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Event ev = eventService.getEvent(event.getId());
				model.addAttribute("event", ev);
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("event", event);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-event-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/home";
		}
//TODO add database errors handling(everywhere)
		
		return "redirect:/home";
	}
	
	@GetMapping("/eventDetails")
	public String eventDetails(Model model, @RequestParam("eventId") int eventId) {
		Event event = eventService.getEvent(eventId);
		model.addAttribute("event", event);
		
		List<Participation> participations = participationService.getParticipationsByEventId(eventId);
		model.addAttribute("participations", participations);
		
		ParticipationViewModel participation = new ParticipationViewModel();
		participation.setEventId(eventId);
		model.addAttribute("participation", participation);
		
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);
		
		List<User> users = userService.getUsers();
		model.addAttribute("employees", users);
		
		return "event-details";
	}
	
	@GetMapping("/updateEventForm")
	public String updateEventForm(Model model, @RequestParam("eventId") int eventId) {
		Event event = eventService.getEvent(eventId);
		model.addAttribute("event", event);
		
		return "add-event-form";
	}
	
	@GetMapping("/deleteEvent")
	public String deleteEvent(@RequestParam("eventId") int eventId) {
		eventService.deleteEvent(eventId);
		
		return "redirect:/home";
	}
	
}
