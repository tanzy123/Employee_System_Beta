package com.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Department;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyService;
import com.beta.services.DepartmentService;
import static com.beta.TestConstant.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPADepartmentServiceTest {

	@Autowired
	DepartmentService deptServ;
	
	@Autowired
	CompanyService comServ;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	//	expectedEx.expect(VendorMgmtException.class);
	//  expectedEx.expectMessage("MANDATORY FIELDS ARE NOT ALL FILLED UP");
	
	@Before
	public void initialize() {
		comServ.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Test
	public void departmentCreationSuccess() {
		
		Department dept = new Department();
		dept.setCompanyReferenceNumber("CTS123");
		dept.setDepartmentName("ADMIN");
		int size1 = deptServ.findAll().size();
		deptServ.saveOrUpdate(dept);
		int size2 = deptServ.findAll().size();
		
		assertEquals((size1+1),size2);
	}
	
	@Test
	public void departmentUpdateSuccess() {
		
		Department dept = new Department();
		dept.setCompanyReferenceNumber("CTS123");
		dept.setDepartmentName("ADMIN");
		
		deptServ.saveOrUpdate(dept);
		Long id =dept.getDepartmentId();
		
		dept.setDepartmentName("FOOD");
		deptServ.saveOrUpdate(dept);
		dept=deptServ.find(id);
		
		assertEquals("FOOD", dept.getDepartmentName());
		
	
	}
	
	@Test
	public void departmentDelete() {
		
		Department dept = new Department();
		dept.setCompanyReferenceNumber("CTS123");
		dept.setDepartmentName("ADMIN");
		deptServ.saveOrUpdate(dept);
		Long id = dept.getDepartmentId();
		int size1 = deptServ.findAll().size();
		deptServ.delete(id);
		int size2 = deptServ.findAll().size();
		
		assertEquals((size1),size2+1);
	}
	
	@Test
	public void InvalidCompanyFailure() {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("Invalid company entered while validating department");
		
		Department dept = new Department();
		dept.setCompanyReferenceNumber("CTT100");
		dept.setDepartmentName("ADMIN");
		
		deptServ.saveOrUpdate(dept);
		
	}
	
	@Test
	public void departmentNamenotFoundFAILURE() {
		
		expectedEx.expect(VendorMgmtException.class);
		expectedEx.expectMessage("department name or company reference number not found");
		
		Department dept = new Department();
		dept.setCompanyReferenceNumber("CTS123");
	
		deptServ.saveOrUpdate(dept);
		
		
		
	}
	
}