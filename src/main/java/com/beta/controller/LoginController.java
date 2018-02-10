package com.beta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public interface LoginController {

	public abstract ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response);


}