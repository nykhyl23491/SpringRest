package com.app.rest.assign.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rest.assign.model.Employee;
import com.app.rest.assign.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee enrollEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		if (empOptional.isPresent())
			return empOptional.get();
		else
			return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmpolyeeInDepartment(Integer departmentId) {
		return employeeRepository.findEmployeesInDepartment(departmentId);

	}

	@Override
	public Employee updateEmployee(Employee employee, Integer employeeId) {
		Employee employeeToUpdate = employeeRepository.getOne(employeeId);
		employeeToUpdate.setEmployeeEmail(employee.getEmployeeEmail());
		employeeToUpdate.setEmployeeDesignation(employee.getEmployeeDesignation());
		employeeToUpdate.setEmployeeLocation(employee.getEmployeeLocation());
		employeeToUpdate.setEmployeeContactNumber(employee.getEmployeeContactNumber());
		return employeeRepository.save(employeeToUpdate);
	}

	@Override
	public Employee updateEmployeeLocation(String location, Integer employeeId) {
		Employee employeeToUpdate = employeeRepository.getOne(employeeId);
		employeeToUpdate.setEmployeeLocation(location);
		return employeeRepository.save(employeeToUpdate);
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
