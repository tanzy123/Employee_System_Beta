package com.beta.controllerImpl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CompanyExternalAffairsControllerImpl {

	@RequestMapping(value="/serviceRequestFromVendor", method = RequestMethod.GET)
	public ModelAndView showServiceRequestFromVendor(HttpSession session)
	{
		ModelAndView mav=new ModelAndView("redirect:/serviceRequestFromVendor");
		return mav;
		
	}
	
	
}
