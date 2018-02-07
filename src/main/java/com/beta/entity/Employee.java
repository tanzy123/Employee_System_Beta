package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee extends UserAccount {

	private String employeeId;
	
	private String employeeEmail;
	
	private String contactNumber;

	private String companyReferenceNumber;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.EMPLOYEE;

	@OneToOne
	@JoinColumn(name="role", referencedColumnName="role")
	private Role role;

	@OneToOne
	@JoinColumn(name="department", referencedColumnName="departmentName")
	private Department department;

	
	
	public Employee(String userName, String password, String employeeId, String employeeEmail, String contactNumber,
			String companyReferenceNumber, Role role, Department department) {
		super(userName, password);
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
		this.contactNumber = contactNumber;
		this.companyReferenceNumber = companyReferenceNumber;
		this.role = role;
		this.department = department;
	}

	public Employee() {}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public LogInType getLogInType() {
		return logInType;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

}
