package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.MostActiveEmployeeView;
import pl.poznan.put.student.spacjalive.erp.mongo.dao.LogDAO;
import pl.poznan.put.student.spacjalive.erp.mongo.entity.Log;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.MostActiveEmployeeViewService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EventService eventService;

    @Autowired
    MostActiveEmployeeViewService mostActiveEmployeeViewService;

    @Autowired
    LogDAO logDAO;

    @GetMapping("/home")
    public String home(Model model) {

        List<Event> events = eventService.getEvents();

        model.addAttribute("events", events);

        List<MostActiveEmployeeView> mostActiveEmployees = mostActiveEmployeeViewService.getEmployees();

        model.addAttribute("mostActiveEmployees", mostActiveEmployees);

        return "home";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
        List<Log> logs = logDAO.getLogs();

        model.addAttribute("logs", logs);

        return "list-logs";
    }

}
