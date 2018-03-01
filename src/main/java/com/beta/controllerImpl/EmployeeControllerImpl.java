package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.controller.object.RequirementApproval;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Company;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.exception.UserException;
import com.beta.service.VendorVettingProcess;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RequirementService;

@Controller
public class EmployeeControllerImpl {

//	
//	@Autowired
//	ExceptionHandler exceptionHandler;
	
	@Autowired
	EmployeeAccountService employeeAccountService;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	RequirementService requirementService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	VendorVettingProcess vendorVettingProcess;

	@RequestMapping(value = "/employeeDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpSession session) {
		EmployeeAccount account = employeeAccountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		ModelAndView mav = new ModelAndView("employeeDashboard");
		return mav;
	}

	@RequestMapping(value = "/employeeRequestServiceFromAVendor")
	public String employeeRequestServiceFromAVendor(@ModelAttribute("company") Company company) {

		return "requestServiceFromAVendor";
	}

	@RequestMapping(value = "/pendingvendorapplication")
	public ModelAndView employeePendingVendorApplication(HttpSession session) {

		EmployeeAccount account = (EmployeeAccount) session.getAttribute("account");

		List<CompanyApplication> companyApplicationlist = getListOfPendingApplications(account.getUserName());
		ModelAndView mav = new ModelAndView("pendingvendorapplication");
		mav.addObject("companyApplicationlist", companyApplicationlist);

		return mav;
	}

	@RequestMapping(value = "/companyApplicationPending/{applicationRef}", method = RequestMethod.GET)
	public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef,
			HttpSession session,
			@ModelAttribute("requirementApproval") RequirementApproval requirementApproval) {
		CompanyApplication companyApplication = getVendorApplication(applicationRef);

		ModelAndView mav = new ModelAndView("vendorApplicationDetailsForEmployee");
		mav.addObject("companyApplication", companyApplication);

		return mav;
	}

	@RequestMapping(value = "/companyApplicationPending/vetApplication/{applicationRef}", method = RequestMethod.POST)
	public ModelAndView approveOrRejectApplication(HttpSession session, @PathVariable String applicationRef,
			@ModelAttribute("requirementApproval") RequirementApproval requirementApproval) {	
		String userName = session.getAttribute("username").toString();
		Application application = applicationService.findByApplicationRefNo(applicationRef);
	//<--	
		try {
			vendorVettingProcess.vetVendor(userName, application, requirementApproval.getStatus(), requirementApproval.getRequirements());
		} 
		catch(Exception e)
		{
			//exceptionHandler.handleException(e);
		}

		ModelAndView mav = new ModelAndView("success");
		return mav;
	}
	//-->

	private CompanyApplication getVendorApplication(String applicationRef) {
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		Company company = companyService.findbyRefNo(application.getVendorReferenceNumber());
		return new CompanyApplication(company, application);
	}

	// applytobeavendor
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

	private List<CompanyApplication> getListOfPendingApplications(String userName) {
		List<CompanyApplication> list = new ArrayList<>();
		List<Requirement> listOfPendingRequirements = requirementService.findByUsernameAndStatus(userName,
				ApprovalStatus.PENDING);
		List<Application> listOfPendingApplications = listOfPendingRequirements.stream()
				.map(req -> applicationService.findByApplicationRefNo(req.getApplicationRef()))
				.collect(Collectors.toList());
		for (Application a : listOfPendingApplications) {
			Company c = companyService.findbyRefNo(a.getVendorReferenceNumber());
			CompanyApplication companyApplication = new CompanyApplication(c, a);
			list.add(companyApplication);
		}
		return list;
	}

}
