package com.app.rest.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.assign.model.Department;
import com.app.rest.assign.service.DepartmentService;

@RestController
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping(value = "/assignment/department", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
			"application/vnd.yash.api.v1+xml" }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
					"application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department deptResponse = departmentService.createDepartment(department);
		return new ResponseEntity<>(deptResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/assignment/department/{departmentId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Department> fetchDepartmnet(@PathVariable Integer departmentId) {
		Department deptResponse = departmentService.getDepartmentById(departmentId);
		return new ResponseEntity<>(deptResponse, HttpStatus.OK);
	}
}
