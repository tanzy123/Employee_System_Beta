package com.beta;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;



@Service
public class MailMail implements NotificationService{

//	private MailSender mailSender;
	
	HtmlEmail email = new HtmlEmail();

	
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
	
	public void sendMail(String to, String cc[], String subject,String msg) throws Exception
	{
		//Create Message		
		//SimpleMailMessage message = new SimpleMailMessage();
		String path = ConfigUtil.getKey("htmlTemplateLocation");
		//Resource resource = new ClassPathResource(path);
		String msg1 = FileUtils.readFileToString(new File(path));
		
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
	
}
