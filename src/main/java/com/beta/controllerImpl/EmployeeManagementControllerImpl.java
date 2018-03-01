package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beta.entity.Application;
import com.beta.entity.Department;
import com.beta.entity.EmailPurposeType;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Role;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.service.NotificationService;
import com.beta.services.DepartmentService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RoleService;
import com.beta.unused.RegistrationService;

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
	
	@Autowired
	NotificationService notificationService;

	// --------------------------------------load jsp pages----------------------------------------------------------------------------
	@RequestMapping(value = "/employeeManagement", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		ModelAndView mav = new ModelAndView("employeemanagement");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
		}catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "employee Management view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/showCreateEmployee", method = RequestMethod.GET)
	public ModelAndView showCreaeteEmployee(HttpServletRequest request,

	HttpServletResponse response, HttpSession session) {
		try {
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
		}catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", " Employee created view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/showSearchEmployee", method = RequestMethod.GET)
	public ModelAndView showSearchEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		try
		{
		ModelAndView mav = new ModelAndView("searchEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "Search Employee view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/showUpdateEmployee", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployee(HttpServletRequest request,
			HttpServletResponse response) {

		try
		{
		ModelAndView mav = new ModelAndView("updateEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", " update employee view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/showDeleteEmployee", method = RequestMethod.GET)
	public ModelAndView showDeleteEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		ModelAndView mav = new ModelAndView("deleteEmployee");
		mav.addObject("employeeManagement", new EmployeeAccount());
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "Deleted employee view could not be displayed");
	    	
		   return mav;
		}
	}

	// ------------------------------------------------Create-------------------------------------------------------------------------

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ModelAndView createEmployee(HttpSession session,
			@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "employeeName") String employeeName,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "department") String departmentName,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		EmployeeAccount employeeAccount = new EmployeeAccount();
		Department employeeDepartment = new Department();

		Role employeeRole=new Role();
		String companyReferenceNumber=session.getAttribute("companyRefNumber").toString();
		
		employeeAccount.setEmployeeName(employeeName);


		employeeAccount.setCompanyReferenceNumber(companyReferenceNumber);

		// need to check if department doesn't exist, haven't done yet
		employeeDepartment = departmentService.findByNameAndCompanyRef(departmentName, companyReferenceNumber);
		employeeAccount.setDepartment(employeeDepartment);

		// need to check if role doesn't exist, haven't done yet
		employeeRole = roleService.findByCompanyReferenceNumberAndRole(companyReferenceNumber, role);
		employeeAccount.setRole(employeeRole);
		employeeAccount.setEmployeeEmail(employeeEmail);
		employeeAccount.setIsValidated(true);
		employeeAccount.setUserName(userName);
		employeeAccount.setPassword(password);
		if (employeeAccountService.checkDuplicateEmployeeIdInSameCompany(companyReferenceNumber, employeeId).isEmpty()) {
			employeeAccount.setEmployeeId(employeeId);
		} else {
			mav = new ModelAndView("error");
			mav.addObject("message", "the same Employee ID already exist!");
			return mav;
		}

		

		
		try{
		employeeAccountService.createNewAccount(employeeAccount);
		}catch(UserException e)
		{
			mav=new ModelAndView("error");

			mav.addObject("message", e.getMessage());
			return mav;
		} catch (VendorMgmtException e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error");
			return mav;
		} catch (Exception e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error, please contact System administrator");
			return mav;
		}
		String[] myStringArray = {};
		String message= "Username : "+userName+"Employee ID : "+employeeId+"Employee Email : "+employeeEmail+"Role :" +role+"Department : "+departmentName;
		notificationService.sendEmailWithPurposeCC(employeeEmail, myStringArray, "Employee Account Created", message, "", EmailPurposeType.AccountCreated);
		
		mav = new ModelAndView("employeemanagement");
		return mav;
		

	}

	// --------------------------------------------------------------search----------------------------------------------------------
	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public ModelAndView searchEmployee(
			@RequestParam(value = "employeeUserName") String employeeUserName) {
		try {
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee",employeeAccountService.findByUserName(employeeUserName));
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "Employee could not be found!");
	    	
		   return mav;
		}
	}

	// ---------------------------------------------------------------update---------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchEmployeeToUpdate(HttpSession session, HttpServletRequest req) {
		try {
		String userName = req.getParameter("userName");
		ModelAndView mav = new ModelAndView("updateEmployeeUpdate");
		EmployeeAccount employee = new EmployeeAccount();

		employee = employeeAccountService.findByUserName(userName);
		mav.addObject("updateEmployeeUpdate", employee);
		req.getSession().setAttribute("employee", employee);

		String companyReferenceNumber = session.getAttribute("companyRefNumber").toString();
		List<String> departmentNames = generateListOfDepartmentOfCompany(companyReferenceNumber);
		List<String> roleNames = generateListOfRoleOfCompany(companyReferenceNumber);
		mav.addObject("departmentNames", departmentNames);
		mav.addObject("roleNames", roleNames);
		
		
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "Searching employee to be updated could not be displayed.");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/showUpdateEdit", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployeeEdit(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@ModelAttribute("updateEmployeeUpdate") EmployeeAccount employee) {

		try {
		ModelAndView mav = new ModelAndView("updateEmployeeUpdate");
		mav.addObject("employee", employee);
		
		
		return mav;
		}
		catch(VendorMgmtException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(UserException e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", e.getMessage());
	    	
		   return mav;
		}
		catch(Exception e)
		{
        	ModelAndView mav = new ModelAndView("error");
	    	mav.addObject("message", "show update employee edit view could not be displayed");
	    	
		   return mav;
		}
	}

	@RequestMapping(value = "/edited", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpServletRequest request,HttpSession session,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "employeeEmail") String employeeEmail,
			@RequestParam(value = "role") String role,
			@RequestParam(value = "department") String departmentName,
			@ModelAttribute("updateEmployeeUpdate") EmployeeAccount employee) throws Exception {

		
		ModelAndView mav = new ModelAndView("updateEmployeeUpdate");
		String companyReferenceNumber = session
				.getAttribute("companyRefNumber").toString();

		// mav.addObject("employee",
		// employeeAccountService.findByUserName(request.getParameter("empUpdateSearchByUserName")));

		EmployeeAccount employeeAccount = new EmployeeAccount();
		Department employeeDepartment = new Department();
		Role employeeRole = new Role();

		employeeAccount.setUserName(userName);
				                                   // setComRefNo
		employeeAccount.setCompanyReferenceNumber(companyReferenceNumber);

		// setDept
		employeeDepartment = departmentService.findByNameAndCompanyRef(
				departmentName, companyReferenceNumber);
		employeeAccount.setDepartment(employeeDepartment);

		// setRole
		employeeRole = roleService.findByCompanyReferenceNumberAndRole(
				companyReferenceNumber, role);
		employeeAccount.setRole(employeeRole);

		// setEmail
		employeeAccount.setEmployeeEmail(employeeEmail);
		// setId
		employeeAccount.setEmployeeId(employeeId);

		try {
			employeeAccountService.saveOrUpdateByCompAdmin(employeeAccount);
		} catch(UserException e)
		{
			mav=new ModelAndView("error");

			mav.addObject("message", e.getMessage());
			return mav;
		} catch (VendorMgmtException e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error");
			return mav;
		} catch (Exception e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error, please contact System administrator");
			return mav;}
		String[] myStringArray = {};

		String message= "Username : "+userName+"Employee ID : "+employeeId+"Employee Email : "+employeeEmail+"Role :" +role+"Department : "+departmentName;
		notificationService.sendEmailWithPurposeCC(employeeEmail, myStringArray, "Employee Account Created", message, "", EmailPurposeType.AccountCreated);
		mav = new ModelAndView("employeemanagement");
	
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
//------------------------------------------------------delete------------------------------------------------------------
	@RequestMapping(value = "/deleteEmployee", method = { RequestMethod.DELETE,
			RequestMethod.GET })
	public ModelAndView deleteEmployee(
			@RequestParam(value = "userName") String userName)
			throws NumberFormatException, Exception {

		     ModelAndView mav=new ModelAndView();
		try {

			employeeAccountService.deleteByUserName(userName);
		} catch(UserException e)
		{
			mav=new ModelAndView("error");

			mav.addObject("message", e.getMessage());
			return mav;
		} catch (VendorMgmtException e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error");
			return mav;
		} catch (Exception e) {
			mav=new ModelAndView("error");
			mav.addObject("message", "System error, please contact System administrator");
			return mav;
		}

		mav.addObject("message", "employee deleted successfully!");
		return mav;
	}

	@RequestMapping(value = "/BackToEmployeemanagementPage", method = RequestMethod.GET)
	public ModelAndView createEmployeeBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView("employeemanagement");
		return mav;
	}
}
