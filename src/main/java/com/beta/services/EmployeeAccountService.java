package com.beta.services;

import java.util.List;

import com.beta.entity.EmployeeAccount;
import com.beta.exception.VendorMgmtException;

public interface EmployeeAccountService extends BaseService<Long, EmployeeAccount> {

	void createNewAccount(EmployeeAccount userAccount);

	void updatePassword(EmployeeAccount userAccount, String updatedPassword);
	
	EmployeeAccount findByUserName(String userName);
	
	EmployeeAccount validateAccount(EmployeeAccount entity);
	
	List<EmployeeAccount> checkDuplicateEmployeeIdInSameCompany(String companyReferencenumber, String employeeId);
	
	EmployeeAccount saveOrUpdate2(EmployeeAccount entity) throws VendorMgmtException;
	
	void saveOrUpdateByCompAdmin(EmployeeAccount entity) throws VendorMgmtException;
}
