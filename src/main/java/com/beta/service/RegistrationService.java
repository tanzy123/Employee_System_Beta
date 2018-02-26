package com.beta.service;

import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.UserAccount;

public interface RegistrationService {
	
	void registerCompanyAccount(Company company, String userName, String password);
	
	boolean sendVerificationEmail(Company company, String userName);

	boolean tokenComparison(String token, String username);

	
	
	
	
}
