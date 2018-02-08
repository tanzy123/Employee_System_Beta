package com.beta;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.dao.ApplicationDao;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.services.RequirementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestRequirementServiceImpl {

	@Autowired
	RequirementService service;
	
	@Autowired
	ApplicationDao dao;
	
	@Test
	public void testGetRequirementByUsernameAndStatusSuccess(){
		Requirement r = new Requirement();
		r.setStatus(ApprovalStatus.PENDING);
		r.setUserName("asd");
		List<Requirement> reqList = new ArrayList<>();
		reqList.add(r);
		Application a = new Application();
		a.setVettorRequirement(reqList);
		a.setApplicationRef("qqqaaa");
		dao.persist(a);
		List<Requirement> list = service.getRequirementByUsernameAndStatus("asd", ApprovalStatus.PENDING);
		assertThat(list.size(), is(1));
	}
	
	@Test
	public void testGetRequirementByUsernameAndStatusFail(){
		Requirement r = new Requirement();
		r.setStatus(ApprovalStatus.PENDING);
		r.setUserName("asd");
		List<Requirement> reqList = new ArrayList<>();
		reqList.add(r);
		Application a = new Application();
		a.setVettorRequirement(reqList);
		a.setApplicationRef("qqqaaa");
		dao.persist(a);
		List<Requirement> list = service.getRequirementByUsernameAndStatus("a", ApprovalStatus.PENDING);
		assertThat(list.size(), is(0));
	}
}
