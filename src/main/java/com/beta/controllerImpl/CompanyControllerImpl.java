package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.controller.object.RequirementList;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.service.VetterAssignmentService;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;
import com.beta.services.RequirementService;

@Controller
public class CompanyControllerImpl {
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	RequirementService requirementService;
	
	@Autowired
	VetterAssignmentService vetterAssignmentService;
	
	@RequestMapping(value = "/dashboardcompany", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpSession session) {
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		ModelAndView mav = new ModelAndView("dashboardcompany");
		return mav;
	}

	@RequestMapping(value = "/vetterManagement", method = RequestMethod.GET)  
    public ModelAndView showVetters(HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		
        List<CompanyApplication> companyApplicationlist = getListOfApplicationsToBeVetted(account.getCompanyReferenceNumber());
        ModelAndView mav = new ModelAndView("vettermanagement");
        mav.addObject("companyApplicationlist", companyApplicationlist);
     
        return mav;
    }
	
	@RequestMapping(value = "/pendingApplication", method = RequestMethod.GET)  
    public ModelAndView getApplicationsToBeVetted(HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		
        List<CompanyApplication> companyApplicationlist = getListOfApplicationsToBeVetted(account.getCompanyReferenceNumber());
        ModelAndView mav = new ModelAndView("vettermanagement");
        mav.addObject("companyApplicationlist", companyApplicationlist);
     
        return mav;
    }
	
	@RequestMapping(value = "/vendorApplication/{applicationRef}", method = RequestMethod.GET)  
    public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef, HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(), applicationRef);
		
        ModelAndView mav = new ModelAndView("vendorApplicationDetails");
        mav.addObject("companyApplication", companyApplication);
     
        return mav;
    }
	
	@RequestMapping(value = "/assignVetter/{applicationRef}", method = RequestMethod.GET)  
    public ModelAndView assignVetters(@PathVariable String applicationRef, HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(), applicationRef);
		
        ModelAndView mav = new ModelAndView("assignVetter");
        mav.addObject("companyApplication", companyApplication);
     
        return mav;
    }
	
	@RequestMapping(value = "/setVetters", method = RequestMethod.POST)  
	@ResponseBody
    public String setVetters(@RequestBody RequirementList requirementList, HttpSession session){
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		vetterAssignmentService.assignVettersByApplicationRef(account.getCompanyReferenceNumber(), requirementList.getRequirementList());
		return "Sucessful";
    }
	
	private CompanyApplication getCompanyApplication(String companyReferenceNumber, String applicationRef) {
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		if (!application.getCompanyReferenceNumber().equals(companyReferenceNumber))
			throw new VendorMgmtException("Company Reference number does not match application's company reference number");
		else {
			Company company = companyService.findbyRefNo(application.getVendorReferenceNumber());
			return new CompanyApplication(company, application);
		}
	}

	private List<CompanyApplication> getListOfApplicationsToBeVetted(String companyReferenceNumber) {
		List<CompanyApplication> list = new ArrayList<>();
		List<Application> applicationList = applicationService.findByStatusAndCompRef(ApplicationStatus.VETTING, companyReferenceNumber);
		for (Application a: applicationList) {
			Company c = companyService.findbyRefNo(a.getVendorReferenceNumber());
			CompanyApplication companyApplication = new CompanyApplication(c, a);
			list.add(companyApplication);
		}
		return list;
	}
}
