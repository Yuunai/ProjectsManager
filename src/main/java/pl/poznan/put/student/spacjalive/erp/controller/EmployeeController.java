package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list")
    public String listEmployee(Model model) {

        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);

//        TODO add employees list page
        return null;
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

//        TODO add addEmployeeForm
        return null;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.saveEmployee(employee);

//        TODO return employeeList page
        return null;
    }

}



//biuro@datasystem.pl Ci od pracy

//znać do egzaminu helmana, RSA, założenia do Ergamala
//plecakowy