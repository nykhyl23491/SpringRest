package com.app.rest.assign.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.assign.exception.EmployeeNotFoundException;
import com.app.rest.assign.holder.EmployeeDetailSHolder;
import com.app.rest.assign.model.Employee;
import com.app.rest.assign.service.EmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/assignment/employee/{employeeId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public Resource<Employee> getEmployeeById(@PathVariable Integer employeeId) {
		Employee employeeResponse = employeeService.getEmployeeById(employeeId);
		if (employeeResponse == null)
			throw new EmployeeNotFoundException("Employee Not Found");
		Resource<Employee> employeeResource = new Resource<>(employeeResponse);
		Link linkTo = linkTo(methodOn(this.getClass()).getAllEmployees()).withSelfRel();
		employeeResource.add(linkTo.withRel("all-employees"));
		return employeeResource;
	}

	@PostMapping(value = "/assignment/employee", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
			"application/vnd.yash.api.v1+xml" }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
					"application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Employee> enrollEmployee(@RequestBody Employee employee) {
		Employee employeeResponse = employeeService.enrollEmployee(employee);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/assignment/employee", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<List<Employee>> getAllEmployees() {
		// EmployeeDetailSHolder empHolder = new EmployeeDetailSHolder();
		// empHolder.setEmployees();
		return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@GetMapping(value = "/assignment/employee/dept/{departmentId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<EmployeeDetailSHolder> fetchEmployeesFromDepartment(@PathVariable Integer departmentId) {
		EmployeeDetailSHolder empHolder = new EmployeeDetailSHolder();
		List<Employee> employees = employeeService.getEmpolyeeInDepartment(departmentId);
		if (employees.isEmpty()) {
			System.out.println("----------Inside if block");
			throw new EmployeeNotFoundException("Employee Not Present In Department");
		} else {
			System.out.println("----------Inside else block");
			empHolder.setEmployees(employees);
			return new ResponseEntity<>(empHolder, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/assignment/employee/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
		Employee employeeResponse = employeeService.updateEmployee(employee, employeeId);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/assignment/employee/{employeeId}")
	public HttpStatus updateEmployeeLocation(@PathVariable Integer employeeId, @RequestParam String location) {
		employeeService.updateEmployeeLocation(location, employeeId);
		return HttpStatus.OK;
	}

	@DeleteMapping(value = "/assignment/employee/{employeeId}")
	public HttpStatus deleteEmployee(@PathVariable Integer employeeId) {
		employeeService.deleteEmployee(employeeId);
		return HttpStatus.NO_CONTENT;
	}

}
