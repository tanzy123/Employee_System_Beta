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
		
		verificationEmail.SendVarificationEmail(Company company);
	}
	
}
