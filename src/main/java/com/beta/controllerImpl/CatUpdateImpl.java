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

import com.beta.controller.CategoryUpdateController;
import com.beta.entity.Category;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CategoryService;
import com.beta.orm.service.CompanyAdminstratorAccountService;

@Controller
public class CatUpdateImpl implements CategoryUpdateController {

	@Autowired
	CompanyAdminstratorAccountService accountService;

	@Autowired
	CategoryService catService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.beta.controllerImpl.CategoryUpdateController#Registration(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.http.HttpSession)
	 */
	@Override
	@RequestMapping(value = "/updateCat", method = RequestMethod.GET)
	public ModelAndView Registration(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());
			List<Category> catlist = catService.findByCompanyRef(account.getCompanyReferenceNumber());

			ModelAndView mav = new ModelAndView("updateCat");
			mav.addObject("catlist", catlist);

			return mav;
	}

	@Override
	@RequestMapping(value = "/storeNewCatInfo", method = RequestMethod.POST)
	public ModelAndView StoreNewCompanyInfo(HttpSession session, @RequestParam(value = "catName") String catName)
	{
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());
			List<String> theList = Arrays.asList(catName.split(","));
			for (String s : theList) {
				Category cat = new Category();
				cat.setCompanyReferenceNumber(account.getCompanyReferenceNumber());
				cat.setCategoryName(s);
				catService.saveOrUpdate(cat);
			}

			ModelAndView successMAV = new ModelAndView("redirect:updateCat");

			return successMAV;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.beta.controllerImpl.CategoryUpdateController#DeleteCatInfo(javax.servlet.
	 * http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/deleteCatInfo", method = RequestMethod.GET)
	public ModelAndView DeleteCatInfo(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("username")==null)
			return new ModelAndView("redirect:/login");
		
			String depid = request.getParameter("depid");
			Long did = Long.parseLong(depid);
			CompanyAdministratorAccount account = accountService
					.findByUserName(session.getAttribute("username").toString());
			catService.removeCat(did);
			ModelAndView successMAV = new ModelAndView("redirect:updateCat");
			return successMAV;
	}

}
