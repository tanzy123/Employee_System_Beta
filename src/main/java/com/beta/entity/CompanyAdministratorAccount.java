package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="CompanyAdministrator.findByUsername",
                query="SELECT c FROM CompanyAdministratorAccount c WHERE c.userName = :userName"),
})
public class CompanyAdministratorAccount extends UserAccount{

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.COMPANY_ADMINISTRATOR;

	public LogInType getLogInType() {
		return logInType;
	}
	
	public CompanyAdministratorAccount() {}

	public CompanyAdministratorAccount(String userName, String password, String companyReferenceNumber) {
		super(userName, password, companyReferenceNumber);
	}

}
