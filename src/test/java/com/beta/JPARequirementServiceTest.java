package com.beta;

import static com.beta.TestConstant.SAMPLE_APPLICATION1;
import static com.beta.TestConstant.SAMPLE_CATEGORY1;
import static com.beta.TestConstant.SAMPLE_COMPANY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.dao.ApplicationDao;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Category;
import com.beta.entity.Requirement;
import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CategoryService;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.RequirementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPARequirementServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	ApplicationService appServ;
	
	@Autowired
	CategoryService catServ;
	
	@Autowired
	CompanyService comServ;
	
	@Autowired
	RequirementService service;
	
	@Before
	public void initialize() {
		
		Category category = new Category();
		category.setCategoryName(SAMPLE_CATEGORY1.getCategoryName());
		category.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
												
		comServ.saveOrUpdate(SAMPLE_COMPANY);
	//	catServ.saveOrUpdate(category);
		appServ.saveOrUpdate(SAMPLE_APPLICATION1);
		
	}
	
	@Test
	public void testRequirementCreateSuccess() {
	//create
		
	int startsize = service.findAll().size();	
	
	Calendar cal = Calendar.getInstance();
	
	Requirement req = new Requirement();
	req.setApplicationRef("ASD1111");
	req.setRequirement("Must be a listed company");
	req.setSequence(10);
	req.setStatus(ApprovalStatus.WAITING);
	req.setStatusUpdateDate(cal.getTime());
	req.setUserName("Mr Lee");
	service.saveOrUpdate(req);
	int finalsize = service.findAll().size();
	
	assertEquals((startsize+1), finalsize);
	
	}
	
	@Test
	public void testRequirementUpdate() {
		
	
	Calendar cal = Calendar.getInstance();
	
	Requirement req = new Requirement();
	req.setApplicationRef("ASD1111");
	req.setRequirement("Must be a listed company");
	req.setSequence(10);
	req.setStatus(ApprovalStatus.WAITING);
	req.setStatusUpdateDate(cal.getTime());
	req.setUserName("Mr Lee");
	service.saveOrUpdate(req);
	long id = req.getRequirementId();
	
	Requirement req1 = new Requirement();
	req1.setApplicationRef("ASD1111");
	req1.setRequirement("Must be a SMALL company");
	req1.setSequence(10);
	req1.setStatus(ApprovalStatus.WAITING);
	req1.setStatusUpdateDate(cal.getTime());
	req1.setUserName("Mr Lee");
	service.saveOrUpdate(req1);
	
	req = service.find(id);
	
	System.out.println(req);
	
	
	assertEquals(req.getRequirement(), "Must be a SMALL company");
	
	}
	
	@Test
	public void testRequirementDelete() {	
		
	Calendar cal = Calendar.getInstance();
	
	Requirement req = new Requirement();
	req.setApplicationRef("ASD1111");
	req.setRequirement("Must be a listed company");
	req.setSequence(10);
	req.setStatus(ApprovalStatus.WAITING);
	req.setStatusUpdateDate(cal.getTime());
	req.setUserName("Mr Lee");
	service.saveOrUpdate(req);
	Long id = req.getRequirementId();
	int startsize = service.findAll().size();
	
	service.delete(id);
	
	int finalsize = service.findAll().size();
	
	assertEquals((startsize-1), finalsize);
	
	}
	
	@Test
	public void testRequirementCreateFail() {
	//create fail because user name is not inserted
		
		Calendar cal = Calendar.getInstance();
	expectedEx.expect(VendorMgmtException.class);
	expectedEx.expectMessage("Requirements or user name not found");
	
	int startsize = service.findAll().size();	
	
	Requirement req = new Requirement();
	req.setApplicationRef("ASD1111");
	req.setRequirement("Must be a listed company");
	req.setSequence(10);
	req.setStatus(ApprovalStatus.WAITING);
	req.setStatusUpdateDate(cal.getTime());
	service.saveOrUpdate(req);
	int finalsize = service.findAll().size();

	}
	
	@Test
	public void testNoApplicationRefFail() {
	// will fail because of multiple requirement to one appRef
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("No application found with the same application reference");
	
		Calendar cal = Calendar.getInstance();
		
		Requirement req = new Requirement();
		req.setApplicationRef("ASD1110");
		req.setRequirement("Must be a listed company");
		req.setSequence(10);
		req.setStatus(ApprovalStatus.WAITING);
		req.setStatusUpdateDate(cal.getTime());
		req.setUserName("Mr Lee");
		service.saveOrUpdate(req);
	
	}
	
	@Test
	public void testGetRequirementByUsernameAndStatusSuccess(){
		
		Requirement r = new Requirement();
		r.setStatus(ApprovalStatus.WAITING);
		r.setUserName("asd");
		List<Requirement> reqList = new ArrayList<>();
		reqList.add(r);
		Application a = new Application();
		a.setVetterRequirement(reqList);
		a.setApplicationRef("qqqaaa");
		appServ.saveOrUpdate(a);
		List<Requirement> list = service.findByUsernameAndStatus("asd", ApprovalStatus.WAITING);
		assertThat(list.size(), is(1));
	}
	
	@Test
	public void testGetRequirementByUsernameAndStatusFail(){
		
		Requirement r = new Requirement();
		r.setStatus(ApprovalStatus.WAITING);
		r.setUserName("asd");
		List<Requirement> reqList = new ArrayList<>();
		reqList.add(r);
		Application a = new Application();
		a.setVetterRequirement(reqList);
		a.setApplicationRef("qqqaaa");
		appServ.saveOrUpdate(a);
		List<Requirement> list = service.findByUsernameAndStatus("a", ApprovalStatus.WAITING);
		assertThat(list.size(), is(0));
	}
}
