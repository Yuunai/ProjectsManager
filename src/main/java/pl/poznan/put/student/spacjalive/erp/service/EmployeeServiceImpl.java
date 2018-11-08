package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.EmployeeDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}
	
	@Override
	public List<Employee> getEmployees(boolean active) {
		return employeeDAO.getEmployees(active);
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);
	}
	
	@Override
	public void deleteEmployee(int id) {
		employeeDAO.deleteEmployee(id);
	}
	
	@Override
	public Employee getEmployee(int id) {
		return employeeDAO.getEmployee(id);
	}
}
