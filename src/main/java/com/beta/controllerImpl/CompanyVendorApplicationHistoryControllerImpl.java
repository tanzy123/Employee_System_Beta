package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.VendorMgmtException;
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

	@RequestMapping(value = "/pendingCompanyApplication", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplications(HttpSession session) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllPendingAndVettingApplications(account.getCompanyReferenceNumber());
		
		
		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);

		return mav;
	}
	
	@RequestMapping(value = "/companyApplication", method = RequestMethod.GET)
	public ModelAndView getAllApplications(HttpSession session) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllApplications(account.getCompanyReferenceNumber());
		
		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);

		return mav;
	}
	
	@RequestMapping(value = "/companyApplicationHistory/{applicationRef}", method = RequestMethod.GET)  
    public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef, HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(), applicationRef);
		
        ModelAndView mav = new ModelAndView("companyApplicationDetails");
        mav.addObject("companyApplication", companyApplication);
     
        return mav;
    }

	private List<CompanyApplication> getAllApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByVendorRef(companyReferenceNumber);
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> getAllPendingAndVettingApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByStatusAndVendorRef(ApplicationStatus.PENDING, companyReferenceNumber);
		applicationList.addAll(applicationService.findByStatusAndVendorRef(ApplicationStatus.VETTING, companyReferenceNumber));
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> convertToCompanyApplication(List<Application> applicationList) {
		List<CompanyApplication> vendorApplicationDetails = new ArrayList<>();
		for (Application a: applicationList) {
			Company c = companyService.findbyRefNo(a.getCompanyReferenceNumber());
			CompanyApplication vendorApplication = new CompanyApplication(c, a);
			vendorApplicationDetails.add(vendorApplication);
		}
		return vendorApplicationDetails;
	}
	
	private CompanyApplication getCompanyApplication(String companyReferenceNumber, String applicationRef) {
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		if (!application.getVendorReferenceNumber().equals(companyReferenceNumber))
			throw new VendorMgmtException("Company Reference number does not match application's company reference number");
		else {
			Company company = companyService.findbyRefNo(application.getCompanyReferenceNumber());
			return new CompanyApplication(company, application);
		}
	}

}
