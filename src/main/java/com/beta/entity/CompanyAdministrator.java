package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class CompanyAdministrator extends UserAccount{

	private String companyReferenceNumber;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.COMPANY_ADMINISTRATOR;

	public LogInType getLogInType() {
		return logInType;
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

}
