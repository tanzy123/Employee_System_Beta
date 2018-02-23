package com.beta.service;

import java.text.ParseException;

import com.beta.entity.Application;
import com.beta.exception.VendorMgmtException;

public interface VendorApplication {

	public Application generateVendorApplication(Application application);
	public void validateVendorApplication(Application application) throws VendorMgmtException;
	 
}
