package com.beta.service.impl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Company;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;

@Service("CompanyValidation")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class CompanyValidationImpl implements CompanyValidation {

	@Override
	public void validateCommpanyApplication(Company company) throws UserException {

		try {
			if (company.getCompanyName().equals(null) || company.getCompanyAddress().equals(null)
					|| company.getCompanyEmail().equals(null) || company.getCompanyEmail().equals(null)
					|| company.getContactNumber().equals(null) || company.getTurnover().equals(null)
					|| company.getRoles().equals(null) || company.getDepartment().equals(null)
					|| company.getCategory().equals(null)) {
				throw new UserException("MANDATORY FIELDS ARE NOT ALL FILLED UP");
			} else {
				try {
					InternetAddress emailAddr = new InternetAddress(company.getCompanyEmail());
					emailAddr.validate();
				} catch (AddressException ex) {
					throw new UserException("INVALID EMAIL ADDRESS GIVEN");
				}

				if (company.getTurnover() < 0) {
					throw new UserException("TURNOVER CANNOT BE NEGATIVE");
				}
			}
		} catch (NullPointerException e) {
			throw new UserException("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		}

	}

}
