package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmailPurposeType;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CategoryService;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.CompanyService;
import com.beta.service.MailNotification;
import com.beta.service.VendorApplication;

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
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		ModelAndView mav = new ModelAndView("vendorApplicationForm", "application", new Application());

		mav.addObject("account", account);
		return mav;

	}

	@RequestMapping(value = "/addCompany/{companyRef}", method = RequestMethod.GET)
	public ModelAndView addCompany(HttpSession session, @PathVariable String companyRef) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");

		ModelAndView mav = new ModelAndView("vendorApplicationForm", "application", new Application());
		mav.addObject("companyReferenceNumber", companyRef);
		mav.addObject("account", account);

		return mav;

	}

	@RequestMapping(value = "/findCompany", method = RequestMethod.GET)
	public ModelAndView companySearch(HttpSession session, @RequestParam(value = "comName") String comName) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		CompanyAdministratorAccount account = accountService
				.findByUserName(session.getAttribute("username").toString());

		List<Company> comList = companyService.findByComName(comName);

		ModelAndView mav = new ModelAndView("vendorApplicationForm", "application", new Application());
		mav.addObject("comList", comList);
		mav.addObject("account", account);
		return mav;

	}

	@RequestMapping(value = "/applyApplicationStage2", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage2(HttpSession session,
			@ModelAttribute("application") Application application) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		if (companyService.findbyRefNo(application.getCompanyReferenceNumber()) == null)
			throw new VendorMgmtException("Company Reference Number does not exist");
		if (application.getCompanyReferenceNumber().equals(application.getVendorReferenceNumber()))
			throw new VendorMgmtException("You cannot apply to be a vendor of your own company!");
		List<String> categoryNames = generateListOfCategoriesOfCompany(application.getCompanyReferenceNumber());
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage2", "application", application);
		mav.addObject("categoryNames", categoryNames);
		mav.addObject("account", account);
		return mav;

	}

	@RequestMapping(value = "/applyApplicationStage3", method = RequestMethod.POST)
	public ModelAndView applyApplicationStage3(HttpSession session,
			@ModelAttribute("application") Application application) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		Category category = categoryService.findByNameAndCompanyRef(application.getCategory().getCategoryName(),
				application.getCompanyReferenceNumber());
		application.setCategory(category);
		// vendorApplicationService.validateVendorApplication(application);
		ModelAndView mav = new ModelAndView("vendorApplicationFormStage3");
		mav.addObject("application", application);
		mav.addObject("account", account);

		return mav;

	}

	@RequestMapping(value = "/uploadDocumentsAndSubmit", method = RequestMethod.POST)
	public ModelAndView documentsUpload(@RequestParam("file") MultipartFile[] files,
			@ModelAttribute("application") Application application, HttpSession session) throws Exception {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		Category category = categoryService.findByNameAndCompanyRef(application.getCategory().getCategoryName(),
				application.getCompanyReferenceNumber());
		application.setCategory(category);
		Application generatedApplication = vendorApplicationService.generateVendorApplication(application);
		vendorApplicationService.uploadApplicationAndDocuments(generatedApplication, files);
		sendEmailNotificationToCompany(generatedApplication);
		ModelAndView mav = new ModelAndView("dashboardcompany");
		// return to dashboard
		return mav;

	}

	private void sendEmailNotificationToCompany(Application application) throws Exception {
		Company company = companyService.findbyRefNo(application.getCompanyReferenceNumber());
		String to = company.getCompanyEmail();
		Company vendor = companyService.findbyRefNo(application.getVendorReferenceNumber());
		String[] cc = { vendor.getCompanyEmail() };
		String subject = "Vendor Application Request";
		String msg = vendor.getCompanyName() + " wishes to apply as a " + application.getCategory().getCategoryName()
				+ " vendor";
		mailNotification.sendEmailWithPurposeCC(to, cc, subject, msg, "", EmailPurposeType.VendorRequestToCompany);
	}

	private List<String> generateListOfCategoriesOfCompany(String companyReferenceNumber) {
		List<Category> categoryList = categoryService.findByCompanyRef(companyReferenceNumber);
		List<String> categoryNames = new ArrayList<>();
		for (Category c : categoryList)
			categoryNames.add(c.getCategoryName());
		return categoryNames;
	}
}
