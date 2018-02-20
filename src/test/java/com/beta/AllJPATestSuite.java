package com.beta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	JPAApplicationServiceTest.class, 
	JPACategoryServiceTest.class, 
	JPACategoryServiceTestByZQ.class,
	JPACompanyAdministratorTest.class, 
	JPACompanyServiceTest.class,
	JPACompanyApplicationTest.class,
	JPAEmployeeAccountTest.class,  
	JPARegistrationServiceTest.class, 
	JPARoleServiceTest.class,
	JPARequirementServiceTest.class,
	JPAVendorApplicationTest.class,
	JPAVendorReferenceTests.class} )
public class AllJPATestSuite {}
