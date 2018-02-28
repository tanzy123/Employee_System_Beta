package com.beta.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beta.entity.Company;
import com.beta.services.ApplicationService;
import com.beta.services.RequirementService;

@Controller
public class EmployeeControllerImpl {

	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	RequirementService requirementService;
	
	@RequestMapping(value = "/employeeRequestServiceFromAVendor")
	public String employeeRequestServiceFromAVendor(@ModelAttribute("company") Company company) {

		return "requestServiceFromAVendor";
	}
	
	@RequestMapping(value = "/pendingvendorapplication")
	public String employeePendingVendorApplication(@ModelAttribute("company") Company company) {

		return "pendingvendorapplication";
	}
	
	//applytobeavendor
	@RequestMapping(value = "/applytobeavendor")
	public String applytobeavendor(@ModelAttribute("company") Company company) {

		return "applytobeavendor";
	}
	 
	@RequestMapping(value = "/vendorapplicationhistory")
	public String employeeChecksVendorApplicationHistory(@ModelAttribute("company") Company company) {

		return "vendorapplicationhistory";
	}
	
	@RequestMapping(value = "/applyingToBeAVendor")
	public String applyToBeAVendor(@ModelAttribute("company") Company company) {

		return "applyToBeAVendor";
	}
	
	@RequestMapping(value = "/editEmployeeInformation")
	public String editEmployeeInformation(@ModelAttribute("company") Company company) {

		return "editemployeeinformation";
	}
	
	

	
}
