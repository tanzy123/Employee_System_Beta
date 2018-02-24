package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.LoginController;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.UserAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;
import com.beta.services.EmployeeAccountService;

@Controller
@RequestMapping("/")
@SessionAttributes
public class LoginControllerImpl implements LoginController {

	// private final String prefixURL = "views";
	
	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyValidation companyValicationService;
	
	@Autowired
	CompanyAdminstratorAccountService companyAdminAccountService;
	
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new UserAccount());
		return mav;
	}

	@RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
	public ModelAndView loginVerification(HttpSession session,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "selectLoginType") String loginType) 
	{
		ModelAndView mav = null;
		session.setAttribute("username", username);
		session.setAttribute("companyRefNumber", companyAdminAccountService.findByUserName(username).getCompanyReferenceNumber());
		EmployeeAccount employeeAccount = new EmployeeAccount();
		if (loginType.equals("EMPLOYEE")) {
			
			employeeAccount.setUserName(username);
			employeeAccount.setPassword(password);
			try {
				employeeAccountService.validateAccount(employeeAccount);
				mav = new ModelAndView("dashboardcompany");
			} catch (VendorMgmtException e) {
				mav = new ModelAndView("error");
				mav.addObject("message", e);
			}

		} else if (loginType.equals("COMPANY_ADMINISTRATOR")) {
			CompanyAdministratorAccount companyAdministratorAccount = new CompanyAdministratorAccount();
			companyAdministratorAccount.setUserName(username);
			companyAdministratorAccount.setPassword(password);
			try {
				companyAdminAccountService
						.validateAccount(companyAdministratorAccount);

				

				mav = new ModelAndView("redirect:/dashboardcompany");

			} catch (VendorMgmtException e) {
				mav = new ModelAndView("error");
				mav.addObject("message", e);
			}
		}
		return mav;
	}
	
	
}