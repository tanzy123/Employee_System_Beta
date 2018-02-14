package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class CompanyControllerImpl {
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
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
		
        List<CompanyApplication> companyApplicationlist = getList(account.getCompanyReferenceNumber());
        ModelAndView mav = new ModelAndView("vettermanagement");
        mav.addObject("companyApplicationlist", companyApplicationlist);
        
        return mav;
    }
	
	

	private List<CompanyApplication> getList(String companyReferenceNumber) {
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
