package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CompanyAdministrator extends UserAccount{

	@Id
	@GeneratedValue
	private Long adminId;

	@OneToOne
	@JoinColumn(name = "companyEmail")
	private Company company;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.COMPANY_ADMINISTRATOR;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public LogInType getLogInType() {
		return logInType;
	}

}
