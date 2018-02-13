package com.beta.service;

import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.UserAccount;

public interface RegistrationService {

	void RegisterCompany(Company company);
	
	boolean SendVarificationEmail (Company company);

	public boolean TokenComparison(String token);
	
	
	
}
