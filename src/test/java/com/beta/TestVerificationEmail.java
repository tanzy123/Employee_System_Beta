package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Company;
import com.beta.service.DefaultRegistrationVerificationByEmail;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestVerificationEmail {

	@Test
	public void testVerificationEmail()
	{
		DefaultRegistrationVerificationByEmail verificationEmail= new DefaultRegistrationVerificationByEmail();
	
		
		Company company =new Company();
		company.setCompanyReferenceNumber("CTS");
		company.setCompanyName("Cognizant");
		company.setCompanyAddress("expo");
		company.setCompanyEmail("cognizant@cognizant.com");
		company.setContactNumber("61234567");
		company.setCompanyWebsite("www.cognizant.com");
		company.setTurnover(9999999L);
		verificationEmail.SendVarificationEmail(company);
	}
	
}
