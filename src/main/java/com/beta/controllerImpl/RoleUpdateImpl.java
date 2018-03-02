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
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.RoleService;

@Controller
public class RoleUpdateImpl {

	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/updateRole", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		try {
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<Role> rolelist = roleService.findByCompanyRef(account.getCompanyReferenceNumber());

		ModelAndView mav = new ModelAndView("updateRole");
		mav.addObject("rolelist",rolelist);
		
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
	    	mav.addObject("message", "updating of role could not be achieved.");
	    	
		   return mav;
		}
	}
	
	@RequestMapping(value = "/storeNewRoleInfo", method= RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(HttpSession session,
			@RequestParam(value = "roleName") String roleName)
		
	{
		try {
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
	    	mav.addObject("message", "Storing of new role could not be carried out.");
	    	
		   return mav;
		}
		
	}
	
	@RequestMapping(value = "/deleteRoleInfo", method= RequestMethod.GET)
	public ModelAndView DeleteCompanyInfo(HttpSession session, HttpServletRequest request)
			
		
	{
		try
		{
		String depid = request.getParameter("depid");
		Long did = Long.parseLong(depid);
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
	
		
		
		roleService.removeRole(did);
		
		ModelAndView successMAV = new ModelAndView("redirect:updateRole");
		
		return successMAV;
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
	    	mav.addObject("message", "Deleting CompanyInfo is not possible");
	    	
		   return mav;
		}
		
	}
}
