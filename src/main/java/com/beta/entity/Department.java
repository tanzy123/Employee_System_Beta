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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyReferenceNumber == null) ? 0 : companyReferenceNumber.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
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
		Department other = (Department) obj;
		if (companyReferenceNumber == null) {
			if (other.companyReferenceNumber != null)
				return false;
		} else if (!companyReferenceNumber.equals(other.companyReferenceNumber))
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		return true;
	}
	
	
}
