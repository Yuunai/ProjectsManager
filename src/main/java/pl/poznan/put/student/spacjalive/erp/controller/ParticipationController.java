package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/participation")
public class ParticipationController {
	
	@Autowired
	ParticipationService participationService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@PostMapping("/addParticipation")
	public String addParticipation(@ModelAttribute("participation") ParticipationViewModel participationViewModel) {
		Event event = eventService.getEvent(participationViewModel.getEventId());
		User user = userService.getUser(participationViewModel.getEmployeeId());
		Role role = roleService.getRole(participationViewModel.getRoleId());
		Participation participation = new Participation(event, user, role);
		
		participationService.addParticipation(participation);
		
		return "redirect:/event/eventDetails?eventId=" + participationViewModel.getEventId();
	}
	
	@GetMapping("/deleteParticipation")
	public String deleteParticipation(@RequestParam("roleId") int roleId,
	                                  @RequestParam("employeeId") int employeeId,
	                                  @RequestParam("eventId") int eventId) {
		participationService.deleteParticipation(eventId, roleId, employeeId);
		
		return "redirect:/event/eventDetails?eventId=" + eventId;
	}
	
}
