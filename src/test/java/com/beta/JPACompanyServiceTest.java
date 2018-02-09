package com.beta;

import static com.beta.TestConstant.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Company;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPACompanyServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	CompanyService service;
	
	@Test
	public void testAddCompany() {
		final int listSize = service.findAll().size();
		service.save(SAMPLE_COMPANY);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddAndRemoveCompany() {
		service.save(SAMPLE_COMPANY);
		final int listSize = service.findAll().size();
		Company company = (Company)service.find(SAMPLE_COMPANY.getCompanyPrimaryId());
		service.delete(company.getCompanyPrimaryId());
		assertThat(service.findAll().size(), is(listSize-1));
	}

	@Test
	public void testAddAndUpdateCompany() {
		service.saveOrUpdate(SAMPLE_COMPANY);
		Company company = new Company();
		company.setCompanyName(SAMPLE_COMPANY.getCompanyName());
		company.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		company.setContactNumber("123123");
		service.saveOrUpdate(company);
		Company updatedCompany = service.findbyCompanyNameAndRefNo(SAMPLE_COMPANY.getCompanyReferenceNumber(), SAMPLE_COMPANY.getCompanyName());
		System.out.println(updatedCompany);
		assertThat(updatedCompany.getCompanyEmail(), is(SAMPLE_COMPANY.getCompanyEmail()));
		assertThat(updatedCompany.getContactNumber(), is("123123"));
	}
}
