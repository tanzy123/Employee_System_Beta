package com.beta.controllerImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beta.entity.Company;

@Controller
public class EmployeeControllerImpl {

	@RequestMapping(value = "/employeeRequestServiceFromAVendor")
	public String employeeRequestServiceFromAVendor(@ModelAttribute("company") Company company) {

		return "requestServiceFromAVendor";
	}
	
	@RequestMapping(value = "/pendingvendorapplication")
	public String employeePendingVendorApplication(@ModelAttribute("company") Company company) {

		return "pendingvendorapplication";
	}
	 
	@RequestMapping(value = "/vendorapplicationhistory")
	public String employeeChecksVendorApplicationHistory(@ModelAttribute("company") Company company) {

		return "vendorapplicationhistory";
	}
	
	@RequestMapping(value = "/applyingToBeAVendor")
	public String applyToBeAVendor(@ModelAttribute("company") Company company) {

		return "applyToBeAVendor";
	}
	
	@RequestMapping(value = "/employeeLogin")
	public String employeeLogIn(@ModelAttribute("company") Company company) {

		return "employeeLogin";
	}
	
}
