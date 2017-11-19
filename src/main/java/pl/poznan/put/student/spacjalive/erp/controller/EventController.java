package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.service.EventService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

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
    public String addEvent(@ModelAttribute("event") @Valid Event event, BindingResult result) {

        if(result.hasErrors()) {
            return "add-event-form";
        }

        eventService.saveEvent(event);

        return "redirect:/home";
    }

}
