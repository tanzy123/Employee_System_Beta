package com.beta.service;


import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;


public interface CompanyValidation {
	public void validateCommpanyApplication(Company company) throws VendorMgmtException;
}
