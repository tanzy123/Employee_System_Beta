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

import com.beta.entity.Category;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Role;
import com.beta.services.CategoryService;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.RoleService;

@Controller
public class CatUpdateImpl {
	
	@Autowired
	CompanyAdminstratorAccountService accountService;
	
	@Autowired
	CategoryService catService;
	
	@RequestMapping(value = "/updateCat", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<Category> catlist = catService.findByCompanyRef(account.getCompanyReferenceNumber());

		ModelAndView mav = new ModelAndView("updateCat");
		mav.addObject("catlist",catlist);
		
		return mav;
	}
	
	@RequestMapping(value = "/storeNewCatInfo", method= RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(HttpSession session,
			@RequestParam(value = "catName") String catName)
		
	{
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
		List<String> theList = Arrays.asList(catName.split(","));
		for (String s: theList) {
		Category cat = new Category();
		cat.setCompanyReferenceNumber(account.getCompanyReferenceNumber());
		cat.setCategoryName(s);
		catService.saveOrUpdate(cat);
		}
		
		ModelAndView successMAV = new ModelAndView("redirect:updateCat");
		
		return successMAV;
		
	}
	
	@RequestMapping(value = "/deleteCatInfo", method= RequestMethod.GET)
	public ModelAndView DeleteCatInfo(HttpSession session, HttpServletRequest request)
			
		
	{
		
		String depid = request.getParameter("depid");
		Long did = Long.parseLong(depid);
		CompanyAdministratorAccount account = accountService.findByUserName(session.getAttribute("username").toString());
	
		
		
		catService.removeCat(did);
		
		ModelAndView successMAV = new ModelAndView("redirect:updateCat");
		
		return successMAV;
		
	}

}
