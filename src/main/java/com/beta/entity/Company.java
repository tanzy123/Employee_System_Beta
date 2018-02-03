package com.beta.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private Long companyId;
	
	private Long registerationNumber;
	
	private UserLogin userLogin;

	private String companyName;

	private String companyAddress;
	
	private String email;

	private String contactNumber;
	
	private String companyWebsite;
	
	private Long turnover;
	
	@OneToMany
	private List<Company> vendors;
	
	@OneToMany
	private List<Company> clients;
	
	@OneToMany
	private List<Application> applications;
	
	@ManyToMany
	private List<Role> roles;
	
	@ManyToMany
	private List<Department> department;
	
	@ManyToMany
	private List<ApprovalStatus> approvalStatus;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
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

	public Long getRegisterationNumber() {
		return registerationNumber;
	}

	public void setRegisterationNumber(Long registerationNumber) {
		this.registerationNumber = registerationNumber;
	}
}
