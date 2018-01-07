package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Lending;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;
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
    LendingService  lendingService;

    @Autowired
    EventService eventService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EmployeeService employeeService;

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

        List<Employee> employees = employeeService.getEmployees(true);
        model.addAttribute("employees", employees);

        return "add-lending-form";
    }

    @PostMapping("/addLending")
    public String addLending(@ModelAttribute("lending") @Valid Lending lending, BindingResult result, Model model) {

        logger.info(lending.toString());

        if(result.hasErrors()) {
            logger.info(result.toString());
            model.addAttribute("lending", lending);

            List<Equipment> equipmentList = equipmentService.getFreeEquipment();
            model.addAttribute("equipmentList", equipmentList);

            List<Event> events = eventService.getEvents(0);
            model.addAttribute("events", events);

            List<Employee> employees = employeeService.getEmployees(true);
            model.addAttribute("employees", employees);

            return "add-lending-form";
        }

        lendingService.saveLending(lending);

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

        List<Employee> employees = employeeService.getEmployees(true);
        employees.remove(lending.getEmployee());
        model.addAttribute("employees", employees);

        return "add-lending-form";
    }

    @GetMapping("/deleteLending")
    public String deleteLending(@RequestParam("lendingId") int lendingId) {

        lendingService.deleteLending(lendingId);

        return "redirect:/lending/list";
    }

}
