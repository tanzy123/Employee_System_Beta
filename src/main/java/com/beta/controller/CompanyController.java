package com.beta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beta.entity.Company;





@Controller
public class CompanyController {

	//Link to JSP : Add Company intending to be Vendor
	@RequestMapping(value = "/pendingVendorApplication")
	
	public String pendingVendorApplication(@ModelAttribute("pendingVendorApplication") Company company)
	{
		
		
		
		return "pendingVendorApplication";
	}
	
	
@RequestMapping(value = "/addCompanyIntendingToBeVendor")
	
	public String addCompany(@ModelAttribute("company") Company company)
	{
		
		
		
		return "addCompanyIntendingToBeVendor";
	}

@RequestMapping(value = "/vendorApplicationHistory")

public String vendorApplicationHistory(@ModelAttribute("company") Company company)
{
	
	
	
	return "vendorApplicationHistory";
}

@RequestMapping(value = "/employeeManagement")

public String employeeManagement(@ModelAttribute("company") Company company)
{
	
	
	
	return "employeeManagement";
}
	

@RequestMapping(value = "/vetterManagement")

public String vetterManagement(@ModelAttribute("company") Company company)
{
	
	
	
	return "vetterManagement";
}
	



}
