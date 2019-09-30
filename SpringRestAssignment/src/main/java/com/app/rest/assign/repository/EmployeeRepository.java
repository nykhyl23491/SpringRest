package com.app.rest.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.rest.assign.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "from Employee e where e.department.departmentId=:departmentId")
	public List<Employee> findEmployeesInDepartment(int departmentId);
	
}
