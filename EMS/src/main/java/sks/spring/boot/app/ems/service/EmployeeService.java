package sks.spring.boot.app.ems.service;

import java.util.Collection;

import sks.spring.boot.app.ems.model.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee emp);
	public Collection<Employee> getAllEmployee();
	public void removeEmployee(String empId);
	public Employee getEmployee(String empId);
	public void updateEmployee(Employee emp);

}
