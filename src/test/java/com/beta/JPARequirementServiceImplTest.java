package com.beta;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static com.beta.TestConstant.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.dao.ApplicationDao;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Category;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;
import com.beta.services.RequirementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPARequirementServiceImplTest {

	@Autowired
	RequirementService service;
	
	@Autowired
	ApplicationService appServ;
	
	@Autowired
	CompanyService comServ;
	
	@Autowired
	CategoryService catServ;
	
	@Autowired
	ApplicationDao dao;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	//expectedEx.expect(VendorMgmtException.class);
	//expectedEx.expectMessage("No application found with the same application reference");
	
	@Before
	public void initialize() {
		Category category= new Category();// = SAMPLE_CATEGORY1;
		category.setCategoryName(SAMPLE_CATEGORY1.getCategoryName());
		category.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		comServ.saveOrUpdate(SAMPLE_COMPANY);
		catServ.saveOrUpdate(category);
		appServ.saveOrUpdate(SAMPLE_APPLICATION1);
		
	}
	
	@Test
	@Rollback
	public void createRequirementSuccess() {
		Requirement req = new Requirement();
		req.setRequirement("LALALA");
		req.setUserName("TOM");
		req.setStatus(ApprovalStatus.PENDING);
		req.setSequence(133);
		req.setApplicationRef("ASD1111");
		int size1 = service.findAll().size();
		service.saveOrUpdate(req);
		int size2 = service.findAll().size();
		assertEquals((size1+1),size2);
		
	}
	
	@Test
	@Rollback
	public void updateRequirementSuccess() {
		Requirement req = new Requirement();
		req.setRequirement("LALALA");
		req.setUserName("TOM");
		req.setStatus(ApprovalStatus.PENDING);
		req.setSequence(133);
		req.setApplicationRef("ASD1111");
		
		service.saveOrUpdate(req);
		Long id = req.getRequirementId();
		
		Requirement req1 = new Requirement();
		req1.setRequirement("HEHEHE");
		req1.setUserName("TOM");
		req1.setStatus(ApprovalStatus.PENDING);
		req1.setSequence(133);
		req1.setApplicationRef("ASD1111");
		service.saveOrUpdate(req1);
		
		
		assertEquals("HEHEHE",service.find(id).getRequirement());
		
	}
	
	@Test
	@Rollback
	public void deleteRequirementSuccess() {
		Requirement req = new Requirement();
		req.setRequirement("LALALA");
		req.setUserName("TOM");
		req.setStatus(ApprovalStatus.PENDING);
		req.setSequence(133);
		req.setApplicationRef("ASD1111");
		service.saveOrUpdate(req);
		Long id = req.getRequirementId();
		int size1 = service.findAll().size();
		service.delete(id);
		int size2 = service.findAll().size();
		assertEquals((size1-1),size2);
		
	}
	
	@Test
	@Rollback
	public void invalidApplicationRef() {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("No application found with the same application reference");
		
		Requirement req = new Requirement();
		req.setRequirement("LALALA");
		req.setUserName("TOM");
		req.setStatus(ApprovalStatus.PENDING);
		req.setSequence(133);
		req.setApplicationRef("ASD1112");	
		service.saveOrUpdate(req);

		
	}
	
	@Test
	@Rollback
	public void missingUsername() {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("Requirements or user name not found");
		
		Requirement req = new Requirement();
		req.setRequirement("LALALA");
		req.setStatus(ApprovalStatus.PENDING);
		req.setSequence(133);
		req.setApplicationRef("ASD1111");
		service.saveOrUpdate(req);

	}
	
	@Test
	@Rollback
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
	@Rollback
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
