package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.NewCompanyVerificationController;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.service.RegistrationService;
import com.beta.services.CompanyService;

@Controller
public class NewCompanyVerificationControllerImpl implements NewCompanyVerificationController{

	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	CompanyValidation companyValicationService;
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("registration");
		mav.addObject("registration", new Company());
		return mav;
	}
	@RequestMapping(value = "/storeRegistration", method= RequestMethod.POST)
	public ModelAndView StoreRegistration(@RequestParam(value = "companyReferenceNumber") String companyReferenceNumber,
			@RequestParam(value = "companyName") String companyName,
			@RequestParam(value = "companyAddress") String companyAddress,
			@RequestParam(value = "companyEmail") String companyEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "companyWebsite") String companyWebsite,
			@RequestParam(value = "turnover") String turnover,
			@RequestParam(value = "category") String category)
	{
		ModelAndView mav = null;
		Company company = new Company();
		company.setCompanyReferenceNumber(companyReferenceNumber);
		company.setCompanyName(companyName);
		company.setCompanyAddress(companyAddress);
		company.setCompanyEmail(companyEmail);
		company.setCompanyWebsite(companyWebsite);
		company.setContactNumber(contactNumber);
		company.setTurnover(Long.parseLong(turnover));
		Category categoryAll= new Category();
		String []categoryList=category.split(",");
		List<Category> categoryAtRegistration = new ArrayList<>();
		for(String cat: categoryList)
		{
			categoryAll.setCategoryName(cat);
			categoryAll.setCompanyReferenceNumber(companyReferenceNumber);
			categoryAtRegistration.add(categoryAll);
		
		}
		company.setCategory(categoryAtRegistration);
		try
		{
		companyValicationService.validateCommpanyApplication(company);
		}catch(VendorMgmtException e)
		{
			mav = new ModelAndView("error");
			mav.addObject("message", e);
		}
		companyService.saveOrUpdate(company);
		
		
		registrationService.SendVarificationEmail(company);
		
		return null;
		
	}
	
	@Override
	@RequestMapping(value = "/emailToken", method = RequestMethod.GET)
	public ModelAndView tokenPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("token");
		return mav;
	}

	@RequestMapping(value="/verifyToken", method=RequestMethod.POST)
	public ModelAndView tokenVarification(@RequestParam(value = "token") String token) 
	{
		ModelAndView mav=null;
		if(registrationService.TokenComparison(token))
		{
			mav=new ModelAndView("dashboard");
		}
		else
		{
			mav = new ModelAndView("token");
			mav.addObject("message", "Wrong token entered!");
			
		}
		return null;
		
	}
}
