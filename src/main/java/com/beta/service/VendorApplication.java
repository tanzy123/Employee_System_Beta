package com.beta.service;

import java.text.ParseException;

import com.beta.entity.Application;
import com.beta.exception.VendorMgmtException;

public interface VendorApplication {

	public Application generateVendorApplication(Application application) throws ParseException;
	public void validateVendorApplication(Application application) throws VendorMgmtException;
	 
}
