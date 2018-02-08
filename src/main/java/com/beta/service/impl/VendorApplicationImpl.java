package com.beta.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.richfaces.component.DataAdaptorIterationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.JPADAO;
import com.beta.dao.impl.JpaDAOImpl;
import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;
import com.beta.service.VendorApplication;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;
import com.beta.services.impl.ApplicationServiceImpl;
import com.beta.services.impl.CategoryServiceImpl;

@Service("VendorApplication")

@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class VendorApplicationImpl implements VendorApplication {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ApplicationService appservice; 
	
	@Autowired
	CompanyService companyService;
	
	@Override
	public Application generateVendorApplication(Application application) throws ParseException {
		// There are some fields not inserted by vendor or staff, hence this method will generate the rest
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date applicationDate = sdf.parse(sdf.format(new Date()));
		Date modifiedDate = sdf.parse(sdf.format(new Date()));
		
		application.setCurrentStatus("Pending");
		application.setApprovalStatus("Pending");
		application.setApplicationDate(applicationDate);
		application.setApplicationDate(modifiedDate);
		
		return application;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String validateVendorApplication(Application application) {
		
		List <Category> category = categoryService.findAll();
		
		Long companyId = application.getCompanyId();
		Long vendorId = application.getVendorId();
		Category vendorCategory = application.getCategory();
		String poc = application.getPOC();
		Long vendorPeriod = application.getVendorPeriod();
		// default mandatory fields are company, category, POC, period
		
		try {
		if (companyId.equals(0L)||vendorCategory.equals(null)||poc.equals(" ")||vendorPeriod.equals(0L)||vendorId.equals(0L)) {
			return "MANDATORY FIELDS ARE NOT ALL FILLED UP";
		}else {
			for (Category c: category) {
				if (vendorCategory.getCategoryName().equals(c.getCategoryName())) { 
					appservice.saveOrUpdate(application); //why cannot saveOrUpdate????
					return "APPLICATION UPLOADED SUCCESSFULLY";
				}
			}
			
		}
		}catch (NullPointerException e) {
			return "MANDATORY FIELDS ARE NOT ALL FILLED UP";
		}
		return "APPLICATION SUBMISSION FAILED";
	}
}
