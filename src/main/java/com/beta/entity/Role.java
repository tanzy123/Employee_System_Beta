package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long roleId;
	
	private String role;
	
	private String companyReferenceNumber;

	public Role() {
		super();
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}
	
	
}
