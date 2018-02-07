package com.beta.service;


public interface NotificationService {
	
	public void sendMail(String to, String cc[], String subject,String msg) throws Exception;
	
	public void SmsService() throws Exception;

}
