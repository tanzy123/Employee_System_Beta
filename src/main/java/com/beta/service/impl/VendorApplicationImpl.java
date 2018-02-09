package com.beta.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Category;
import com.beta.exception.VendorMgmtException;
import com.beta.service.VendorApplication;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;

@Service("VendorApplication")

@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class VendorApplicationImpl implements VendorApplication {

	private static final ApplicationStatus Pending = null;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ApplicationService appservice; 
	
	@Autowired
	CompanyService companyService;
	
	@Override
	public Application generateVendorApplication(Application application) throws ParseException {
		// There are some fields not inserted by vendor or staff, hence this method will generate the rest
		String uniqueKey = "";
		do {
		uniqueKey = UUID.randomUUID().toString();
		} while (appservice.findByApplicationRefNo(uniqueKey)!=null);
		
		Date applicationDate = new Date();
		Date modifiedDate = new Date();
		application.setApplicationRef(uniqueKey);
		application.setApplicationStatus(ApplicationStatus.PENDING);
		application.setApplicationDate(applicationDate);
		application.setModifiedDate(modifiedDate);
		
		return application;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String validateVendorApplication(Application application) {
		
		List <Category> category = categoryService.findAll();
		
		String vendorRef = application.getVendorReferenceNumber();
		String companyRef = application.getCompanyReferenceNumber();
		
		Category vendorCategory = application.getCategory();
		String poc = application.getPOC();
		Long vendorPeriod = application.getVendorPeriod();
		
		try {
			if (companyRef.equals(null)||vendorCategory.equals(null)||poc.equals(null)||vendorPeriod.equals(null)||vendorRef.equals(null)) {
			return "MANDATORY FIELDS ARE NOT ALL FILLED UP";
		}else {
				if (vendorRef.equals(companyRef)) {
					return "VENDOR REFERENCE NUMBER CANNOT BE THE SAME AS COMPANY REFERENCE NUMBER";
				}
			
				else {
				for (Category c: category) {
				if (vendorCategory.getCategoryName().equals(c.getCategoryName()) && vendorCategory.getCompanyReferenceNumber().equals(application.getCompanyReferenceNumber())) { 
					appservice.saveOrUpdate(application); //why cannot saveOrUpdate????
					return "APPLICATION UPLOADED SUCCESSFULLY";
				}
				}
				return "VENDOR CATEGORY DO NOT FALL INTO COMPANY'S REQUESTED CATEGORY LIST";
			}
		}
		}catch (NullPointerException e) {
			return "MANDATORY FIELDS ARE NOT ALL FILLED UP";
		}
	} 
}
