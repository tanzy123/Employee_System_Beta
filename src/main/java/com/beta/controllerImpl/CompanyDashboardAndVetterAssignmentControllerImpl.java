package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.CompanyDashboardAndVetterAssignmentController;
import com.beta.controller.object.CompanyApplication;
import com.beta.controller.object.DocumentFiles;
import com.beta.controller.object.VetterList;
import com.beta.controller.object.VetterdDTO;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Documents;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.DocumentsService;
import com.beta.orm.service.EmployeeAccountService;
import com.beta.orm.service.RequirementService;
import com.beta.service.VendorVettingProcess;

@Controller
public class CompanyDashboardAndVetterAssignmentControllerImpl implements CompanyDashboardAndVetterAssignmentController {
	
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	RequirementService requirementService;
	
	@Autowired
	VendorVettingProcess vendorVettingProcess;
	
	@Autowired
	DocumentsService documentsService;
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#showDashboard(javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/dashboardcompany", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpSession session) throws Exception {
		if (session.getAttribute("username")==null)
			throw new Exception();
		try {
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		
		ModelAndView mav = new ModelAndView("dashboardcompany");
		mav.addObject("username", session.getAttribute("username").toString());
		return mav;
		} catch (VendorMgmtException e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
		catch (UserException e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
		catch (Exception e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", "Company page could not be displayed");
			return mav;
		}
	}

	@Override
	@RequestMapping(value = "/vetterManagement", method = RequestMethod.GET)  
    public ModelAndView showVetters(HttpSession session){
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
        List<CompanyApplication> companyApplicationlist = getListOfApplicationsToBeVetted(account.getCompanyReferenceNumber());
        ModelAndView mav = new ModelAndView("vettermanagement");
        mav.addObject("companyApplicationlist", companyApplicationlist);
     
        return mav;
		} catch (VendorMgmtException e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
			
		}
		catch (UserException e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
			
		}
		catch (Exception e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", "Vetter list could not be display.");
			return mav;
			
		}
		
    }
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#getApplicationsToBeVetted(javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/pendingApplication", method = RequestMethod.GET)  
    public ModelAndView getApplicationsToBeVetted(HttpSession session){
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
        List<CompanyApplication> companyApplicationlist = getListOfApplicationsToBeVetted(account.getCompanyReferenceNumber());
        ModelAndView mav = new ModelAndView("vettermanagement");
        mav.addObject("companyApplicationlist", companyApplicationlist);
     
        return mav;
        
		} catch(VendorMgmtException e)
		
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
		
		catch (Exception e)
		{
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", "Pending application page could not be displayed.");
			return mav;
			
		}
    }
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#showDetailsOfApplication(java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/vendorApplication/{applicationRef}", method = RequestMethod.GET)  
    public ModelAndView showDetailsOfApplication(@PathVariable String applicationRef, HttpSession session){
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(), applicationRef);
		List<DocumentFiles> files = getApplicationDocumentsFromDropBox(applicationRef);
        ModelAndView mav = new ModelAndView("vendorApplicationDetails");
        mav.addObject("companyApplication", companyApplication);
        mav.addObject("files", files);
        return mav;
    } catch(VendorMgmtException e)
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
	    	mav.addObject("message", "Vetter applications could not be displayed");
	    	
		   return mav;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#assignVetters(java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/assignVetter/{applicationRef}", method = RequestMethod.GET)  
    public ModelAndView assignVetters(@PathVariable String applicationRef, HttpSession session){
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try
		{
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		CompanyApplication companyApplication = getCompanyApplication(account.getCompanyReferenceNumber(), applicationRef);
		List<VetterdDTO> vetters = new ArrayList<>();
		session.setAttribute("vetters", vetters);
		session.setAttribute("applicationRef", applicationRef);
        ModelAndView mav = new ModelAndView("assignVetter"); // used to be assignVetter
        mav.addObject("companyApplication", companyApplication);
        mav.addObject("vetters", vetters);
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
	    	mav.addObject("message", "Assign vetter view could not be displayed");
	    	
		   return mav;
		}		
    }
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#Registration(javax.servlet.http.HttpSession, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "assignVetter/findByEmpName", method = RequestMethod.GET)
	public ModelAndView Registration(HttpSession session, @RequestParam(value = "empName") String empName) {
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());

			List<EmployeeAccount> empList = employeeAccountService.findByEmpNameAndCompany(account.getCompanyReferenceNumber(), empName);
			List<VetterdDTO> vetters = (ArrayList<VetterdDTO>)session.getAttribute("vetters");

			session.setAttribute("vetters", vetters);
			ModelAndView mav = new ModelAndView("assignVetter");
			mav.addObject("vetters", vetters);
			mav.addObject("empList", empList);
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
	
	@RequestMapping(value = "addVetter/{empUsername}", method = RequestMethod.GET)
	public ModelAndView addVetter(HttpSession session, @PathVariable String empUsername) {
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());

			EmployeeAccount emp = employeeAccountService.findByUserName(empUsername);
			VetterdDTO v = new VetterdDTO();
			v.setUserName(emp.getUserName());
			
			List<VetterdDTO> vetters = (ArrayList<VetterdDTO>)session.getAttribute("vetters");
			vetters.add(v);
			for (int i=0;i<vetters.size();i++) {
				VetterdDTO vetter = vetters.get(i);
				vetter.setSequenceNo(i+1);
			}
			session.setAttribute("vetters", vetters);
			ModelAndView mav = new ModelAndView("assignVetter");
			mav.addObject("vetters", vetters);
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
	
	@RequestMapping(value = "deleteVetter/{empUsername}", method = RequestMethod.GET)
	public ModelAndView deleteVetter(HttpSession session, @PathVariable String empUsername) {
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());

			EmployeeAccount emp = employeeAccountService.findByUserName(empUsername);
			VetterdDTO v = new VetterdDTO();
			v.setUserName(emp.getUserName());
			
			List<VetterdDTO> vetters = (ArrayList<VetterdDTO>)session.getAttribute("vetters");
			for (Iterator<VetterdDTO> iterator = vetters.iterator(); iterator.hasNext();) {
				VetterdDTO vetter = iterator.next();
				if (vetter.getUserName().equals(empUsername)) {
					iterator.remove();
				}
			}
			for (int i=0;i<vetters.size();i++) {
				VetterdDTO vetter = vetters.get(i);
				vetter.setSequenceNo(i+1);
			}
			session.setAttribute("vetters", vetters);
			ModelAndView mav = new ModelAndView("assignVetter");
			mav.addObject("vetters", vetters);
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
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyDashboardAndVetterAssignmentController#setVetters(com.beta.controller.object.RequirementList, javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/setVetters", method = RequestMethod.POST)  
	@ResponseBody
    public ModelAndView setVetters(HttpSession session){
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		try {
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		List<VetterdDTO> list = (ArrayList<VetterdDTO>)session.getAttribute("vetters");
		List<Requirement> requirementList = new ArrayList<>();
		for (VetterdDTO v: list) {
			Requirement r = new Requirement();
			r.setSequence(v.getSequenceNo());
			r.setUserName(v.getUserName());
			r.setApplicationRef(session.getAttribute("applicationRef").toString());
			requirementList.add(r);
		}
		vendorVettingProcess.initialVettersAssignmentByApplicationRef(account.getCompanyReferenceNumber(), requirementList);
		return new ModelAndView("dashboardcompany");
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
	

	private List<DocumentFiles> getApplicationDocumentsFromDropBox(String applicationRef) {
		List<Documents> documents = documentsService.findByApplicationRef(applicationRef);
		List<DocumentFiles> documentFiles = new ArrayList<>();
		for (Documents d: documents) {
			String oringalFilename = d.getOriginalFileName();
			documentFiles.add(new DocumentFiles(d.getUrl(), oringalFilename));
		}
		return documentFiles;
	}
	@RequestMapping(value = "/BackToDashboardCompanyPage", method = RequestMethod.GET)
	public ModelAndView createEmployeeBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView("dashboardcompany");
		return mav;
	}
}
