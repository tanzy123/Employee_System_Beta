package com.beta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface CompanyInfoUpdateController {

	ModelAndView Registration(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	ModelAndView StoreNewCompanyInfo(String companyReferenceNumber, String companyName, String companyAddress,
			String companyEmail, String contactNumber, String companyWebsite, String turnover, String userName,
			String password);

}