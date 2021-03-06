package com.beta.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beta.entity.ApprovalStatus;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.orm.service.CompanyAdminstratorAccountService;
import com.beta.orm.service.EmployeeAccountService;
import com.beta.orm.service.RequirementService;

@Controller
public class VetterMgmtControllerImpl {

	@Autowired
	CompanyAdminstratorAccountService accountService;

	@Autowired
	RequirementService reqService;

	@Autowired
	EmployeeAccountService empAcctServ;

	@RequestMapping(value = "/vetterDisplay/{appRef}", method = RequestMethod.GET)
	public ModelAndView displayVetters(@PathVariable String appRef, HttpSession session) {

		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		CompanyAdministratorAccount account = (CompanyAdministratorAccount) session.getAttribute("account");

		List<Requirement> reqList = reqService.findByApplicationRef(appRef);
		List<EmployeeAccount> vetterList = new ArrayList();
		for (Requirement q : reqList) {
			EmployeeAccount a = empAcctServ.findByUserName(q.getUserName());
			vetterList.add(a);

		}

		ModelAndView mav = new ModelAndView("assignVetterByZQ");
		mav.addObject("vetterList", vetterList);
		mav.addObject("appRef", appRef);
		mav.addObject("comRef", account.getCompanyReferenceNumber());

		return mav;
	}

	@RequestMapping(value = "vetterDisplay/addVetInfo", method = RequestMethod.GET)
	public ModelAndView addVetterInfo(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		String id = request.getParameter("id");
		String appRef = request.getParameter("appRef");
		String Sequence = request.getParameter("Sequence");
		int seq = Integer.parseInt(Sequence);
		CompanyAdministratorAccount account = accountService
				.findByUserName(session.getAttribute("username").toString());

		List<EmployeeAccount> list = empAcctServ.findByEmpId(id);

		EmployeeAccount ea = new EmployeeAccount();

		for (EmployeeAccount e : list) {
			ea = e;
		}

		Requirement req = new Requirement();
		req.setUserName(ea.getUserName());
		req.setApplicationRef(appRef);

		if (seq == 1) {
			req.setStatus(ApprovalStatus.PENDING);
		} else {
			req.setStatus(ApprovalStatus.WAITING);
		}
		req.setSequence(seq);

		reqService.saveOrUpdate(req);

		return displayVetters(appRef, session);

	}

	@RequestMapping(value = "vetterDisplay/deleteVetInfo", method = RequestMethod.GET)
	public ModelAndView DeleteCompanyInfo(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("username") == null)
			return new ModelAndView("redirect:/login");
		String userName = request.getParameter("userName");
		String appRef = request.getParameter("appRef");

		Requirement req = reqService.findByApplicationRefAndUser(appRef, userName);

		Long id = req.getRequirementId();

		reqService.removeVet(id);

		return displayVetters(appRef, session);
	}
}