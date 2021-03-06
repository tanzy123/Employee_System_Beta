package com.beta.orm.service;

import java.util.List;

import com.beta.entity.EmployeeAccount;
import com.beta.exception.VendorMgmtException;

public interface EmployeeAccountService extends BaseService<Long, EmployeeAccount> {

	void createNewAccount(EmployeeAccount userAccount);

	void updatePassword(EmployeeAccount userAccount, String updatedPassword);
	
	EmployeeAccount findByUserName(String userName);
	
	EmployeeAccount validateAccount(EmployeeAccount entity);
	
	List<EmployeeAccount> checkDuplicateEmployeeIdInSameCompany(String companyReferencenumber, String employeeId);
	

	public List<EmployeeAccount> findByEmpNameAndCompany(String companyReferencenumber, String employeeName);

	public List<EmployeeAccount> findByEmpId(String employeeId);
	
	void saveOrUpdateByCompAdmin(EmployeeAccount entity) throws VendorMgmtException;
	
	void deleteByUserName(String userName);

}
