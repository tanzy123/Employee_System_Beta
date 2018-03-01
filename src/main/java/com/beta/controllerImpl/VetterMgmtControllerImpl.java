package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beta.controller.object.CompanyApplication;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.entity.Role;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RequirementService;

@Controller
public class VetterMgmtControllerImpl {
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	RequirementService reqService;
	
	@Autowired
	EmployeeAccountService empAcctServ;
	
	@RequestMapping(value = "/vetterDisplay/{appRef}", method = RequestMethod.GET)  
    public ModelAndView displayVetters(@PathVariable String appRef,HttpSession session){
		
		 try
	        {
	        
		CompanyAdministratorAccount account = (CompanyAdministratorAccount)session.getAttribute("account");
		
        List <Requirement> reqList = reqService.findByApplicationRef(appRef);
        List<EmployeeAccount> vetterList= new ArrayList();
        for (Requirement q: reqList) {
        	EmployeeAccount a = empAcctServ.findByUserName(q.getUserName());
        	vetterList.add(a);
        	
        }
       
        ModelAndView mav = new ModelAndView("assignVetterByZQ");
        mav.addObject("vetterList", vetterList);
        mav.addObject("appRef", appRef);
        mav.addObject("comRef", account.getCompanyReferenceNumber());
     
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
	    	mav.addObject("message", "assigning of vetter could not be carried out.");
	    	
		   return mav;
		}
        
        
    }

	
	@RequestMapping(value = "/vetterDisplay/findByEmpName/{comRef}/{appRef}", method = RequestMethod.POST)
	public ModelAndView Registration(HttpSession session,@RequestParam(value = "empName") String empName,@PathVariable String comRef,@PathVariable String appRef)
	{
		try {
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		
		List<EmployeeAccount>empList=empAcctServ.findByEmpNameAndCompany(comRef,empName);

		ModelAndView mav = new ModelAndView("displayEmpSearch");
		mav.addObject("empList",empList);
		mav.addObject("appRef", appRef);
		
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
	    	mav.addObject("message", "Registration could not be carried out.");
	    	
		   return mav;
		}
        
		 
	}
	
	@RequestMapping(value = "/vetterDisplay/findByEmpName/111111/addVetInfo", method= RequestMethod.GET)
	public ModelAndView addVetterInfo(HttpSession session, HttpServletRequest request)
			
		
	{
		
		try {
		String id = request.getParameter("id");
		String appRef = request.getParameter("appRef");
		Long did = Long.parseLong(id);
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
	
		EmployeeAccount acct = empAcctServ.find(did); // This is the PROBLEM
		
		
		Requirement req = new Requirement();
		req.setUserName(acct.getUserName());
		req.setApplicationRef(appRef);
		req.setStatus(ApprovalStatus.PENDING);
		
		reqService.saveOrUpdate(req);
		
		//ModelAndView successMAV = new ModelAndView("redirect:/vetterDisplay/"+appRef);
		
		//return successMAV;
		return displayVetters(appRef, session);
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
	    	mav.addObject("message", "adding of  vetter Info could not be carried out.");
	    	
		   return mav;
		}
        
		
	}
}
