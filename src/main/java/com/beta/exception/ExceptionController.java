//package com.beta.exception;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionController {
//
//	private Log logger = LogFactory.getLog(ExceptionController.class);
//
//	@ExceptionHandler(value=RuntimeException.class)
//	public String handleException(HttpServletRequest request,Exception ex)
//	{
//		
//		logger.error("Request" +request.getRequestURL()+ "Threw an Exception",ex);
//		
//		return "login_error";
//	}
//	
//	
//	@ExceptionHandler(value=SecurityException.class)
//	public String handleException2(HttpServletRequest request,Exception ex)
//	{
//		
//		logger.error("Request" +request.getRequestURL()+ "Threw an Exception",ex);
//		
//		return "login_error";
//	}
//	
//	
//	
//}