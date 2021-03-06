package com.beta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Company.findByName",
             query="SELECT c FROM Company c where c.companyName = :companyName"),
    @NamedQuery(name="Company.findByNameAndRefNo",
                query="SELECT c FROM Company c where c.companyReferenceNumber = :companyReferenceNumber"
                		+ " AND c.companyName = :companyName"),
    @NamedQuery(name="Company.findByRefNo",
    			query="SELECT c FROM Company c where c.companyReferenceNumber = :companyReferenceNumber"),
})
public class Company implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4683198518681773609L;

	@Id
	@GeneratedValue
	private Long companyPrimaryId;

	@Column(nullable = false, unique = true)
	private String companyReferenceNumber;

	@Column(nullable = false)
	private String companyName;

	@Column(nullable = false)
	private String companyAddress;

	@Column(nullable = false, unique = true)
	private String companyEmail;

	@Column(nullable = false)
	private String contactNumber;

	private String companyWebsite;

	@Column(nullable = false)
	private Long turnover;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<Application> applications;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<Department> department;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<EmployeeAccount> employees;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<Category> category;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="companyReferenceNumber", referencedColumnName="companyReferenceNumber")
	private List<CompanyAdministratorAccount> companyAdministrator;

	public Company(String companyReferenceNumber, String companyName, String companyAddress, String companyEmail,
			String contactNumber, String companyWebsite, Long turnover,
			List<Application> applications, List<Role> roles, List<Department> department,
			List<Category> category) {
		super();
		this.companyReferenceNumber = companyReferenceNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyEmail = companyEmail;
		this.contactNumber = contactNumber;
		this.companyWebsite = companyWebsite;
		this.turnover = turnover;
		this.applications = applications;
		this.roles = roles;
		this.department = department;
		this.category = category;
	}
	

	public Company(String companyReferenceNumber, String companyName, String companyAddress, String companyEmail,
			String contactNumber, String companyWebsite, Long turnover, List<Role> roles, List<Department> department,
			List<Category> category) {
		super();
		this.companyReferenceNumber = companyReferenceNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyEmail = companyEmail;
		this.contactNumber = contactNumber;
		this.companyWebsite = companyWebsite;
		this.turnover = turnover;
		this.roles = roles;
		this.department = department;
		this.category = category;
	}


	public Company() {
		super();
	}

	public Long getCompanyPrimaryId() {
		return companyPrimaryId;
	}

	public void setCompanyPrimaryId(Long companyPrimaryId) {
		this.companyPrimaryId = companyPrimaryId;
	}

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

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
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

	public List<EmployeeAccount> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeAccount> employees) {
		this.employees = employees;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}


	public List<CompanyAdministratorAccount> getCompanyAdministrator() {
		return companyAdministrator;
	}


	public void setCompanyAdministrator(List<CompanyAdministratorAccount> companyAdministrator) {
		this.companyAdministrator = companyAdministrator;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyReferenceNumber == null) ? 0 : companyReferenceNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyReferenceNumber == null) {
			if (other.companyReferenceNumber != null)
				return false;
		} else if (!companyReferenceNumber.equals(other.companyReferenceNumber))
			return false;
		return true;
	}
	
	
}
