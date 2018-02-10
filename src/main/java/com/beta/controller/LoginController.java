package com.beta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.UserAccount;



@Controller
public class LoginController {
	
	private final String prefixURL = "views";

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	 
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new UserAccount());
    return mav;
  }
//  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
//  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
//  @ModelAttribute("login") UserAccount userAccount) {
//    ModelAndView mav = null;
//    //User user = userService.validateUser(login);
//    if (userAccount.getUserName().equalsIgnoreCase("yifan") && userAccount.getPassword().equalsIgnoreCase("123")) {
//    	String url = "dashboard";
//    mav = new ModelAndView(url);
//    mav.addObject("firstname", "John");
//    } else {
//    	String url = "error";
//    mav = new ModelAndView(url);
//    mav.addObject("message", "Username or Password is wrong!!");
//    }
//    return mav;
//  }
}