package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.Department;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Role;
@Controller
@RequestMapping("/")
public class EmployeeManagementImpl {

	@RequestMapping(value = "/employeeManagement", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("employeemanagement");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	@RequestMapping(value = "/showCreateEmployee", method = RequestMethod.GET)
	public ModelAndView showCreaeteEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("createEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ModelAndView createEmployee(HttpSession session,@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "department") String department)
	{
		EmployeeAccount employeeAccount=new EmployeeAccount();
		Department employeeDepartment = new Department();
		Role employeeRole=new Role();
		employeeAccount.setAccountId(Long.parseLong(employeeId));
		employeeAccount.setContactNumber(contactNumber);
//		employeeAccount.setDepartment(department);
//		employeeAccount.setRole(role);
		
		return null;
		
	}
}
