package com.beta;

import static com.beta.TestConstant.SAMPLE_CATEGORY1;
import static com.beta.TestConstant.SAMPLE_CATEGORY3;
import static com.beta.TestConstant.SAMPLE_CATEGORY4;
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

import com.beta.entity.Category;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPACategoryServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	CategoryService service;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Test
	public void testAddCategory() {
		final int listSize = service.findAll().size();
		Category category = SAMPLE_CATEGORY3;
		category.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		service.saveOrUpdate(SAMPLE_CATEGORY3);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddWithoutCompanyRefInCategory() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Category name or company reference number not found");
		
		service.saveOrUpdate(SAMPLE_CATEGORY4);
	}
	
	@Test
	public void testAddWithInvalidCompanyRefInCategory() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Invalid company entered while validating category");
		
		SAMPLE_CATEGORY3.setCompanyReferenceNumber("asd");
		service.saveOrUpdate(SAMPLE_CATEGORY3);
	}
	
	@Test
	public void testDeleteCategory() {
		SAMPLE_CATEGORY3.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		service.saveOrUpdate(SAMPLE_CATEGORY3);
		int initialSize = service.findAll().size();
		Category category = service.findByNameAndCompanyRef(SAMPLE_CATEGORY3.getCategoryName(), SAMPLE_CATEGORY3.getCompanyReferenceNumber());
		service.delete(category.getCategoryId());
		assertThat(service.findAll().size(), is(initialSize - 1));
	}

	@Test
	public void testAddAndUpdateCategory() {
		int initialSize = service.findAll().size();
		Category category = SAMPLE_CATEGORY3;
		category.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		service.saveOrUpdate(category);
		category = service.findByNameAndCompanyRef(category.getCategoryName(), category.getCompanyReferenceNumber());
		assertThat(category.getCategoryName(), is(SAMPLE_CATEGORY3.getCategoryName()));
		category.setCategoryName("Security");
		SAMPLE_CATEGORY3.setCategoryName("Security");
		service.saveOrUpdate(category);
		category = service.findByNameAndCompanyRef(SAMPLE_CATEGORY3.getCategoryName(), SAMPLE_CATEGORY3.getCompanyReferenceNumber());
		assertThat(category.getCategoryName(), is("Security"));
		assertThat(service.findAll().size(), is(initialSize + 1));
	}
	
	// With or without changes also have failure
	@Test
	public void testSaveAndUpdateExistingCategory() {
		SAMPLE_CATEGORY1.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		service.saveOrUpdate(SAMPLE_CATEGORY1);
	}
	
	
}
