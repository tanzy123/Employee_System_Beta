package com.beta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface LoginController {

	ModelAndView showLogin(HttpSession session);


}