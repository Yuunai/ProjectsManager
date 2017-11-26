package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Position;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionService positionService;

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

//        TODO add employee details page
        return null;
    }

    @GetMapping("/addEmployeeForm")
    public String addEmployeeForm(Model model) {

        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        List<Position> positions = positionService.getPositions();

        model.addAttribute("positions", positions);

        return "add-employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/employee/list";
    }

}



//biuro@datasystem.pl Ci od pracy

//znać do egzaminu helmana, RSA, założenia do Ergamala
//plecakowy