package com.app.rest.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.assign.model.Salary;
import com.app.rest.assign.service.SalaryService;

@RestController
@RequestMapping(value = "/assignment/salary")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
	
	@GetMapping(value = "/emp/{employeeId}")
	public ResponseEntity<Salary> getSalaryOfEmployee(@PathVariable Integer employeeId)
	{
		return new ResponseEntity<Salary>(salaryService.getSalaryOfEmployee(employeeId),HttpStatus.OK);
	}
}
