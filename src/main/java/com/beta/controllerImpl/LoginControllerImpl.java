package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.LoginController;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.UserAccount;
import com.beta.services.EmployeeAccountService;
import com.beta.services.impl.EmployeeAccountServiceImpl;



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
  @RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
  public ModelAndView loginVerification(@RequestParam(value="username") String username, @RequestParam(value="password") String password, @RequestParam(value="selectLoginType") String loginType) 
  {
    ModelAndView mav = null;
    //User user = userService.validateUser(login);
    if (loginType.equals("EMPLOYEE"))
    {
   
//    EmployeeAccount employeeAccount;
//    EmployeeAccountService employeeAccountService;
//    employeeAccount.setUserName(username);
//    employeeAccount.setPassword(password);
    //if(username.equals(employeeAccountService.)&&password.equals(employeeAccountService).)
    mav = new ModelAndView("admin");
    
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