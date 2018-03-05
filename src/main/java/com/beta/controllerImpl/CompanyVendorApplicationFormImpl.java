package com.beta.controllerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.KeySelector.Purpose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmailPurposeType;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.service.MailNotification;
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
	
	@Autowired
	MailNotification mailNotification;
	
	@RequestMapping(value = "/vendorApplicationForm", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplications(HttpSession session) {
		try
		{
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		ModelAndView mav = new ModelAndView("vendorApplicationForm", "application", new Application());
		
		mav.addObject("account", account);
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
	    	mav.addObject("message", "Pending and vetting application view could not be displayed");
	    	
		   return mav;
		}
	}
	
	@RequestMapping(value = "/findCompany", method = RequestMethod.GET)
	public ModelAndView companySearch(HttpSession session, @RequestParam(value = "comName") String comName) {
		try {
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());
			
			List <Company>comList=companyService.findByComName(comName);
			
			ModelAndView mav = new ModelAndView("displayComSearch");
			mav.addObject("comList", comList);
			
			return mav;
		}

		catch (VendorMgmtException e) {
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());

			return mav;
		} catch (UserException e) {
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());

			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", "Registration could not be carried out.");

			return mav;
		}

	}
	
	
	
	
	@RequestMapping(value = "/applyApplicationStage2", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage2(HttpSession session, @ModelAttribute("application") Application application) {
		try
		{
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		if (companyService.findbyRefNo(application.getCompanyReferenceNumber())==null)
			throw new VendorMgmtException("Company Reference Number does not exist");
		
		List<String> categoryNames = generateListOfCategoriesOfCompany(application.getCompanyReferenceNumber());
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage2", "application", application);
		mav.addObject("categoryNames", categoryNames);
		mav.addObject("account", account);
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
	    	mav.addObject("message", "apply Application stage 2 is not successful!");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/applyApplicationStage3", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage3(HttpSession session,
			@ModelAttribute("application") Application application) {
		try
		{
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		Category category = categoryService.findByNameAndCompanyRef
				(application.getCategory().getCategoryName(), application.getCompanyReferenceNumber());
		application.setCategory(category);
//		vendorApplicationService.validateVendorApplication(application);
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage3");
		mav.addObject("application", application);
		mav.addObject("account", account);
		
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
	    	mav.addObject("message", "apply Application stage 3 is not successful!");
	    	
		   return mav;
		}
	}
	
	@RequestMapping(value = "/uploadDocumentsAndSubmit", method = RequestMethod.POST)
	   public String documentsUpload(@RequestParam("file") MultipartFile[] files, 
			   @ModelAttribute("application") Application application) throws Exception {
		Category category = categoryService.findByNameAndCompanyRef
				(application.getCategory().getCategoryName(), application.getCompanyReferenceNumber());
		application.setCategory(category);
		Application generatedApplication = vendorApplicationService.generateVendorApplication(application);
		vendorApplicationService.uploadApplicationAndDocuments(generatedApplication, files);
		sendEmailNotificationToCompany(generatedApplication);
		
//		return to dashboard
	      return "dashboardcompany";
	}
	
	
	
	private void sendEmailNotificationToCompany(Application application) throws Exception {
		Company company = companyService.findbyRefNo(application.getCompanyReferenceNumber());
		String to = company.getCompanyEmail();
		Company vendor = companyService.findbyRefNo(application.getVendorReferenceNumber());
		String[] cc = {vendor.getCompanyEmail()};
		String subject = "Vendor Application Request";
		String msg = vendor.getCompanyName() + " wishes to apply as a " + application.getCategory().getCategoryName() + " vendor";
		mailNotification.sendEmailWithPurposeCC(to, cc, subject, msg, "", EmailPurposeType.VendorRequestToCompany);
	}

	private List<String> generateListOfCategoriesOfCompany(String companyReferenceNumber) {
		List<Category> categoryList = categoryService.findByCompanyRef(companyReferenceNumber);
		List<String> categoryNames = new ArrayList<>();
		for (Category c: categoryList)
			categoryNames.add(c.getCategoryName());
		return categoryNames;
	}
}
