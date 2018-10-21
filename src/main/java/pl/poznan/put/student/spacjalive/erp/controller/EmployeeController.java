package pl.poznan.put.student.spacjalive.erp.controller;

import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Authority;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.Position;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;
import pl.poznan.put.student.spacjalive.erp.service.ParticipationService;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    Logger logger = Logger.getLogger(EmployeeController.class.getSimpleName());

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionService positionService;

    @Autowired
    ParticipationService participationService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listEmployee(Model model) {

        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);

        return "list-employees";
    }

    @GetMapping("/employeeDetails")
    public String employeeDetails(@RequestParam("employeeId") int employeeId, Model model) {

        Employee employee = employeeService.getEmployee(employeeId);

        model.addAttribute("employee", employee);

        List<Participation> participations = participationService.getParticipationsByEmployeeId(employeeId);

        model.addAttribute("participations", participations);

        return "employee-details";
    }

    @GetMapping("/addEmployeeForm")
    public String addEmployeeForm(Model model) {

        Employee employee = new Employee(null, null, null, null, "USER", 0, null, 0, 1, null, new HashSet<>());
        model.addAttribute("employee", employee);

        List<Position> positions = positionService.getPositions();
        model.addAttribute("positions", positions);

        return "add-employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, Model model) {

        if(result.hasErrors()) {
            List<Position> positions = positionService.getPositions();
            model.addAttribute("positions", positions);

            return "add-employee-form";
        }

        try {
            employeeService.saveEmployee(employee);
        } catch(JDBCConnectionException e) {
            result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
        } catch(SQLGrammarException e) {
            result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
        } catch (GenericJDBCException e) {
            List<Position> positions = positionService.getPositions();
            model.addAttribute("positions", positions);

            if(e.getSQLState().equalsIgnoreCase("12345")) {
                result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
            } else {
                result.reject(String.valueOf(e.getErrorCode()),"Niepoprawne dane!");
            }
            return "add-employee-form";
        } catch (HibernateJdbcException e) {
            List<Position> positions = positionService.getPositions();
            model.addAttribute("positions", positions);

            if(e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
                Employee emp = employeeService.getEmployee(employee.getId());
                model.addAttribute("employee", emp);

                model.addAttribute("message", e.getSQLException().getMessage());
            } else {
                model.addAttribute("employee", employee);
                model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
            }
            return "add-employee-form";
        }
        return "redirect:/employee/list";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId, Model model) {

        try {
            employeeService.deleteEmployee(employeeId);
        } catch(JDBCConnectionException e) {
            model.addAttribute("message", "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
        } catch(SQLGrammarException e) {
            model.addAttribute("message", "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
        } catch (JDBCException e) {
            List<Employee> employees = employeeService.getEmployees();

            model.addAttribute("employees", employees);

            if(e.getSQLState().equalsIgnoreCase("12345")) {
                model.addAttribute("message", e.getSQLException().getMessage());
            } else {
                model.addAttribute("message",e.getSQLException().getMessage());
            }
            return "list-employees";
        }

        return "redirect:/employee/list";
    }

    @GetMapping("/updateEmployeeForm")
    public String updateEmployee(@RequestParam("employeeId") int employeeId, Model model) {

        Employee employee = employeeService.getEmployee(employeeId);
        model.addAttribute("employee", employee);

        List<Position> positions = positionService.getPositions();
        model.addAttribute("positions", positions);

        return "add-employee-form";
    }

}
