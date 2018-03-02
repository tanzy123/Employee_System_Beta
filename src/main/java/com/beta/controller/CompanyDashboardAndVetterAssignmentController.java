package com.beta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.VetterList;

public interface CompanyDashboardAndVetterAssignmentController {

	ModelAndView showDashboard(HttpSession session);

	ModelAndView showVetters(HttpSession session);

	ModelAndView getApplicationsToBeVetted(HttpSession session);

	ModelAndView showDetailsOfApplication(String applicationRef, HttpSession session);

	ModelAndView assignVetters(String applicationRef, HttpSession session);

	ModelAndView Registration(HttpSession session, String empName);

	ModelAndView setVetters(HttpSession session);

}