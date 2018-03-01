package com.beta.controllerImpl;

import java.util.Arrays;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.RoleService;

@Controller
public class RoleUpdateImpl {

	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/updateRole", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<Role> rolelist = roleService.findByCompanyRef(account.getCompanyReferenceNumber());

		ModelAndView mav = new ModelAndView("updateRole");
		mav.addObject("rolelist",rolelist);
		
		return mav;
	}
	
	@RequestMapping(value = "/storeNewRoleInfo", method= RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(HttpSession session,
			@RequestParam(value = "roleName") String roleName)
		
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<String> theList = Arrays.asList(roleName.split(","));
		for (String s: theList) {
		Role role = new Role();
		role.setCompanyReferenceNumber(account.getCompanyReferenceNumber());
		role.setRole(s);
		roleService.saveOrUpdate(role);
		}
		
		ModelAndView successMAV = new ModelAndView("redirect:updateRole");
		
		return successMAV;
		
	}
	
	@RequestMapping(value = "/deleteRoleInfo", method= RequestMethod.GET)
	public ModelAndView DeleteCompanyInfo(HttpSession session, HttpServletRequest request)
			
		
	{
		
		String depid = request.getParameter("depid");
		Long did = Long.parseLong(depid);
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
	
		
		
		roleService.removeRole(did);
		
		ModelAndView successMAV = new ModelAndView("redirect:updateRole");
		
		return successMAV;
		
	}
}
