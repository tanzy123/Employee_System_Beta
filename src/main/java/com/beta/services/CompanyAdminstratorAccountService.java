package com.beta.services;

import com.beta.entity.CompanyAdministratorAccount;

public interface CompanyAdminstratorAccountService extends BaseService<Long, CompanyAdministratorAccount>
{
	void createNewAccount(CompanyAdministratorAccount userAccount);

	void updatePassword(CompanyAdministratorAccount userAccount, String updatedPassword);
	
	CompanyAdministratorAccount findByUserName(String userName);
	
	CompanyAdministratorAccount validateAccount(CompanyAdministratorAccount entity);
	
}
