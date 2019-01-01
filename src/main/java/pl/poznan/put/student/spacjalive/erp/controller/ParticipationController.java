package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NoAccessGrantedException;
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
	public String addParticipation(@SessionAttribute("userId") int accessorId,
			@ModelAttribute("participation") ParticipationViewModel participationViewModel)
			throws NoAccessGrantedException {
		checkAccess(accessorId, participationViewModel.getUserId());
		Event event = eventService.getEvent(participationViewModel.getEventId());
		User user = userService.getUser(participationViewModel.getUserId());
		Role role = roleService.getRole(participationViewModel.getRoleId());
		Participation participation = new Participation(event, user, role);
		
		participationService.addParticipation(participation);
		
		return "redirect:/event/eventDetails?eventId=" + participationViewModel.getEventId();
	}
	
	@GetMapping("/deleteParticipation")
	public String deleteParticipation(
			@SessionAttribute("userId") int accessorId,
			@RequestParam("roleId") int roleId,
	        @RequestParam("employeeId") int userId,
	        @RequestParam("eventId") int eventId) throws NoAccessGrantedException {
		checkAccess(accessorId, userId);
		participationService.deleteParticipation(eventId, roleId, userId);
		
		return "redirect:/event/eventDetails?eventId=" + eventId;
	}
	
	private boolean checkAccess(int accessingUserId, int userId) throws NoAccessGrantedException {
		if(accessingUserId == userId) {
			return true;
		} else {
			User user = userService.getUser(accessingUserId);
			if (user.getAdmRoles().stream().anyMatch(e -> e.getId() == AdministrativeRole.ADMIN
					|| e.getId() == AdministrativeRole.MODERATOR))
				return true;
		}
		throw new NoAccessGrantedException();
	}
}
