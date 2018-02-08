package com.beta;

import static com.beta.TestConstant.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Application;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPAApplicationServiceTest {

	@Autowired
	ApplicationService service;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.save(SAMPLE_COMPANY);
	}
	
	@Test
	public void testAddApplication() {
		final int listSize = service.findAll().size();
		service.save(SAMPLE_APPLICATION3);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddAndRemoveApplication() {
		service.save(SAMPLE_APPLICATION3);
		final int listSize = service.findAll().size();
		Application application = (Application)service.find(SAMPLE_APPLICATION3.getApplicationId());
		service.delete(application.getApplicationId());
		assertThat(service.findAll().size(), is(listSize-1));
	}

	@Test
	public void testAddAndUpdateApplication() {
		service.saveOrUpdate(SAMPLE_APPLICATION3);
		Application application = new Application();
		application.setApplicationRef(SAMPLE_APPLICATION3.getApplicationRef());
		application.setRemarks("hello");
		service.saveOrUpdate(application);
		Application updatedApplication = service.findByApplicationRefNo(SAMPLE_APPLICATION3.getApplicationRef());
		assertThat(updatedApplication.getApplicationDate(), is(SAMPLE_APPLICATION3.getApplicationDate()));
		assertThat(updatedApplication.getRemarks(), is("hello"));
	}
}
