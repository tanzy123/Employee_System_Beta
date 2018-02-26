package com.beta.service;

import com.beta.entity.EmailPurposeType;


public interface NotificationService {
	
	public void sendMailCC(String to, String cc[], String subject,String msg) throws Exception;
	
	public void SmsService() throws Exception;

	public void sendMail(String to, String subject,String msg) throws Exception;
	
	public void sendEmailWithPurposeCC(String to, String cc[], String subject,String msg,String msgFromPreviousVettor,EmailPurposeType purpose) throws Exception;
	

}
