package com.beta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/")
public class CompanyController {

	
	@RequestMapping(value = "/pendingVendorApplication")
	
	 public String pendingVendorApplication(@ModelAttribute("pendingVendorApplication") Companies company)
	{

		return "pendingVendorApplication";
	}

	@RequestMapping(value = "/addCompanyIntendingToBeVendor")
	
	public String addCompany(@ModelAttribute("company") Companies company)
	{

		return "addCompanyIntendingToBeVendor";
	}

	@RequestMapping(value = "/vendorApplicationHistory")
	
	public String vendorApplicationHistory(
			@ModelAttribute("company") Companies company) 
	{

		return "vendorApplicationHistory";
	}

	@RequestMapping(value = "/employeeManagement")
	
	public String employeeManagement(@ModelAttribute("company") Companies company) {

		return "employeeManagement";
	}

	@RequestMapping(value = "/vetterManagement")
	public String vetterManagement(@ModelAttribute("company") Companies company) {

		return "vetterManagement";
	}

	@RequestMapping(value = "/companyManagementSys")
	public String companyManagementSystem(
			@ModelAttribute("company") Companies company) {

		return "companyManagementSys";
	}

	@RequestMapping(value = "/requirementForVetterManagement")
	
	public String requirement(@ModelAttribute("company") Companies company) {

		return "requirementForVetterManagement";
	}
	
	@RequestMapping(value = "/vetterNotificationService")
	
	public String notificationService(@ModelAttribute("company") Companies company) {

		return "vetterNotificationService";
	}
	
	@RequestMapping(value = "/requestServiceFromAVendor")
	
	public String requestServiceFromAVendor(@ModelAttribute("company") Companies company) {

		return "requestServiceFromAVendor";
	}
	
	@RequestMapping(value = "/applyToBeAVendor")
	
	public String applyToBeAVendor(@ModelAttribute("company") Companies company) {

		return "applyToBeAVendor";
	}
	
}
