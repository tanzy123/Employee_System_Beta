package com.beta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Category;
import com.beta.services.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCategoryService {
	
	@Autowired
	CategoryService categoryService;
	
	@SuppressWarnings("unchecked") //create
	@Test
	@Rollback //(value=false)
	public void testCategoryCreation(){
		Category category = new Category();
		category.setCategoryName("IT");
		category.setCategoryId(100L);
		categoryService.saveOrUpdate(category);
		
		category = (Category) categoryService.find(100L);
		assertEquals("IT",category.getCategoryName());
	}
	
	@SuppressWarnings("unchecked") //read
	@Test
	@Rollback //(value=false)
	public void testCategorySearchAll(){
		List<Category> category = new ArrayList();
		 category =  categoryService.findAll();
		
		assertEquals(1,category.size()); //assume only one entry which is IT
		
	}
	
	@SuppressWarnings("unchecked") //update
	@Test
	@Rollback //(value=false)
	public void testCategoryUpdate(){
		Category category = new Category();
		category.setCategoryName("supply");
		category.setCategoryId(100L);
		categoryService.saveOrUpdate(category);
		
		category = (Category) categoryService.find(100L);
		assertEquals("supply",category.getCategoryName());
	}
	
	@Test
	@Rollback//(value=false) //delete
	public void testRoleDelete() throws Exception{
		Category category = new Category();
		categoryService.deleteIfExisting(100L);
		category =  (Category) categoryService.find(100L);
		assertNull(category);
		
	}


	
}
