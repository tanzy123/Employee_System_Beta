package com.beta;

import static com.beta.TestConstant.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.EmployeeAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.EmployeeAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPAEmployeeAccountTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	EmployeeAccountService service;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Test
	public void testAddCompanyAdministratorAccount() {
		final int listSize = service.findAll().size();
		service.createNewAccount(SAMPLE_EMPLOYEE);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddAndRemoveCompanyAdministratorAccount() {
		service.createNewAccount(SAMPLE_EMPLOYEE);
		final int listSize = service.findAll().size();
		EmployeeAccount account = service.findByUserName(SAMPLE_EMPLOYEE.getUserName());
		service.delete(account.getAccountId());
		assertThat(service.findAll().size(), is(listSize-1));
	}
	
	@Test
	public void testValidCompanyAdministratorAccount() {
		String password = SAMPLE_EMPLOYEE.getPassword();
		service.createNewAccount(SAMPLE_EMPLOYEE);
		EmployeeAccount accountToCheck = new 
				EmployeeAccount(SAMPLE_EMPLOYEE.getUserName(), password, SAMPLE_EMPLOYEE.getCompanyReferenceNumber());
		service.validateAccount(accountToCheck);
	}
	
	@Test
	public void testInvalidCompanyAdministratorAccount() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Invalid Username or Password");
		
		service.createNewAccount(SAMPLE_EMPLOYEE);
		EmployeeAccount accountToCheck = new 
				EmployeeAccount(SAMPLE_EMPLOYEE.getUserName(), "sdfgsdf", SAMPLE_EMPLOYEE.getCompanyReferenceNumber());
		service.validateAccount(accountToCheck);
	}
	
	@Test
	public void testAddAndUpdateCompanyAdministratorAccount() {
		int initialSize = service.findAll().size();
		EmployeeAccount account = SAMPLE_EMPLOYEE;
		String userName = SAMPLE_EMPLOYEE.getUserName();
		String password = SAMPLE_EMPLOYEE.getPassword();
		String companyReferenceNumber = SAMPLE_EMPLOYEE.getCompanyReferenceNumber();
		EmployeeAccount updatedAccount = new
				EmployeeAccount(userName, password, companyReferenceNumber);
		
		updatedAccount.setToken("1234");
		service.createNewAccount(account);
		service.saveOrUpdate(updatedAccount);
		assertThat(service.findAll().size(), is(initialSize + 1));
		EmployeeAccount foundAccount = service.findByUserName(userName);
		assertThat(foundAccount.getPassword(), is(SAMPLE_EMPLOYEE.getPassword()));
		assertThat(foundAccount.getToken(), is(updatedAccount.getToken()));
	}
}
