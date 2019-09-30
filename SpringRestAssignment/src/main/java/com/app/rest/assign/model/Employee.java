package com.app.rest.assign.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_Employee")
@XmlRootElement
//@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlAccessorType(value = XmlAccessType.NONE)
public class Employee extends ResourceSupport implements Serializable{
	
	private static final long serialVersionUID = 99L;
	private Integer employeeId;
	private String employeeName;
	private String employeeEmail;
	private String employeeDesignation;
	private String employeeContactNumber;
	private String employeeLocation;
	@JsonBackReference
	@XmlTransient
	private Department department;

	public Employee() {
		System.out.println("Constructor Employee.Employee()");
	}

	public Employee(String employeeName, String employeeEmail, String employeeDesignation, String employeeContactNumber,
			String employeeLocation) {
		super();
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeDesignation = employeeDesignation;
		this.employeeContactNumber = employeeContactNumber;
		this.employeeLocation = employeeLocation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Employee_Id")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeeId) {
		this.employeeId = employeeeId;
	}

	@Column(name = "Employee_Name")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "Employee_Email", unique = true)
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	@Column(name = "Employee_Designation")
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	@Column(name = "Employee_Contact", unique = true)
	public String getEmployeeContactNumber() {
		return employeeContactNumber;
	}

	public void setEmployeeContactNumber(String employeeContactNumber) {
		this.employeeContactNumber = employeeContactNumber;
	}

	@Column(name = "Employee_Location")
	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employeeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeDesignation=" + employeeDesignation + ", employeeContactNumber="
				+ employeeContactNumber + ", employeeLocation=" + employeeLocation + "]";
	}

}
