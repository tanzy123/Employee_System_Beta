package com.beta.service;

import java.text.ParseException;

import com.beta.entity.Application;

public interface VendorApplication {

	public Application generateVendorApplication(Application application) throws ParseException;
	public String validateVendorApplication(Application application);
	 
}
