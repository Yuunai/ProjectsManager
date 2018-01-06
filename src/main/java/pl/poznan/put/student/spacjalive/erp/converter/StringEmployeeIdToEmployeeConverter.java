package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.service.EmployeeService;

public class StringEmployeeIdToEmployeeConverter implements Converter<String, Employee> {

    private final EmployeeService employeeService;

    public StringEmployeeIdToEmployeeConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee convert(String s) {
        Employee employee;
        employee = employeeService.getEmployee(Integer.valueOf(s));

        return employee;
    }
}
