package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.CompanyInfoUpdateController;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.CompanyService;

@Controller
public class CompanyInfoUpdateImpl implements CompanyInfoUpdateController {
	
	@Autowired
	CompanyAdminstratorAccountService accountService;

	@Autowired
	CompanyService companyService;
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyInfoUpdateController#Registration(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/updateCompany", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		try {
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		
		Company com = companyService.findbyRefNo(account.getCompanyReferenceNumber());
		
		ModelAndView mav = new ModelAndView("companyUpdate");
		mav.addObject("companyUpdate", new Company());
		mav.addObject("company",com);
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
	    	mav.addObject("message", "Updating company is not successful!");
	    	
		   return mav;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.beta.controllerImpl.CompanyInfoUpdateController#StoreNewCompanyInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/storeNewCompanyInfo", method= RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(
			@RequestParam(value = "companyReferenceNumber") String companyReferenceNumber,
			@RequestParam(value = "companyName") String companyName,
			@RequestParam(value = "companyAddress") String companyAddress,
			@RequestParam(value = "companyEmail") String companyEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "companyWebsite") String companyWebsite,
			@RequestParam(value = "turnover") String turnover,
			@RequestParam(value = "username") String userName,
			@RequestParam(value = "password") String password)
		
	{
		try
		{
		ModelAndView mav = null;
		Company company = new Company();
		company.setCompanyReferenceNumber(companyReferenceNumber);
		company.setCompanyName(companyName);
		company.setCompanyAddress(companyAddress);
		company.setCompanyEmail(companyEmail);
		company.setCompanyWebsite(companyWebsite);
		company.setContactNumber(contactNumber);
		company.setTurnover(Long.parseLong(turnover));
	
		companyService.saveOrUpdate(company);
		
		ModelAndView successMAV = new ModelAndView("updateSuccess");
		successMAV.addObject("username", userName);
		return successMAV;
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
	    	mav.addObject("message", "Storing company Info is not successful!");
	    	
		   return mav;
		}
		
	}
	
	
	
}
