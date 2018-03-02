package com.beta.service;

import java.text.ParseException;

import com.beta.entity.Application;
import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;

public interface RegistrationAndApplication {
	public void registerCompany(Company company) throws VendorMgmtException;
	public String applyVendorship(Application app) throws VendorMgmtException, ParseException;
}
