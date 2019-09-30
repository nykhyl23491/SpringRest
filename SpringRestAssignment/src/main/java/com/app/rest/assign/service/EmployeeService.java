package com.app.rest.assign.service;

import java.util.List;

import com.app.rest.assign.model.Employee;

public interface EmployeeService {

	public Employee enrollEmployee(Employee employee);

	public Employee getEmployeeById(Integer employeeId);

	public List<Employee> getAllEmployee();

	public List<Employee> getEmpolyeeInDepartment(Integer departmentId);

	public Employee updateEmployee(Employee employee, Integer employeeId);

	public Employee updateEmployeeLocation(String location, Integer employeeId);

	public void deleteEmployee(Integer employeeId);

}
