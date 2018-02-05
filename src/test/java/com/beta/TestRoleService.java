package com.beta;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Role;
import com.beta.services.RoleService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestRoleService {
	
	@Autowired
	RoleService roleService;
	
	@SuppressWarnings("unchecked") //create
	@Test
	@Rollback //(value=false)
	public void testRoleCreation(){
		Role role = new Role();
		role.setRole("Associate");
		role.setRoleId((long) 100);
		
		roleService.saveOrUpdate(role);
		
		role = (Role) roleService.find((long)100);
		assertEquals("Associate",role.getRole());
	}
	
	@SuppressWarnings("unchecked") //read
	@Test
	@Rollback //(value=false)
	public void testRoleSearchAll(){
		List<Role> role = new ArrayList();
		 role =  roleService.findAll();
		
		assertEquals(5,role.size()); // This one is for ZQ's SQL because already have 5 data set
		//assertEquals(1,role.size()); 
	}
	
	@SuppressWarnings("unchecked") //update
	@Test
	@Rollback //(value=false)
	public void testRoleUpdate(){
		Role role = new Role();
		role.setRole("Associate1");
		role.setRoleId((long) 100);
		
		roleService.saveOrUpdate(role);
		
		role = (Role) roleService.find((long)100);
		assertEquals("Associate1",role.getRole());
	}
	
	@Test
	@Rollback//(value=false) //delete
	public void testRoleDelete() throws Exception{
		Role role = new Role();
		roleService.deleteIfExisting((long)100);
		role =  (Role) roleService.find((long)100);
		assertNull(role);
		
	}


	
}
