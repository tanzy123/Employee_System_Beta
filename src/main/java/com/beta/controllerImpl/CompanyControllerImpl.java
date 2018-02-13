package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyService;

@Controller
public class CompanyControllerImpl {
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/vettermanagement", method = RequestMethod.GET)  
    public ModelAndView showVetters(@ModelAttribute("company") Company company){  
        List<CompanyApplication> companyApplicationlist = getList(company.getCompanyReferenceNumber());
        ModelAndView model = new ModelAndView("vettermanagement");
        model.addObject("companyApplicationlist", companyApplicationlist);
        
        return model;
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
