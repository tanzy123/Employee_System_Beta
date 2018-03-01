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
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.DepartmentService;

@Controller
public class DepartmentUpdateImpl {

	@Autowired
	DepartmentService deptService;
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@RequestMapping(value = "/updateDepartment", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<Department> departmentlist = deptService.findByCompanyRef(account.getCompanyReferenceNumber());
//		List<String> deptNamelist = new ArrayList();
//		for (Department d: departmentlist) {
//			deptNamelist.add(d.getDepartmentName());
//		}
		int size = departmentlist.size();
		ModelAndView mav = new ModelAndView("updateDepartment");
		mav.addObject("departmentlist",departmentlist);
		mav.addObject("comRef", account.getCompanyReferenceNumber());
		//mav.addObject("deptNamelist", deptNamelist);
		mav.addObject("size", size);
		session.setAttribute("deps", departmentlist);
		return mav;
	}
	
	@RequestMapping(value = "/storeNewDeptInfo", method= RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(HttpSession session,
			@RequestParam(value = "deptName") String departmentName)
		
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<String> theList = Arrays.asList(departmentName.split(","));
		for (String s: theList) {
		Department department = new Department();
		department.setCompanyReferenceNumber(account.getCompanyReferenceNumber());
		department.setDepartmentName(s);
		deptService.saveOrUpdate(department);
		}
		
		ModelAndView successMAV = new ModelAndView("redirect:updateDepartment");
		
		return successMAV;
		
	}
	
	@RequestMapping(value = "/deleteDeptInfo", method= RequestMethod.GET)
	public ModelAndView DeleteCompanyInfo(HttpSession session, HttpServletRequest request)
			
		
	{
		
		String depid = request.getParameter("depid");
		Long did = Long.parseLong(depid);
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
	
		
		deptService.removeDepartment(did);
		
		
		ModelAndView successMAV = new ModelAndView("redirect:updateDepartment");
		
		return successMAV;
		
	}
	
}
