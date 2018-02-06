package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee extends UserAccount {

	@Id
	private Long employeeId;

	private Long companyId;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.EMPLOYEE;

	@OneToOne
	@JoinColumn(name = "role")
	private Role role;

	@OneToOne
	@JoinColumn(name = "departmentName")
	private Department department;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public LogInType getLogInType() {
		return logInType;
	}

}
