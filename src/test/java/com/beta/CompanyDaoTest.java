package com.beta;

import static com.beta.TestConstant.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.dao.CompanyDao;
import com.beta.entity.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class CompanyDaoTest {

	@Autowired
	CompanyDao dao;
	
	@Test
	public void testAddCompany() {
		final int listSize = dao.findAll().size();
		dao.persist(SAMPLE_COMPANY);
		assertThat(dao.findAll().size(), is(listSize+1));
		
	}
	
	@Test
	public void testRemoveCompany() {
		dao.persist(SAMPLE_COMPANY);
		final int listSize = dao.findAll().size();
		Company company = dao.findById(SAMPLE_COMPANY.getCompanyPrimaryId());
		dao.remove(company);
		assertThat(dao.findAll().size(), is(listSize-1));
	}

}
