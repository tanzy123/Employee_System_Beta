package com.beta.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmailPurposeType;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.CompanyService;

@Service("DefaultRegistrationVerificationByEmail")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DefaultRegistrationVerificationByEmail implements RegistrationService {

	@Autowired
	CompanyService companyService;
	@Autowired
	NotificationService notificationService;

	@Autowired
	CompanyAdminstratorAccountService accountService;

	String companyEmail;

	@Override
	public void registerCompanyAccount(Company company, String userName, String password) {

		CompanyAdministratorAccount companyAccount = new CompanyAdministratorAccount(userName, password,
				company.getCompanyReferenceNumber());
		accountService.createNewAccount(companyAccount);
		companyEmail = company.getCompanyEmail();

	}

	@Override
	public boolean sendVerificationEmail(Company company, String userName) {

		String subject = "Email Verification";
		Random rand = new Random();
		String token = String.format("%04d", rand.nextInt(10000));
		String message = "This is your OTP : " + token;
		CompanyAdministratorAccount account = accountService.findByUserName(userName);
		account.setToken(token);
		account.setIsValidated(false);
		String cc[] = {};
		String vetter = "";
		try {
			notificationService.sendEmailWithPurposeCC(company.getCompanyEmail(), cc, subject, message, vetter,
					EmailPurposeType.CompanyRegistrationEmailVerification);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
		// return TokenComparison(tokenFromUI);

	}

	@Override
	public boolean tokenComparison(String token, String username) {

		CompanyAdministratorAccount account = accountService.findByUserName(username);
		if (token.equals(account.getToken())) {
			CompanyAdministratorAccount validatedAccount = accountService.findByUserName(username);
			validatedAccount.setIsValidated(true);
			return true;
		}
		else
			return false;
	}

}