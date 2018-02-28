package com.beta.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.beta.entity.EmailPurposeType;




@Service
public class MailNotification implements NotificationService{

//	private MailSender mailSender;
	
	HtmlEmail email = new HtmlEmail();

	
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
	
	public void sendMailCC(String to, String cc[], String subject,String msg) throws Exception
	{
		//Create Message		
		//SimpleMailMessage message = new SimpleMailMessage();
		String filename = ConfigUtil.getKey("htmlTemplateLocation");
		//Resource resource = new ClassPathResource(path);
		String msg1 = FileUtils.readFileToString(new File(filename));
		
		msg = msg1.replace("${message}", msg);
		
		
		email.setMsg(msg);
		//email.setFrom(from, "Admin Emp Mgmt System");
		email.addTo(to);
		email.setSubject(subject);
			for(String c : cc){
				email.addCc(c);
			}
		String host = ConfigUtil.getKey("hostName");
		String from = ConfigUtil.getKey("sendFrom");
		String fromName = ConfigUtil.getKey("sendFromName");
		String pass = ConfigUtil.getKey("sendFromPwd");
		String smtpPort = ConfigUtil.getKey("smtpPort");
		
		email.setHostName(host);
		
		email.setFrom(from, fromName);
		
		email.setAuthenticator(new DefaultAuthenticator(from, pass));
		email.setTLS(true);
		System.out.println("smtp port configured  is "+smtpPort);
		email.setSmtpPort(Integer.parseInt(smtpPort));
		email.setSSL(true);
		// send the he
		email.send();
		System.out.println("Email Sent");

	}
	
	@Override
	public void sendMail(String to, String subject, String msg) throws Exception {
		
		
				String filename = ConfigUtil.getKey("htmlTemplateLocation");
				String msg1 = FileUtils.readFileToString(new File(filename));
				
				msg = msg1.replace("${message}", msg);
				
				
				email.setMsg(msg);
				email.addTo(to);
				email.setSubject(subject);
					
				String host = ConfigUtil.getKey("hostName");
				String from = ConfigUtil.getKey("sendFrom");
				String fromName = ConfigUtil.getKey("sendFromName");
				String pass = ConfigUtil.getKey("sendFromPwd");
				String smtpPort = ConfigUtil.getKey("smtpPort");
				
				email.setHostName(host);
				
				email.setFrom(from, fromName);
				
				email.setAuthenticator(new DefaultAuthenticator(from, pass));
				email.setTLS(true);
				System.out.println("smtp port configured  is "+smtpPort);
				email.setSmtpPort(Integer.parseInt(smtpPort));
				email.setSSL(true);
				email.send();
	}




	
	
	@Override
	public void sendEmailWithPurposeCC(String to, String[] cc, String subject,
			String msg, String msgFromPreviousVettor, EmailPurposeType purpose)
			throws Exception {
		// TODO Auto-generated method stub
		if(purpose==EmailPurposeType.CompanyRegistrationEmailVerification)
		{
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("CompanyRegistrationEmailVerification");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${CompanyRegistrationEmailVerificationResult_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
			
		}
		
		if(purpose==EmailPurposeType.VendorRequestToCompany)
		{
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("VendorRequestToCompany");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${vendorApplicationRequestToCompany_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
			
		}
		
		if(purpose==EmailPurposeType.SendToNextEmployeeVettor)
		{
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("SendToNextEmployeeVettor");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${messageFromVettorToNextVettor_Message}", msg);
			msgFromPreviousVettor=msg1.replace("${messageFromPreviousVettor_Message}",msgFromPreviousVettor);
			configureEmail(to, cc, subject, msg, email);
		}
		if(purpose==EmailPurposeType.ServiceRequestFromCompanyToVendor)
		{
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("ServiceRequestFromCompanyToVendor");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${ServiceRequestFromCompanyToVendor_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}
		if(purpose==EmailPurposeType.VendorApplicationAccepted)
		{
			//DoSomething
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("VendorApplicationAccepted");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${vendorApplicationResultAccepted_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}	
		if(purpose==EmailPurposeType.VendorApplicationStatus)
		{
			//Do something
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("VendorApplicationStatus");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${vendorApplicationStatusPending_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}
		
		if(purpose==EmailPurposeType.ServiceRequestFromCompanyToVendor)
		{
			//Do something
			HtmlEmail email = new HtmlEmail();
			String filename = ConfigUtil.getKey("ServiceRequestFromCompanyToVendor");
			String msg1 = FileUtils.readFileToString(new File(filename));
			
			msg=msg1.replace("${ServiceRequestFromCompanyToVendor_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}
		if(purpose==EmailPurposeType.AccountCreated)
		{
			HtmlEmail email = new HtmlEmail();
			String path = ConfigUtil.getKey("AccountCreated");
			String msg1 = FileUtils.readFileToString(new File(path));
			
			msg=msg1.replace("${AccountCreated_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}
		if(purpose==EmailPurposeType.VendorApplicationRejected) {
			HtmlEmail email = new HtmlEmail();
			String path = ConfigUtil.getKey("VendorApplicationRejected");
			String msg1 = FileUtils.readFileToString(new File(path));
			
			msg=msg1.replace("${vendorApplicationResultRejected_Message}", msg);
			configureEmail(to, cc, subject, msg, email);
		}
		
	}

	private void configureEmail(String to, String[] cc, String subject, String msg, HtmlEmail email)
			throws EmailException {
		email.setMsg(msg);
		email.addTo(to);
		email.setSubject(subject);
		for(String c : cc)
		{
			email.addCc(c);
		}
		String host = ConfigUtil.getKey("hostName");
		String from = ConfigUtil.getKey("sendFrom");
		String fromName = ConfigUtil.getKey("sendFromName");
		String pass = ConfigUtil.getKey("sendFromPwd");
		String smtpPort = ConfigUtil.getKey("smtpPort");
		
		email.setHostName(host);
		email.setFrom(from, fromName);
		email.setAuthenticator(new DefaultAuthenticator(from, pass));
		email.setTLS(true);
		System.out.println("smtp port configured  is "+smtpPort);
		email.setSmtpPort(Integer.parseInt(smtpPort));
		email.setSSL(true);
		email.send();
	}
	@Override
	public void SmsService() throws Exception {
		// TODO Auto-generated method stub
		
	}




	
	
}
