package com.beta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface CategoryUpdateController {

	ModelAndView Registration(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	ModelAndView StoreNewCompanyInfo(HttpSession session, String catName);

	ModelAndView DeleteCatInfo(HttpSession session, HttpServletRequest request);

}