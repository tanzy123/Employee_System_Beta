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
import com.beta.exception.UserException;
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
	public ModelAndView showLogin(HttpSession session) {
		session.invalidate();	
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
		
		if (loginType.equals("EMPLOYEE")) {
			EmployeeAccount employeeAccount = new EmployeeAccount();
			session.setAttribute("employeeRefNumber", employeeAccountService.findByUserName(username).getCompanyReferenceNumber());
			session.setAttribute("employeeName", employeeAccountService.findByUserName(username).getUserName());
			employeeAccount.setUserName(username);
			employeeAccount.setPassword(password);
			try {
				employeeAccountService.validateAccount(employeeAccount);
				mav = new ModelAndView("redirect:/employeeDashboard");
				
			} 
			catch (UserException e) 
			{
				mav=new ModelAndView("error");
				mav.addObject("message",e.getMessage());
				return mav;
			}
            catch (VendorMgmtException e) 
			{
				mav=new ModelAndView("error");
				mav.addObject("message",e.getMessage());
				return mav;
			}
            catch (Exception e) 
			{
				mav=new ModelAndView("error");
				mav.addObject("message","Invalid Employee log in credentials!");
				return mav;
			}
			}
			

		 else if (loginType.equals("COMPANY_ADMINISTRATOR")) 
		 {
			session.setAttribute("companyRefNumber", companyAdminAccountService.findByUserName(username).getCompanyReferenceNumber());
			CompanyAdministratorAccount companyAdministratorAccount = new CompanyAdministratorAccount();
			companyAdministratorAccount.setUserName(username);
			companyAdministratorAccount.setPassword(password);
			try {
				companyAdminAccountService.validateAccount(companyAdministratorAccount);
				mav = new ModelAndView("redirect:/dashboardcompany");

			} catch (UserException e) 
			{
				if (e.getMessage().equals("Account has not been validated yet")) 
				{
					mav = new ModelAndView("missingToken"); 
					mav.addObject("message", e.getMessage());
					return mav;
				}
				else 
				{
					

				   mav = new ModelAndView("error"); 
				   mav.addObject("message","the Log in credential is invalid!!");
				   return mav;
				
				}
			}
		}
		return mav;
		
	}
	
	
	

	
}
	
	