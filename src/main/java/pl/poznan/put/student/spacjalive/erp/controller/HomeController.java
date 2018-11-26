package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.service.EventService;

import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	EventService eventService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Event> events = eventService.getEvents();
		model.addAttribute("events", events);
		
		return "home";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(Model model) {
		return "login-page";
	}
	
}
