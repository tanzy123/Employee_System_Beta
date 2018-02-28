
package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
//--------------------------------------load jsp pages----------------------------------------------------------------------------
	@RequestMapping(value = "/employeeManagement", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("employeemanagement");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}

	@RequestMapping(value = "/showCreateEmployee", method = RequestMethod.GET)
	public ModelAndView showCreaeteEmployee(HttpServletRequest request,

			HttpServletResponse response, HttpSession session) {

		ModelAndView mav = new ModelAndView("createEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		mav.addObject("department", new Department());
		mav.addObject("role", new Role());

		List<String> departmentNames = generateListOfDepartmentOfCompany(session
				.getAttribute("companyRefNumber").toString());
		List<String> roleNames = generateListOfRoleOfCompany(session
				.getAttribute("companyRefNumber").toString());
		mav.addObject("departmentNames", departmentNames);
		mav.addObject("roleNames", roleNames);
		return mav;
	}
	
	@RequestMapping(value = "/showSearchEmployee", method = RequestMethod.GET)
	public ModelAndView showSearchEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("searchEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	
	@RequestMapping(value = "/showUpdateEmployee", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployee(HttpServletRequest request,HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("updateEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	
	@RequestMapping(value = "/showDeleteEmployee", method = RequestMethod.GET)
	public ModelAndView showDeleteEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("deleteEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
	}
	
	//------------------------------------------------CRUD-------------------------------------------------------------------------

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ModelAndView createEmployee(HttpSession session,@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "contactNumber") String contactNumber,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "department") String departmentName,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password
			)
	{
		ModelAndView mav=new ModelAndView();
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
		if(employeeAccountService.checkDuplicateEmployeeIdInSameCompany(companyReferenceNumber, employeeId).isEmpty())
	   {
			employeeAccount.setEmployeeId(employeeId);
	   }
		else
		{
			mav=new ModelAndView("error");
			mav.addObject("message", "the same Employee ID already exist!");
			return mav;
		}
		
		employeeAccount.setUserName(userName);
		employeeAccount.setPassword(password);
		
		try{
		employeeAccountService.createNewAccount(employeeAccount);
		}catch(VendorMgmtException e)
		{
			mav=new ModelAndView("error");
			mav.addObject("message", e.getMessage());
			return mav;
		}
		
		mav = new ModelAndView("employeemanagement");
		return mav;
		
	}

	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public ModelAndView searchEmployee(@RequestParam(value = "employeeUserName") String employeeUserName) 
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee", employeeAccountService.findByUserName(employeeUserName));
		return mav;
	}
	



	 @RequestMapping(value = "/searchEmployeeToUpdate", method =RequestMethod.GET)
	 public ModelAndView searchEmployeeToUpdate(HttpServletRequest request,HttpSession session,@ModelAttribute("EmployeeToUpdate") EmployeeAccount EmployeeToUpdate,@RequestParam(value ="employeeUserName") String employeeUserName) 
	 {
	 ModelAndView mav=new ModelAndView("updateEmployee");
	// mav.addObject("employee", employeeAccountService.findByUserName(employeeUserName));
	// mav.addObject("department", new Department());
	// mav.addObject("role", new Role());
	 mav.addObject("employee", employeeAccountService.findByUserName(EmployeeToUpdate.getUserName()));
	 mav.addObject("department", EmployeeToUpdate.getRole());
	 mav.addObject("department", EmployeeToUpdate.getDepartment());
	 mav.addObject("EmployeeToUpdate", EmployeeToUpdate);

		List<String> departmentNames = generateListOfDepartmentOfCompany(session.getAttribute("companyRefNumber").toString());
		List<String> roleNames = generateListOfRoleOfCompany(session.getAttribute("companyRefNumber").toString());
		mav.addObject("departmentNames", departmentNames);
		mav.addObject("roleNames", roleNames);
	 return mav;
	 }
	 
	@RequestMapping(value = "/updateEmployee", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateEmployee(HttpSession session,
			@RequestParam(value = "employeeUserName") String employeeUserName,
			@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "departmentName") String departmentName)
	{

		    ModelAndView mav=new ModelAndView("updateEmployee");
		    mav.addObject("employee", employeeAccountService.findByUserName(employeeUserName));
		    mav.addObject("department", new Department());
			mav.addObject("role", new Role());

			List<String> departmentNames = generateListOfDepartmentOfCompany(session.getAttribute("companyRefNumber").toString());
			List<String> roleNames = generateListOfRoleOfCompany(session.getAttribute("companyRefNumber").toString());
			mav.addObject("departmentNames", departmentNames);
			mav.addObject("roleNames", roleNames);

		EmployeeAccount employeeAccount = new EmployeeAccount();
		Department employeeDepartment = new Department();
		Role employeeRole = new Role();
		String companyReferenceNumber = session.getAttribute("companyRefNumber").toString();

		//setComRefNo
		employeeAccount.setCompanyReferenceNumber(companyReferenceNumber);

		//setDept
		employeeDepartment = departmentService.findByNameAndCompanyRef(departmentName, companyReferenceNumber);
		employeeAccount.setDepartment(employeeDepartment);
		

		//setRole
		employeeRole = roleService.findByCompanyReferenceNumberAndRole(companyReferenceNumber, role);
		employeeAccount.setRole(employeeRole);

		//setEmail
		employeeAccount.setEmployeeEmail(employeeEmail);
		//setId
		employeeAccount.setEmployeeId(employeeId);
		

		employeeAccountService.validateAccount(employeeAccount);
		employeeAccountService.saveOrUpdate(employeeAccount);

		return mav;
	}

	private List<String> generateListOfDepartmentOfCompany(
			String companyReferenceNumber) {
		List<Department> departmentList = departmentService
				.findByCompanyRef(companyReferenceNumber);
		List<String> categoryNames = new ArrayList<>();
		for (Department d : departmentList)
			categoryNames.add(d.getDepartmentName());
		return categoryNames;
	}

	private List<String> generateListOfRoleOfCompany(
			String companyReferenceNumber) {
		List<Role> roleList = roleService
				.findByCompanyRef(companyReferenceNumber);
		List<String> categoryNames = new ArrayList<>();
		for (Role R : roleList)
			categoryNames.add(R.getRole());
		return categoryNames;
	}
	


	@RequestMapping(value = "/deleteEmployee", method = { RequestMethod.DELETE,
			RequestMethod.GET })
	public ModelAndView deleteEmployee(
			@RequestParam(value = "employeeId") String employeeId)
			throws NumberFormatException, Exception {

		try {

			employeeAccountService.deleteIfExisting(Long.parseLong(employeeId));
		} catch (VendorMgmtException e) {

		}

		return null;
	}
	@RequestMapping(value = "/BackToEmployeemanagementPage", method = RequestMethod.GET)
	public ModelAndView createEmployeeBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) 
	{
		ModelAndView mav = new ModelAndView("employeemanagement");
		return mav;
	}
}
