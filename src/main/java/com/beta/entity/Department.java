package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Department.findByNameAndRefNo",
                query="SELECT d FROM Department d where d.companyReferenceNumber = :companyReferenceNumber"
                		+ " AND d.departmentName = :departmentName"),
})
public class Department {

	@Id
	@GeneratedValue
	private Long departmentId;
	
	private String departmentName;
	
	private String companyReferenceNumber;

	
	public Department() {
		super();
	}

	public Department(String departmentName) {
		super();
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}
}
