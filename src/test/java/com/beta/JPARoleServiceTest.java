package com.beta;

import static com.beta.TestConstant.SAMPLE_COMPANY;
import static org.junit.Assert.*;


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

import com.beta.entity.Role;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyService;
import com.beta.services.RoleService;
import com.beta.TestConstant.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPARoleServiceTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		
		companyService.saveOrUpdate(SAMPLE_COMPANY);
		
		
	}
	
    //create
	@Test
	@Rollback(value=false)
	public void testRoleCreation(){
		Role role = new Role();
		role.setRole("Associate");
		role.setCompanyReferenceNumber("CTS123");
		int startsize = roleService.findAll().size();
		roleService.saveOrUpdate(role);
		int endsize = roleService.findAll().size();
		
		assertEquals((startsize+1),endsize);
	}
	
	@Test
	public void testMissingRole(){
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Role name or Reference Number not found");
		
		Role role = new Role();
		role.setCompanyReferenceNumber("CTS123");
		roleService.saveOrUpdate(role);
	}
	
	@Test
	public void testInvalidCompanyRef(){
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("No company found with the same given reference");
		
	    Role role = new Role();
		role.setRole("Associate");
		role.setCompanyReferenceNumber("CTS124");
		roleService.saveOrUpdate(role);
		
	}
	
	 //update
	@Test
	public void testRoleUpdate()
	{
		Role role1 = new Role();
		role1.setRoleId(10L);
		role1.setRole("Associate1");
		role1.setCompanyReferenceNumber("CTS123");
		
		roleService.saveOrUpdate(role1);
		
		role1 = roleService.findByCompanyReferenceNumberAndRole("CTS123","Associate1");
		Long id = role1.getRoleId();
		role1.setRole("Staff");
		
		roleService.saveOrUpdate(role1);
		role1 = roleService.find(id);
		
		assertEquals("Staff",role1.getRole());
		
	} 
	
	@Test
	public void testRoleDelete() throws Exception{
		Role role = new Role();
		role.setRole("Associate");
		role.setCompanyReferenceNumber("CTS123");
		int startsize = roleService.findAll().size();
		roleService.saveOrUpdate(role);
		int endsize = roleService.findAll().size();
		assertEquals((startsize+1),endsize);
		role = roleService.findByCompanyReferenceNumberAndRole("CTS123","Associate");
		Long id = role.getRoleId();
		
		roleService.delete(id);
		role= roleService.find(id);
		assertNull(role);
		
	}


	
}
