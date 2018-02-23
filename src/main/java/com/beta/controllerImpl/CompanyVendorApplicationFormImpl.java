package com.beta.controllerImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.service.VendorApplication;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;

@Controller
public class CompanyVendorApplicationFormImpl {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	VendorApplication vendorApplicationService;
	
	@RequestMapping(value = "/vendorApplicationForm", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplications(HttpSession session) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		ModelAndView mav = new ModelAndView("vendorApplicationForm", "application", new Application());
		
		mav.addObject("account", account);
		return mav;
	}
	
	@RequestMapping(value = "/applyApplicationStage2", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage2(HttpSession session, @ModelAttribute("application") Application application) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		if (companyService.findbyRefNo(application.getCompanyReferenceNumber())==null)
			throw new VendorMgmtException("Company Reference Number does not exist");
		List<Category> categoryList = categoryService.findByCompanyRef(application.getCompanyReferenceNumber());
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage2", "application", application);
		mav.addObject("categoryList", categoryList);
		mav.addObject("account", account);
		return mav;
	}
	
	@RequestMapping(value = "/applyApplicationStage3", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage3(HttpSession session,
			@ModelAttribute("application") Application application) {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		
//		vendorApplicationService.validateVendorApplication(application);
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage3");
		mav.addObject("application", application);
		mav.addObject("account", account);
		
		return mav;
	}
	
	@RequestMapping(value = "/uploadDocumentsAndSubmit", method = RequestMethod.POST)
	   public String documentsUpload(@RequestParam("file") MultipartFile[] files, 
			   @ModelAttribute("application") Application application) throws IOException {
		Application generatedApplication = vendorApplicationService.generateVendorApplication(application);
		System.out.println(generatedApplication);
		// Save file on system
		
	      for (MultipartFile file : files) {
	         if (!file.getOriginalFilename().isEmpty()) {
	            BufferedOutputStream outputStream = new BufferedOutputStream(
	                  new FileOutputStream(
	                        new File("D:/MultipleFileUpload", file.getOriginalFilename())));

	            outputStream.write(file.getBytes());
	            outputStream.flush();
	            outputStream.close();
	         } else {
	            return "dashboardcompany";
	         }
	      }
	      return "dashboardcompany";
	}
}
