package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(value = VendorMgmtException.class)
	public ModelAndView defaultVendorMgmtExceptionHandler(VendorMgmtException e) throws Exception {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("message", e.getMessage());
		return mav;
	}
	
	@ExceptionHandler(value = UserException.class)
	public ModelAndView defaultUserExceptionHandler(UserException e) throws Exception {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("message", e.getMessage());
		return mav;
	}
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
}
