package com.beta;

import java.util.ArrayList;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPACompanyApplicationTest {
	
	@Autowired
	CompanyValidation comValid;
	
	@Autowired
	CompanyService comService;
	
	@Autowired
	CategoryService catServ;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testCompanyValidator() throws Exception {
		
	//	expectedEx.expect(VendorMgmtException.class);
	//  expectedEx.expectMessage("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		
	    List<Role> role = new ArrayList<Role>();
	    Role r = new Role();
	    r.setRole("Assoc");
	    r.setCompanyReferenceNumber("AA-12");
	    role.add(r);
	    
	    List<Department> department = new ArrayList<Department>();
	    Department dept = new Department();
	    dept.setCompanyReferenceNumber("AA-12");
	    dept.setDepartmentName("ADMIN");
	    department.add(dept);
	    
		List<Category> category = new ArrayList<Category>();
		Category cat = new Category();
		cat.setCategoryName("ADMIN");
		cat.setCompanyReferenceNumber("AA-12");
		category.add(cat);
		
		Company com = new Company();
		com.setCompanyReferenceNumber("AA-12");
		com.setCompanyName("ABB");
		com.setCompanyAddress("S'pore");
		com.setCompanyEmail("abc@gmail.com");
		com.setCompanyWebsite("www.abc.com");
		com.setContactNumber("9999 9998");
		com.setTurnover(2000L);
		com.setCategory(category);
		com.setRoles(role);
		com.setDepartment(department);
		
		comService.saveOrUpdate(com);
		comValid.validateCommpanyApplication(com);
	}
	
	@Test
	public void testMissingFields() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		
	    List<Role> role = new ArrayList<Role>();
	    Role r = new Role();
	    r.setRole("Assoc");
	    r.setCompanyReferenceNumber("AA-12");
	    role.add(r);
	    
	    List<Department> department = new ArrayList<Department>();
	    Department dept = new Department();
	    dept.setCompanyReferenceNumber("AA-12");
	    dept.setDepartmentName("ADMIN");
	    department.add(dept);
	    
		List<Category> category = new ArrayList<Category>();
		Category cat = new Category();
		cat.setCategoryName("ADMIN");
		cat.setCompanyReferenceNumber("AA-12");
		category.add(cat);
		
		Company com = new Company();
		com.setCompanyReferenceNumber("AA-12");
		com.setCompanyName("ABB");
		com.setCompanyAddress("S'pore");
		com.setCompanyEmail("abc@gmail.com");
		com.setCompanyWebsite("www.abc.com");
		com.setContactNumber("9999 9998");
		com.setTurnover(2000L);
		com.setCategory(category);
	//	com.setRoles(role);                  These 2 mandatory fields were taken out on purpose for the sake of this test case
	//	com.setDepartment(department);
		
		comService.saveOrUpdate(com);
		comValid.validateCommpanyApplication(com);
	}
	
	@Test
	public void testInvalidEmail() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("INVALID EMAIL ADDRESS GIVEN");
		
	    List<Role> role = new ArrayList();
	    Role r = new Role();
	    r.setRole("Assoc");
	    r.setCompanyReferenceNumber("AA-12");
	    role.add(r);
	    
	    List<Department> department = new ArrayList();
	    Department dept = new Department();
	    dept.setCompanyReferenceNumber("AA-12");
	    dept.setDepartmentName("ADMIN");
	    department.add(dept);
	    
		List<Category> category = new ArrayList<Category>();
		Category cat = new Category();
		cat.setCategoryName("ADMIN");
		cat.setCompanyReferenceNumber("AA-12");
		category.add(cat);
		
		Company com = new Company();
		com.setCompanyReferenceNumber("AA-12");
		com.setCompanyName("ABB");
		com.setCompanyAddress("S'pore");
		com.setCompanyEmail("abcl"); // Set to be invalid for this test case
		com.setCompanyWebsite("www.abc.com");
		com.setContactNumber("9999 9998");
		com.setTurnover(2000L);
		com.setCategory(category);
		com.setRoles(role);
		com.setDepartment(department);
		
		comService.saveOrUpdate(com);
		comValid.validateCommpanyApplication(com);
	}
	
	@Test
	public void testNegativeTurnOver() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("TURNOVER CANNOT BE NEGATIVE");
		
	    List<Role> role = new ArrayList();
	    Role r = new Role();
	    r.setRole("Assoc");
	    r.setCompanyReferenceNumber("AA-12");
	    role.add(r);
	    
	    List<Department> department = new ArrayList();
	    Department dept = new Department();
	    dept.setCompanyReferenceNumber("AA-12");
	    dept.setDepartmentName("ADMIN");
	    department.add(dept);
	    
		List<Category> category = new ArrayList<Category>();
		Category cat = new Category();
		cat.setCategoryName("ADMIN");
		cat.setCompanyReferenceNumber("AA-12");
		category.add(cat);
		
		Company com = new Company();
		com.setCompanyReferenceNumber("AA-12");
		com.setCompanyName("ABB");
		com.setCompanyAddress("S'pore");
		com.setCompanyEmail("abc@gmail.com");
		com.setCompanyWebsite("www.abc.com");
		com.setContactNumber("9999 9998");
		com.setTurnover(-2000L);  // set to be negative on purpose
		com.setCategory(category);
		com.setRoles(role);
		com.setDepartment(department);
		
		comService.saveOrUpdate(com);
		comValid.validateCommpanyApplication(com);
	}
	
	
}
