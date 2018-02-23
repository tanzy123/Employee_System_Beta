package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;

@Controller
public class CompanyVendorApplicationHistoryControllerImpl {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyAdminstratorAccountService accountService;

	@RequestMapping(value = "/pendingVendorApplication", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplications(HttpSession session) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllPendingAndVettingApplications(account.getCompanyReferenceNumber());
		
		
		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);

		return mav;
	}
	
	@RequestMapping(value = "/vendorApplication", method = RequestMethod.GET)
	public ModelAndView getAllApplications(HttpSession session) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllApplications(account.getCompanyReferenceNumber());
		
		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);

		return mav;
	}

	private List<CompanyApplication> getAllApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByCompRef(companyReferenceNumber);
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> getAllPendingAndVettingApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByStatusAndCompRef(ApplicationStatus.PENDING, companyReferenceNumber);
		applicationList.addAll(applicationService.findByStatusAndCompRef(ApplicationStatus.VETTING, companyReferenceNumber));
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> convertToCompanyApplication(List<Application> applicationList) {
		List<CompanyApplication> vendorApplicationDetails = new ArrayList<>();
		for (Application a: applicationList) {
			Company c = companyService.findbyRefNo(a.getVendorReferenceNumber());
			CompanyApplication vendorApplication = new CompanyApplication(c, a);
			vendorApplicationDetails.add(vendorApplication);
		}
		return vendorApplicationDetails;
		
	}

}
