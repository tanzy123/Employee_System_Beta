package com.beta.controllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.Department;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Role;
import com.beta.exception.VendorMgmtException;
import com.beta.services.DepartmentService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RoleService;
@Controller
@RequestMapping("/")
@SessionAttributes
public class EmployeeManagementControllerImpl {
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;

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
			@RequestParam(value = "departmentName") String departmentName,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password
			)
	{
		EmployeeAccount employeeAccount =new EmployeeAccount();
		Department employeeDepartment = new Department();
		Role employeeRole=new Role();
		String companyReferenceNumber=session.getAttribute("companyRefNumber").toString();
		
		employeeAccount.setContactNumber(contactNumber);
		employeeAccount.setCompanyReferenceNumber(companyReferenceNumber);
		
		//need to check if department doesn't exist, haven't done yet
		employeeDepartment = departmentService.findByNameAndCompanyRef(departmentName, companyReferenceNumber);
		employeeAccount.setDepartment(employeeDepartment);
		
		//need to check if role doesn't exist, haven't done yet
		employeeRole = roleService.findByCompanyReferenceNumberAndRole(companyReferenceNumber, role);
		employeeAccount.setRole(employeeRole);
		employeeAccount.setEmployeeEmail(employeeEmail);
		employeeAccount.setEmployeeId(employeeId);
		employeeAccount.setUserName(userName);
		employeeAccount.setPassword(password);
		
		try{
		employeeAccountService.createNewAccount(employeeAccount);
		}catch(VendorMgmtException e)
		{
			ModelAndView mav=new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
		
		ModelAndView mav = new ModelAndView("employeemanagement");
		return mav;
		
	}
	
	@RequestMapping(value = "/showSearchEmployee", method = RequestMethod.GET)
	public ModelAndView showSearchEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("searchEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	
	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public ModelAndView searchEmployee(@RequestParam(value = "employeeUserName") String employeeUserName) {

		
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("employee", employeeAccountService.findByUserName(employeeUserName));
		
		return mav;
	}
	
	
	@RequestMapping(value = "/showDeleteEmployee", method = RequestMethod.GET)
	public ModelAndView showDeleteEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("deleteEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	@RequestMapping(value = "/deleteEmployee", method = {RequestMethod.DELETE,RequestMethod.GET})
	public ModelAndView deleteEmployee(@RequestParam(value = "employeeId") String employeeId) throws NumberFormatException, Exception {

		
		
		try {
			
			employeeAccountService.deleteIfExisting(Long.parseLong(employeeId));
		} catch (VendorMgmtException e) {
			
		}
		
		
		return null;
	}
	@RequestMapping(value = "/showUpdateEmployee", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("updateEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
//	@RequestMapping(value = "/searchEmployeeToUpdate", method = RequestMethod.GET)
//	public ModelAndView searchEmployeeToUpdate(@RequestParam(value = "employeeUserName") String employeeUserName) {
//
//		
//		
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("employee", employeeAcoountService.findByUserName(employeeUserName));
//		
//		return mav;
//	}
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpSession session,@RequestParam(value = "employeeUserName") String employeeUserName,
			@RequestParam(value = "employeeUserName") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "departmentName") String departmentName,
			@RequestParam(value = "userName") String userName) {

		
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("employee", employeeAcoountService.findByUserName(employeeUserName));
//		
		EmployeeAccount employeeAccount =new EmployeeAccount();
		Department employeeDepartment = new Department();
		Role employeeRole=new Role();
		String companyReferenceNumber=session.getAttribute("companyRefNumber").toString();
		
		employeeAccount.setContactNumber(contactNumber);
		employeeAccount.setCompanyReferenceNumber(companyReferenceNumber);
		
		employeeDepartment.setCompanyReferenceNumber(session.getAttribute("companyRefNumber").toString());
		employeeDepartment.setDepartmentName(departmentName);
		employeeDepartment.setDepartmentId(departmentService.findByNameAndCompanyRef(departmentName, companyReferenceNumber).getDepartmentId());
		employeeAccount.setDepartment(employeeDepartment);
		
		employeeRole.setCompanyReferenceNumber(companyReferenceNumber);
		employeeRole.setRole(role);
		employeeRole.setRoleId(roleService.findByCompanyReferenceNumberAndRole(companyReferenceNumber, role).getRoleId());
		employeeAccount.setRole(employeeRole);
		
		employeeAccount.setEmployeeEmail(employeeEmail);
		employeeAccount.setEmployeeId(employeeId);
		employeeAccount.setUserName(userName);
		
		employeeAccountService.validateAccount(employeeAccount);
		employeeAccountService.saveOrUpdate(employeeAccount);
		
		return null;
	}
}
