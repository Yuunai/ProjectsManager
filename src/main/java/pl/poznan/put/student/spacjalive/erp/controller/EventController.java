package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;
import pl.poznan.put.student.spacjalive.erp.service.*;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ParticipationViewModel;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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

	@Autowired
	ReservationService reservationService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listEvents(Model model, @RequestParam("archived") boolean archived) {
		List<Event> events = eventService.getEvents(archived, null);
		model.addAttribute("events", events);
		
		return "home";
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
		
		eventService.saveEvent(event);
		
		return "redirect:/home";
	}
	
	@GetMapping("/eventDetails")
	public String eventDetails(Model model, @RequestParam("eventId") int eventId) throws NotFoundException {
		Event event = eventService.getEvent(eventId);
		model.addAttribute("event", event);
		
		List<Participation> participations = participationService.getParticipationsByEventId(eventId);
		model.addAttribute("participations", participations);
		
		ParticipationViewModel participation = new ParticipationViewModel();
		participation.setEventId(eventId);
		model.addAttribute("participation", participation);
		
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);

		List<Reservation> reservations = reservationService.getReservations();
		model.addAttribute("reservations", reservations);

		List<UserDetails> users = userService.getUsersDetails(true);
		model.addAttribute("users", users);
		
		List<Integer> userIds = users.stream().map(e -> e.getUserId()).collect(Collectors.toList());
		Map<Integer, String> usersNames = userService.getUsersNamesMap(userIds);
		model.addAttribute("usersNames", usersNames);
		
		return "event-details";
	}
	
	@GetMapping("/updateEventForm")
	public String updateEventForm(Model model, @RequestParam("eventId") int eventId) throws NotFoundException {
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
