package com.beta.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="EmployeeAccount.findByUsername",
                query="SELECT e FROM EmployeeAccount e WHERE e.userName = :userName"),
                @NamedQuery(name="EmployeeAccount.checkEmpIdDuplicate",
                query="SELECT e FROM EmployeeAccount e WHERE e.companyReferenceNumber = :companyReferenceNumber and e.employeeId = :employeeId"),
})
public class EmployeeAccount extends UserAccount {

	private String employeeId;
	
	private String employeeEmail;
	
	private String contactNumber;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.EMPLOYEE;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="roleId", referencedColumnName="roleId")
	private Role role;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="departmentId", referencedColumnName="departmentId")
	private Department department;

	public EmployeeAccount(String userName, String password, String employeeId, String employeeEmail, String contactNumber,
			String companyReferenceNumber, Role role, Department department) {
		super(userName, password, companyReferenceNumber);
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
		this.contactNumber = contactNumber;
		this.role = role;
		this.department = department;
	}

	public EmployeeAccount() {}

	public EmployeeAccount(String userName, String password, String companyReferenceNumber) {
		super(userName, password, companyReferenceNumber);
	}

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

}
