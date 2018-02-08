package com.beta;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Category;
import com.beta.services.CategoryService;
import com.beta.services.impl.CategoryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCategoryService {

	@Autowired
	CategoryService cat = new CategoryServiceImpl();
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCategory () {
		List <Category> category = cat.findAll();
		for (Category c : category) {
			System.out.println(c);
		}
	}
	
	
	
}
