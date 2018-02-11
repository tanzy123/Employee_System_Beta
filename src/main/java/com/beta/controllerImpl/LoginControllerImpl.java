package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.LoginController;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.UserAccount;



@Controller
public class LoginControllerImpl implements LoginController {
	
	//private final String prefixURL = "views";


@Override
@RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	 
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new UserAccount());
    return mav;
  }
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("login") EmployeeAccount employeeAccount, CompanyAdministratorAccount companyAdministratorAccount) {
    ModelAndView mav = null;
    //User user = userService.validateUser(login);
    if (("$username").equalsIgnoreCase(employeeAccount.getUserName()) &&  ("$password").equalsIgnoreCase(employeeAccount.getPassword()))
    {
    String url = "dashboard";
    String role="Employee";
    
    mav = new ModelAndView(url);
    
    } 
    else
    {
    	String url = "error";
    mav = new ModelAndView(url);
    mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }
}