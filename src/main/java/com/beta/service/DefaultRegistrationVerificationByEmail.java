package com.beta.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.beta.PurposeType;
import com.beta.dao.CompanyDao;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.UserAccount;
import com.beta.services.CompanyService;
import com.beta.services.impl.CompanyServiceImpl;

public class DefaultRegistrationVerificationByEmail implements RegistrationService{

	@Autowired
	CompanyService companyService;
	@Autowired
	NotificationService notificationService;
	
	String companyEmail;
	@Override
	public void RegisterCompany(Company company) {
		
		companyService.saveOrUpdate(company);
		companyEmail=company.getCompanyEmail();
		
	}
	@Override
	public boolean SendVarificationEmail(Company company) {
		
		String subject="Email Verification";
		Random rand = new Random();
		String token=String.format("%04d", rand.nextInt(10000));
		String message="This is your OTP : "+token;
		UserAccount userAccount= new UserAccount();
		userAccount.setToken(token);
		String cc[]={};
		String vetter="";
		try 
		{
			notificationService.sendEmailWithPurposeCC(company.getCompanyEmail(), cc, subject, message,vetter, PurposeType.CompanyRegistrationEmailVerification);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
//		return TokenComparison(tokenFromUI);
	

}

	public boolean TokenComparison(String token)
	{
		UserAccount userAccount=new UserAccount();
		
		Map<String,Object> params = new HashMap<>();
		params.put("companyEmail", companyEmail);
		
		
		CompanyAdministratorAccount companyadministorToken=(CompanyAdministratorAccount) companyService.findByNamedQueryAndNamedParams("findToken", params);

		//select p.token from company c join c.CompanyAdministor p where companyEmail=:companyEmail
		CompanyAdministratorAccount companyAdmin=new CompanyAdministratorAccount();
		
		//@NamedQuery(name="findToken", query="select p.token from company c join c.CompanyAdministor p where companyEmail=:companyEmail") 
		
		
		
		if(token==companyadministorToken.getToken()) return true;
		else return false;
	}


		
	}