package com.beta.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.PersistenceContext;

import com.beta.dao.CompanyDao;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministrator;
import com.beta.entity.UserAccount;
import com.beta.services.CompanyService;
import com.beta.services.impl.CompanyServiceImpl;

public class DefaultRegistrationVerificationByEmail implements RegistrationService{

	
	String companyEmail;
	@Override
	public void RegisterCompany(Company company) {
		CompanyService companyService=new CompanyServiceImpl();
		companyService.saveOrUpdate(company);
		companyEmail=company.getCompanyEmail();
		
	}
	@Override
	public boolean SendVarificationEmail(Company company) {
		NotificationService notificationService=new MailNotification();
		String subject="Email Verification";
		Random rand = new Random();
		String token=Integer.toString(rand.nextInt(10000));
		String message="This is your OTP : "+token;
		UserAccount userAccount= new UserAccount();
		userAccount.setToken(token);
		try 
		{
			notificationService.sendMail(company.getCompanyEmail(), null, subject, message);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return TokenComparison(tokenFromUI);
	

}

	public boolean TokenComparison(String token)
	{
		UserAccount userAccount=new UserAccount();
		CompanyService companyService=new CompanyServiceImpl();
		Map<String,Object> params = new HashMap<>();
		params.put("companyEmail", companyEmail);
		
		
		CompanyAdministrator companyadministorToken=(CompanyAdministrator) companyService.findByNamedQueryAndNamedParams("findToken", params);

		//select p.token from company c join c.CompanyAdministor p where companyEmail=:companyEmail
		CompanyAdministrator companyAdmin=new CompanyAdministrator();
		
		
		
		if(token==companyadministorToken.getToken) return true;
		else return false;
	}


		
	}