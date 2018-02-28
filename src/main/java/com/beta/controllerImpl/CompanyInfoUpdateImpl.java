package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;

@Controller
public class CompanyInfoUpdateImpl {
	
	@Autowired
	CompanyAdminstratorAccountService accountService;

	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/updateCompany", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		session.setAttribute("account", account);
		
		Company com = companyService.findbyRefNo(account.getCompanyReferenceNumber());
		
		ModelAndView mav = new ModelAndView("companyUpdate");
		mav.addObject("companyUpdate", new Company());
		mav.addObject("company",com);
		return mav;
	}
	
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
	
	
	
}
