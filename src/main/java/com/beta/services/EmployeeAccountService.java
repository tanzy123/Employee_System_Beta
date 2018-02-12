package com.beta.services;

import com.beta.entity.EmployeeAccount;

public interface EmployeeAccountService extends BaseService<Long, EmployeeAccount> {

	void createNewAccount(EmployeeAccount userAccount);

	void updatePassword(EmployeeAccount userAccount, String updatedPassword);
	
	EmployeeAccount findByUserName(String userName);
	
	EmployeeAccount validateAccount(EmployeeAccount entity);
}
