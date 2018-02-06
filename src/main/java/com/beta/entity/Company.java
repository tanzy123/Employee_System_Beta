package com.beta.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long companyPrimaryId;

	private Long companyReferenceNumber;

	private String companyName;

	private String companyAddress;

	@Column(nullable = false, unique = true)
	private String companyEmail;

	private String contactNumber;

	private String companyWebsite;

	private Long turnover;

	@ManyToMany
	@JoinTable(name = "COMPANY_VENDOR", joinColumns = { @JoinColumn(name = "companyEmail") }, inverseJoinColumns = {
			@JoinColumn(name = "vendorEmail") })
	private List<Company> vendors;

	@ManyToMany(mappedBy = "vendors")
	private List<Company> clients;

	@OneToMany
	@JoinColumn(name = "applicationRef")
	private List<Application> applications;

	@OneToMany
	@JoinColumn(name = "role")
	private List<Role> roles;

	@OneToMany
	@JoinColumn(name = "departmentName")
	private List<Department> department;

	@OneToMany
	@JoinColumn(name = "employeeId")
	private List<Employee> employees;

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public Long getTurnover() {
		return turnover;
	}

	public void setTurnover(Long turnover) {
		this.turnover = turnover;
	}

	public List<Company> getVendors() {
		return vendors;
	}

	public void setVendors(List<Company> vendors) {
		this.vendors = vendors;
	}

	public List<Company> getClients() {
		return clients;
	}

	public void setClients(List<Company> clients) {
		this.clients = clients;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	public Long getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(Long companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
