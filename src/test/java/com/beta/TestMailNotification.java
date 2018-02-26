package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.EmailPurposeType;
import com.beta.service.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestMailNotification {
	@Autowired
	NotificationService notificationService;
	
	@Test
	public void testSendEmailWithCC() throws Exception{
		String cc[] = {"Koh.KahAikSebastian@cognizant.com", "Tatsuro.Ninomiya@cognizant.com","JianMingTimothy.Tan@cognizant.com","Jermayne.Chong@cognizant.com","HongYun.Pang@cognizant.com","TsuKian.Ng@cognizant.com","Ng.HsinInn@cognizant.com"
				,"YongMeng.Sim@cognizant.com","Yin.Yifan@cognizant.com","ZheQin.Chong@cognizant.com","ZhiYi.Tan@cognizant.com","SongNian.Tay@cognizant.com"};
		notificationService.sendMailCC("ZhiYi.Tan@cognizant.com", cc, "Test Mail", "Lets get started guys!");
	}
	
	@Test
	public void testSendEmail() throws Exception{
		
		notificationService.sendMail("ZhiYi.Tan@cognizant.com", "Test Mail", "Lets get started guys!");
	}
	
	//--------------------------------------Send Email With Purpose & CC Test Cases---------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSendEmailWithPurposeIsVendorRequestToCompanyWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("ZhiYi.Tan@cognizant.com", cc, "Test Mail", "Vendor Request To Company is generated!","Test", EmailPurposeType.VendorRequestToCompany);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsVendorApplicationStatusWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Test Mail", "Vendor Application is generated!","Test", EmailPurposeType.VendorApplicationStatus);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsVendorApplicationAcceptedWithCC() throws Exception{
		String cc[] = {};
		
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Test Mail", "Vendor Application acceptance is generated!","Test", EmailPurposeType.VendorApplicationAccepted);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsVendorApplicationRequestRejectedWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Test Mail", "Vendor Application rejection is generated!","Test", EmailPurposeType.VendorApplicationRejected);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsCompanyRegistrationEmailVerificationWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Company RegistrationEmail Verification", "Company Registration Email Verificationn is generated!","Test", EmailPurposeType.CompanyRegistrationEmailVerification);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsServiceRequestFromCompanyToVendorWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Service Request From Company To Vendor", "Service Request From Company To Vendor!","Test", EmailPurposeType.ServiceRequestFromCompanyToVendor);
		
	}
	
	
	@Test
	public void testSendEmailWithPurposeAccountCreatedWithCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Account Created.", "Account Created!","Test", EmailPurposeType.AccountCreated);
		
	}
	
	@Test
	public void testSendEmailWithPurposeIsSendToNextEmployeeVettorWithoutCC() throws Exception{
		String cc[] = {};
		notificationService.sendEmailWithPurposeCC("Richard.Ong@cognizant.com", cc, "Send To Next Employee Vettor", "Send To Next Employee Vettor!","Test", EmailPurposeType.SendToNextEmployeeVettor);
		
	}
	
	
}
