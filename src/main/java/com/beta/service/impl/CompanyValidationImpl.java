package com.beta.service.impl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;

@Service("CompanyValidation")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class CompanyValidationImpl implements CompanyValidation {

	@Autowired
	CompanyService companyservice;
	
	@Autowired
	CategoryService catServ;
	
	@Override
	public void validateCommpanyApplication(Company company) throws VendorMgmtException {
		
		try {
			if (company.getCompanyName().equals(null)||company.getCompanyAddress().equals(null)||company.getCompanyEmail().equals(null)||
					company.getCompanyEmail().equals(null)|| company.getContactNumber().equals(null)||company.getCompanyWebsite().equals(null)||company.getTurnover().equals(null)
					||company.getRoles().equals(null)||company.getDepartment().equals(null)||company.getCategory().equals(null)) {
			throw new VendorMgmtException("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		}else {
			   boolean result = true;
			   try {
			      InternetAddress emailAddr = new InternetAddress(company.getCompanyEmail());
			      emailAddr.validate();
			   } catch (AddressException ex) {
			      result = false;
			   }
				if (result==false) {
					throw new VendorMgmtException("INVALID EMAIL ADDRESS GIVEN");
				}
			
				else if (company.getTurnover()<0) {
					throw new VendorMgmtException("TURNOVER CANNOT BE NEGATIVE");
				}
				else {
					for (Category c : company.getCategory()) {
					catServ.saveOrUpdate(c);}
					
					companyservice.saveOrUpdate(company);
					System.out.println("APPLICATION SUCCESS");
					
				}
		}
		}catch (NullPointerException e) {
			throw new VendorMgmtException("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		}
	
	}

}
