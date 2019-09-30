package com.app.rest.assign.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rest.assign.model.Department;
import com.app.rest.assign.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(Integer departmentId) {
		return departmentRepository.findById(departmentId).get();
	}

}
