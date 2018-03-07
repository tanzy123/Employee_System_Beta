package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.CompanyVendorApplicationController;
import com.beta.controller.object.CompanyApplication;
import com.beta.controller.object.DocumentFiles;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Documents;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.DocumentsService;
import com.beta.service.SaveDocumentService;

@Controller
public class CompanyVendorApplicationHistoryControllerImpl implements CompanyVendorApplicationController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyAdminstratorAccountService accountService;

	@Autowired
	DocumentsService documentsService;

	@Autowired
	SaveDocumentService saveDocumentService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.beta.controllerImpl.CompanyVendorApplicationController#
	 * getAllPendingAndVettingApplications(javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/pendingCompanyApplication", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplications(HttpSession session) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllPendingAndVettingApplications(
				account.getCompanyReferenceNumber());

		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);
		mav.addObject("msg", "Pending");

		return mav;

	}

	@Override
	@RequestMapping(value = "/pendingCompanyApplication/findbycompanyname", method = RequestMethod.GET)
	public ModelAndView getAllPendingAndVettingApplicationsWithEmpSearch(HttpSession session,
			@RequestParam(value = "comName") String comName) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllPendingAndVettingApplications(
				account.getCompanyReferenceNumber());

		List<Company> comList = companyService.findByComName(comName);

		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);
		mav.addObject("msg", "Pending");
		mav.addObject("comList", comList);

		return mav;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.beta.controllerImpl.CompanyVendorApplicationController#getAllApplications
	 * (javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/companyApplication", method = RequestMethod.GET)
	public ModelAndView getAllApplications(HttpSession session) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		List<CompanyApplication> vendorApplicationDetails = getAllApplications(account.getCompanyReferenceNumber());

		ModelAndView mav = new ModelAndView("vendorApplicationHistory");
		mav.addObject("vendorApplicationDetails", vendorApplicationDetails);
		mav.addObject("msg", "All");
		return mav;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.beta.controllerImpl.CompanyVendorApplicationController#
	 * showDetailsOfApplication(java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/companyApplicationHistory/{applicationRef}", method = RequestMethod.GET)
	public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef, HttpSession session) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(),
				applicationRef);
		List<DocumentFiles> files = getApplicationDocumentsFromDropBox(applicationRef);

		ModelAndView mav = new ModelAndView("companyApplicationDetails");
		mav.addObject("companyApplication", companyApplication);
		mav.addObject("files", files);
		return mav;

	}

	private List<DocumentFiles> getApplicationDocumentsFromDropBox(String applicationRef) {
		List<Documents> documents = documentsService.findByApplicationRef(applicationRef);
		List<DocumentFiles> documentFiles = new ArrayList<>();
		for (Documents d : documents) {
			String oringalFilename = d.getOriginalFileName();
			documentFiles.add(new DocumentFiles(d.getUrl(), oringalFilename));
		}
		return documentFiles;
	}

	private List<CompanyApplication> getAllApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByVendorRef(companyReferenceNumber);
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> getAllPendingAndVettingApplications(String companyReferenceNumber) {
		List<Application> applicationList = applicationService.findByStatusAndVendorRef(ApplicationStatus.PENDING,
				companyReferenceNumber);
		applicationList
				.addAll(applicationService.findByStatusAndVendorRef(ApplicationStatus.VETTING, companyReferenceNumber));
		return convertToCompanyApplication(applicationList);
	}

	private List<CompanyApplication> convertToCompanyApplication(List<Application> applicationList) {
		List<CompanyApplication> vendorApplicationDetails = new ArrayList<>();
		for (Application a : applicationList) {
			Company c = companyService.findbyRefNo(a.getCompanyReferenceNumber());
			CompanyApplication vendorApplication = new CompanyApplication(c, a);
			vendorApplicationDetails.add(vendorApplication);
		}
		return vendorApplicationDetails;
	}

	private CompanyApplication getCompanyApplication(String companyReferenceNumber, String applicationRef) {
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		if (!application.getVendorReferenceNumber().equals(companyReferenceNumber))
			throw new VendorMgmtException(
					"Company Reference number does not match application's company reference number");
		else {
			Company company = companyService.findbyRefNo(application.getCompanyReferenceNumber());
			return new CompanyApplication(company, application);
		}
	}

}
