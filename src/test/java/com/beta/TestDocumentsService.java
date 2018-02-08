package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Documents;
import com.beta.services.DocumentsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestDocumentsService {
	
	@Autowired
	DocumentsService docService;
	
	@SuppressWarnings("unchecked") //create
	@Test
	@Rollback //(value=false)
	public void testDocCreation(){
		Documents doc = new Documents();
<<<<<<< HEAD
		doc.setDocumentId((long)10);
	//	Byte[] documents= {1,2,20};
		String example = "This is an example";
	//    byte[] bytes = example.getBytes();
	//	doc.setDocuments(bytes);
		System.out.println("AAA");
=======
		doc.setDocumentId(10L);
		Byte[] documents= {1,2,20};
//		doc.setDocuments(documents);
>>>>>>> c522fbff2913c959a8540d001f292fe57156e79e
		docService.saveOrUpdate(doc);
		System.out.println("BBB");
		
	//	doc = (Documents) docService.find((long)10);
	//	assertEquals(10L, doc.getDocumentId());
	}
	
	/*
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
	*/

	
}