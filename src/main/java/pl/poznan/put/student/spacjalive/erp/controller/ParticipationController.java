package pl.poznan.put.student.spacjalive.erp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NoAccessGrantedException;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;
import pl.poznan.put.student.spacjalive.erp.service.*;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ParticipationViewModel;

@Controller
@RequestMapping("/participation")
public class ParticipationController {
	
	Logger logger = LogManager.getLogger(ParticipationController.class);
	
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
			throws NoAccessGrantedException, NotFoundException {
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
	        @RequestParam("userId") int userId,
	        @RequestParam("eventId") int eventId) throws NoAccessGrantedException {
		checkAccess(accessorId, userId);
		participationService.deleteParticipation(eventId, roleId, userId);
		
		return "redirect:/event/eventDetails?eventId=" + eventId;
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
			if (user.getAdmRoles().stream().anyMatch(e -> e.getId() == AdministrativeRole.ADMIN
					|| e.getId() == AdministrativeRole.MODERATOR))
				return true;
		}
		throw new NoAccessGrantedException();
	}
}
