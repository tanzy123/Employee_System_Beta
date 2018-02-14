package com.beta;

import static com.beta.TestConstant.SAMPLE_COMPANY;
import static com.beta.TestConstant.SAMPLE_COMPANY_ADMINISTRATOR;
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

import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPACompanyAdministratorTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	CompanyAdminstratorAccountService service;
	
	@Autowired
	CompanyService companyService;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	}
	
	@Test
	@Rollback(value=false)
	public void testAddCompanyAdministratorAccount() {
		final int listSize = service.findAll().size();
		service.createNewAccount(SAMPLE_COMPANY_ADMINISTRATOR);
		assertThat(service.findAll().size(), is(listSize+1));
	}
	
	@Test
	public void testAddAndRemoveCompanyAdministratorAccount() {
		service.createNewAccount(SAMPLE_COMPANY_ADMINISTRATOR);
		final int listSize = service.findAll().size();
		CompanyAdministratorAccount account = service.findByUserName(SAMPLE_COMPANY_ADMINISTRATOR.getUserName());
		service.delete(account.getAccountId());
		assertThat(service.findAll().size(), is(listSize-1));
	}
	
	@Test
	public void testValidCompanyAdministratorAccount() {
		String password = SAMPLE_COMPANY_ADMINISTRATOR.getPassword();
		service.createNewAccount(SAMPLE_COMPANY_ADMINISTRATOR);
		CompanyAdministratorAccount accountToCheck = new 
				CompanyAdministratorAccount(SAMPLE_COMPANY_ADMINISTRATOR.getUserName(), password, SAMPLE_COMPANY_ADMINISTRATOR.getCompanyReferenceNumber());
		service.validateAccount(accountToCheck);
	}
	
	@Test
	public void testInvalidCompanyAdministratorAccount() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Invalid Username or Password");
		
		service.createNewAccount(SAMPLE_COMPANY_ADMINISTRATOR);
		CompanyAdministratorAccount accountToCheck = new 
				CompanyAdministratorAccount(SAMPLE_COMPANY_ADMINISTRATOR.getUserName(), "sdfgsdf", SAMPLE_COMPANY_ADMINISTRATOR.getCompanyReferenceNumber());
		service.validateAccount(accountToCheck);
	}
	
	@Test
	public void testAddAndUpdateCompanyAdministratorAccount() {
		int initialSize = service.findAll().size();
		CompanyAdministratorAccount account = SAMPLE_COMPANY_ADMINISTRATOR;
		String userName = SAMPLE_COMPANY_ADMINISTRATOR.getUserName();
		String password = SAMPLE_COMPANY_ADMINISTRATOR.getPassword();
		String companyReferenceNumber = SAMPLE_COMPANY_ADMINISTRATOR.getCompanyReferenceNumber();
		CompanyAdministratorAccount updatedAccount = new
				CompanyAdministratorAccount(userName, password, companyReferenceNumber);
		
		updatedAccount.setToken("1234");
		service.createNewAccount(account);
		service.saveOrUpdate(updatedAccount);
		assertThat(service.findAll().size(), is(initialSize + 1));
		CompanyAdministratorAccount foundAccount = service.findByUserName(userName);
		assertThat(foundAccount.getPassword(), is(SAMPLE_COMPANY_ADMINISTRATOR.getPassword()));
		assertThat(foundAccount.getToken(), is(updatedAccount.getToken()));
	}
}
