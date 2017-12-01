package pl.poznan.put.student.spacjalive.erp.controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;
import pl.poznan.put.student.spacjalive.erp.service.RoleService;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ParticipationViewModel;

import javax.validation.Valid;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/event")
public class EventController {

    Logger logger = Logger.getLogger(EventController.class.getName());

    @Autowired
    EventService eventService;

    @Autowired
    ParticipationService participationService;

    @Autowired
    RoleService roleService;

    @Autowired
    EmployeeService employeeService;

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

        try {
            eventService.saveEvent(event);
        } catch(JDBCConnectionException e) {
            result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
        } catch(SQLGrammarException e) {
            result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
        }catch (JDBCException e) {
            result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
            return "add-event-form";
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

        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "event-details";
    }

}
