package com.beta.controllerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.EmployeeAccount;
import com.beta.orm.service.EmployeeAccountService;

@Controller
public class PendingVendorApplicationControllerImpl {

	// @Autowired - Zhiyi implementation

	// if accept -> send email -> call the method
	// if reject -> send email -> call the reject email method.

	@Autowired
	EmployeeAccountService employeeAccountService;

	@RequestMapping(value = "/pendingvendorapplicationview2", method = RequestMethod.GET)

	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		ModelAndView mav = new ModelAndView("pendingvendorapplicationview");
		mav.addObject("employeeManagement", employeeAccountService.findAll());
		return mav;

	}

	@RequestMapping(value = "/pendingvendorapplicationview")
	public ModelAndView listEmployee(ModelAndView model, HttpSession session) throws IOException {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");

		List<EmployeeAccount> listEmployee = employeeAccountService.findAll();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("pendingvendorapplicationview");
		return model;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Map<String, Object> map, HttpSession session)

	{
		map.put("vendorApplicationList", employeeAccountService.findAll());

		return "pendingvendorapplication/pendingvendorapplicationview";
	}

}
