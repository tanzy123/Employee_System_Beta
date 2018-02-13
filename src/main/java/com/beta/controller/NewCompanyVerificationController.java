package com.beta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface NewCompanyVerificationController {

	public abstract ModelAndView tokenPage(HttpServletRequest request,HttpServletResponse response);
}
