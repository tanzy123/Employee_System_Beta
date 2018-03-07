package com.beta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public interface CompanyVendorApplicationController {

	ModelAndView getAllPendingAndVettingApplications(HttpSession session);

	ModelAndView getAllApplications(HttpSession session);

	ModelAndView showDetailsOfApplication(String applicationRef, HttpSession session);

//	ModelAndView getAllPendingAndVettingApplicationsWithEmpSearch(HttpSession session);

	ModelAndView getAllPendingAndVettingApplicationsWithEmpSearch(HttpSession session, String comName);

}