package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;
import pl.poznan.put.student.spacjalive.erp.service.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class RESTController {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private ParticipationService participationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/getFreeEquipment", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFreeEquipment(@RequestParam(name = "date-since") String dateSince,
	                               @RequestParam(name = "time-since") String timeSince,
	                               @RequestParam(name = "date-to") String dateTo,
	                               @RequestParam(name = "time-to") String timeTo) {
		return equipmentService.getFreeEquipmentJson(dateSince, timeSince, dateTo, timeTo).toString();
	}
	
	@GetMapping(value = "/getEventParticipants", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEventParticipants(@RequestParam("eventId") int eventId) {
		return participationService.getParticipationsJsonByEventId(eventId).toString();
	}
	
	@GetMapping("/makeParticipation")
	@ResponseBody
	public String makeParticipation(HttpServletResponse response,
			@SessionAttribute("userId") int userId,
			@RequestParam(name = "eventId") int eventId,
			@RequestParam(name = "roleId") int roleId) {
		try {
			User user = userService.getUser(userId);
			Event event = eventService.getEvent(eventId);
			Role role = roleService.getRole(roleId);
			participationService.addParticipation(new Participation(event, user, role));
			response.setStatus(HttpServletResponse.SC_OK);
			return "HTTP 200 OK";
		} catch (NotFoundException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "HTTP 404 NOT FOUND";
		}
	}
	
}
