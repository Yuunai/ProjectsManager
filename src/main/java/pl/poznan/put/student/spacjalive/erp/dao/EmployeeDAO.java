package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> getEmployees();

    public List<Employee> getEmployees(boolean enabled);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(int id);

    public Employee getEmployee(int id);
}
