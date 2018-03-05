package com.beta;

import static com.beta.TestConstant.SAMPLE_APPLICATION3;
import static com.beta.TestConstant.SAMPLE_COMPANY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Application;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPAApplicationServiceTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	ApplicationService service;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Test
	public void testAddApplication() {
	//	final int listSize = service.findAll().size();
		int listSize = service.findAll().size();
		service.saveOrUpdate(SAMPLE_APPLICATION3);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddAndRemoveApplication() {
		service.saveOrUpdate(SAMPLE_APPLICATION3);
		final int listSize = service.findAll().size();
		Application application = service.findByApplicationRefNo(SAMPLE_APPLICATION3.getApplicationRef());
		service.delete(application.getApplicationId());
		assertThat(service.findAll().size(), is(listSize-1));
	}

	@Test
	public void testAddAndUpdateApplication() {
		int initialSize = service.findAll().size();
		service.saveOrUpdate(SAMPLE_APPLICATION3);
		Application application = new Application();
		application.setApplicationRef(SAMPLE_APPLICATION3.getApplicationRef());
		application.setRemarks("hello");
		service.saveOrUpdate(application);
		Application updatedApplication = service.findByApplicationRefNo(SAMPLE_APPLICATION3.getApplicationRef());
		assertThat(updatedApplication.getApplicationDate(), is(SAMPLE_APPLICATION3.getApplicationDate()));
		assertThat(updatedApplication.getRemarks(), is("hello"));
		assertThat(service.findAll().size(), is(initialSize + 1));
	}
}
