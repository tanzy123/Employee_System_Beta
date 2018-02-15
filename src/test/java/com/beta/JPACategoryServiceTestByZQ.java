package com.beta;


import static com.beta.TestConstant.SAMPLE_COMPANY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

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
import com.beta.services.impl.CategoryServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPACategoryServiceTestByZQ {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
	CategoryService cat;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Autowired
	CategoryService categoryService;
	
	 //create
	@Test
	@Rollback 
	public void testCategoryCreation(){
		Category category = new Category();
		category.setCategoryName("IT");
		category.setCompanyReferenceNumber("CTS123");
		categoryService.saveOrUpdate(category);
		Long id = category.getCategoryId();
		
		category =  categoryService.find(id);
		assertEquals("IT",category.getCategoryName());
	}
	
	//read
	@Test
	@Rollback 
	public void testCategoryDelete(){
		Category category = new Category();
		category.setCategoryName("IT");
		category.setCompanyReferenceNumber("CTS123");
		categoryService.saveOrUpdate(category);
		Long id = category.getCategoryId();
		int size1 = categoryService.findAll().size();
		
		categoryService.delete(id);
		int size2 = categoryService.findAll().size();
		
		assertEquals(size1,(size2+1)); 
		
	}
	
	@Test
	@Rollback 
	public void testCategoryUpdate(){
		Category category = new Category();
		category.setCategoryName("supply");
		category.setCompanyReferenceNumber("CTS123");
		categoryService.saveOrUpdate(category);
		Long id = category.getCategoryId();
		
		Category category1 = new Category();
		category1.setCategoryName("FOOD");
		category1.setCompanyReferenceNumber("CTS123");
		category1.setCategoryId(id);
		categoryService.saveOrUpdate(category1);
		
		Category category2 = categoryService.find(id);
		
		assertEquals("FOOD",category2.getCategoryName());
	}
	
	@Test
	@Rollback 
	public void testCategoryNOCompanyRef(){
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Category name or company reference number not found");
		
		Category category = new Category();
		category.setCategoryName("supply");
		categoryService.saveOrUpdate(category);
		
	}
	
	@Test
	@Rollback 
	public void testCategoryInvalidCompanyRef(){
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Invalid company entered while validating category");
		
		Category category = new Category();
		category.setCategoryName("supply");
		category.setCompanyReferenceNumber("CTS12"); // Invalid companyRef number
		categoryService.saveOrUpdate(category);
	}
	
	
}
