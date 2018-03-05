package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.NewCompanyVerificationController;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CompanyService;
import com.beta.service.CompanyValidation;
import com.beta.service.RegistrationService;


@Controller

public class NewCompanyVerificationControllerImpl implements NewCompanyVerificationController{

	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	CompanyValidation companyValidationService;
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response)
	{
		try {
		ModelAndView mav = new ModelAndView("registration");
		mav.addObject("registration", new Company());
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "Registration view could not be displayed");
	    	
		   return mav;
		}
	}
	@RequestMapping(value = "/storeRegistration", method= RequestMethod.POST)
	public ModelAndView StoreRegistration(
			@RequestParam(value = "companyReferenceNumber") String companyReferenceNumber,
			@RequestParam(value = "companyName") String companyName,
			@RequestParam(value = "companyAddress") String companyAddress,
			@RequestParam(value = "companyEmail") String companyEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "companyWebsite") String companyWebsite,
			@RequestParam(value = "turnover") String turnover,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "username") String userName,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "department") String department,
			@RequestParam(value = "role") String role)
	{
		ModelAndView mav = null;
		try
		{
		Company company = new Company();
		company.setCompanyReferenceNumber(companyReferenceNumber);
		company.setCompanyName(companyName);
		company.setCompanyAddress(companyAddress);
		company.setCompanyEmail(companyEmail);
		company.setCompanyWebsite(companyWebsite);
		company.setContactNumber(contactNumber);
		company.setTurnover(Long.parseLong(turnover));
		
		String []categoryList=category.split(",");
		List<Category> categoryAtRegistration = new ArrayList<>();
		for(String cat: categoryList)
		{
			Category newCategory= new Category();
			newCategory.setCategoryName(cat);
			newCategory.setCompanyReferenceNumber(companyReferenceNumber);
			categoryAtRegistration.add(newCategory);
		
		}
		company.setCategory(categoryAtRegistration);
		
		String[] departmentList = department.split(",");
		List<Department> departmentAtRegistration = new ArrayList<>();
		for (String dept: departmentList) {
			Department newDepartment = new Department();
			newDepartment.setCompanyReferenceNumber(companyReferenceNumber);
			newDepartment.setDepartmentName(dept);
			departmentAtRegistration.add(newDepartment);
		}
		company.setDepartment(departmentAtRegistration);
		
		String[] roleList = role.split(",");
		List<Role> roleAtRegistration = new ArrayList<>();
		for (String r: roleList) {
			Role newRole = new Role();
			newRole.setCompanyReferenceNumber(companyReferenceNumber);
			newRole.setRole(r);
			roleAtRegistration.add(newRole);
		}
		company.setRoles(roleAtRegistration);
		
		
		companyValidationService.validateCommpanyApplication(company);
		
		companyService.saveOrUpdate(company);
		registrationService.registerCompanyAccount(company, userName, password);
		registrationService.sendVerificationEmail(company, userName);
		ModelAndView successMAV = new ModelAndView("token");
		successMAV.addObject("username", userName);
		return successMAV;
		}catch(VendorMgmtException e)
		{
			mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
	
		catch(UserException e)
		{
        	 mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	 mav = new ModelAndView("error");
	    	mav.addObject("message", "Error in registering account");
	    	
		   return mav;
		}
		
	}
	
	@Override
	@RequestMapping(value = "/emailToken", method = RequestMethod.GET)
	public ModelAndView tokenPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("token");
		return mav;
	}

	@RequestMapping(value="/verifyToken", method=RequestMethod.POST)
	public ModelAndView tokenVarification(HttpSession session,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "username") String username) 
	{
		
		ModelAndView mav=null;
		try {
		if(registrationService.tokenComparison(token, username))
		{
			mav=new ModelAndView("redirect:/login");
		}
		else
		{
			mav = new ModelAndView("token");
			mav.addObject("message", "Wrong token entered!");
			
		}
		return mav;
		}catch(VendorMgmtException e)
		{
			mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
	
		catch(UserException e)
		{
        	 mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	 mav = new ModelAndView("error");
	    	mav.addObject("message", "Error in verifying token");
	    	
		   return mav;
		}
		
	}
}