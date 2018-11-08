package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Employee;

import java.util.List;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	
	public List<Employee> getEmployees(boolean active);
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(int id);
	
	public Employee getEmployee(int id);
}
