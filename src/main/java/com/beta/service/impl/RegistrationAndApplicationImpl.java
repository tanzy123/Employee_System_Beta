package com.beta.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Application;
import com.beta.entity.Company;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.service.RegistrationAndApplication;
import com.beta.service.VendorApplication;

@Service("RegistrationAndApplication")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class RegistrationAndApplicationImpl implements RegistrationAndApplication {

	@Autowired
	CompanyValidation comValid;
	
	@Autowired
	VendorApplication venApp;
	
	@Override
	public void registerCompany(Company company) throws UserException {
		comValid.validateCommpanyApplication(company);
	}
	
	@Override
	public String applyVendorShip(Application app) throws VendorMgmtException, ParseException {
		Application completeApplication=venApp.generateVendorApplication(app); 
		// The above step gives the completed application with the autogenrated fields
		
		String appRef = completeApplication.getApplicationRef();
		
		venApp.validateVendorApplication(completeApplication);
		// The comepleted application is sent to the above step to validate and submit
		
		return appRef;
	}
	
}
