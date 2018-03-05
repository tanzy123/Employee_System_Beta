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
import com.beta.controller.object.DocumentFiles;
import com.beta.controller.object.RequirementApproval;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Company;
import com.beta.entity.Documents;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;

import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.DocumentsService;
import com.beta.orm.service.EmployeeAccountService;
import com.beta.orm.service.RequirementService;
import com.beta.service.VendorVettingProcess;

@Controller
public class EmployeeDashboardAndVetVendorControllerImpl {

	
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
	
	@Autowired
	DocumentsService documentsService;

	@RequestMapping(value = "/employeeDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpSession session) {
		try {
		EmployeeAccount account = employeeAccountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		ModelAndView mav = new ModelAndView("employeeDashboard");
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
	    	mav.addObject("message", "Employee view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/pendingvendorapplication")
	public ModelAndView employeePendingVendorApplication(HttpSession session) {
		try {
		EmployeeAccount account = (EmployeeAccount) session.getAttribute("account");

		List<CompanyApplication> companyApplicationlist = getListOfPendingApplications(account.getUserName());
		ModelAndView mav = new ModelAndView("pendingvendorapplication");
		mav.addObject("companyApplicationlist", companyApplicationlist);

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
	    	mav.addObject("message", "employee pending application view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/companyApplicationPending/{applicationRef}", method = RequestMethod.GET)
	public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef,
			HttpSession session,
			@ModelAttribute("requirementApproval") RequirementApproval requirementApproval) {
		try {
		CompanyApplication companyApplication = getVendorApplication(applicationRef);
		List<DocumentFiles> files = getApplicationDocumentsFromDropBox(applicationRef);
		ModelAndView mav = new ModelAndView("vendorApplicationDetailsForEmployee");
		mav.addObject("companyApplication", companyApplication);
		mav.addObject("files", files);
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
	    	mav.addObject("message", "Application details view could not be displayed");
	    	
		   return mav;
		}
		
	}

	@RequestMapping(value = "/companyApplicationPending/vetApplication/{applicationRef}", method = RequestMethod.POST)
	public ModelAndView approveOrRejectApplication(HttpSession session, @PathVariable String applicationRef,
			@ModelAttribute("requirementApproval") RequirementApproval requirementApproval) {	
		String userName = session.getAttribute("username").toString();
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		
		
		try {
			vendorVettingProcess.vetVendor(userName, application, requirementApproval.getStatus(), requirementApproval.getRequirements());
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("success");
		return mav;
	}
	

	private List<DocumentFiles> getApplicationDocumentsFromDropBox(String applicationRef) {
		List<Documents> documents = documentsService.findByApplicationRef(applicationRef);
		List<DocumentFiles> documentFiles = new ArrayList<>();
		for (Documents d: documents) {
			String oringalFilename = d.getOriginalFileName();
			documentFiles.add(new DocumentFiles(d.getUrl(), oringalFilename));
		}
		return documentFiles;
	}

	private CompanyApplication getVendorApplication(String applicationRef) {
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		Company company = companyService.findbyRefNo(application.getVendorReferenceNumber());
		return new CompanyApplication(company, application);
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