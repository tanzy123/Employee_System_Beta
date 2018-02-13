package com.beta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.Department;
import com.beta.entity.Documents;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.entity.Role;
import com.beta.entity.VendorReference;

public class TestConstant {
	public static final Role SAMPLE_ROLE1 = new Role("Manager");
	public static final Role SAMPLE_ROLE2 = new Role("Director");
	public static final List<Role> SAMPLE_ROLE_LIST;
	
	public static final Department SAMPLE_DEPARTMENT1 = new Department("Sales");
	public static final Department SAMPLE_DEPARTMENT2 = new Department("IT");
	public static final Department SAMPLE_DEPARTMENT3 = new Department("HR");
	public static final List<Department> SAMPLE_DEPARTMENT_LIST;
	
	public static final VendorReference SAMPLE_REFERENCE1 = new VendorReference("Panasonic", "Japan", "Tatsuro", 
			"+12314432", "pan1234@pan.com", new Date(800L), new Date(800000L));
	public static final VendorReference SAMPLE_REFERENCE2 = new VendorReference("Honda", "Japan", "Kei", 
			"+544314432", "pan1234@pan.com", new Date(800000L), new Date(1000000L));
	public static final List<VendorReference> SAMPLE_REFERENCE_LIST;
	
	public static final Category SAMPLE_CATEGORY1 = new Category("Utilities");
	public static final Category SAMPLE_CATEGORY2 = new Category("HR");
	public static final Category SAMPLE_CATEGORY3 = new Category("Food");
	public static final Category SAMPLE_CATEGORY4 = new Category("IT");
	public static final List<Category> SAMPLE_CATEGORY_LIST;
	
	public static final Documents SAMPLE_DOCUMENT1 = new Documents("asd".getBytes());
	public static final Documents SAMPLE_DOCUMENT2 = new Documents("asd11".getBytes());
	public static final List<Documents> SAMPLE_DOCUMENT_LIST;
	
	public static final Requirement SAMPLE_REQUIREMENT1 = new Requirement("E102023", "onboard ASAP", ApprovalStatus.PENDING);
	public static final Requirement SAMPLE_REQUIREMENT2 = new Requirement("RX1234", "need more money", ApprovalStatus.PENDING);
	public static final List<Requirement> SAMPLE_REQUIREMENT_LIST;
	
	public static final Company SAMPLE_VENDOR;
	public static final List<Company> SAMPLE_VENDOR_LIST;
	
	public static final Company SAMPLE_CLIENT;
	public static final List<Company> SAMPLE_CLIENT_LIST;
	
	public static final Application SAMPLE_APPLICATION1;
	public static final Application SAMPLE_APPLICATION2;
	public static final Application SAMPLE_APPLICATION3;
	public static final List<Application> SAMPLE_APPLICATION_LIST;
	
	public static final Company SAMPLE_COMPANY;
	
	public static final EmployeeAccount SAMPLE_EMPLOYEE;
	
	public static final CompanyAdministratorAccount SAMPLE_COMPANY_ADMINISTRATOR;
	
	static {
		List<Role> roleList = new ArrayList<>();
		roleList.add(SAMPLE_ROLE1);
		roleList.add(SAMPLE_ROLE2);
		SAMPLE_ROLE_LIST = Collections.unmodifiableList(roleList);
		
		List<Department> departmentList = new ArrayList<>();
		departmentList.add(SAMPLE_DEPARTMENT1);
		departmentList.add(SAMPLE_DEPARTMENT2);
		departmentList.add(SAMPLE_DEPARTMENT3);
		SAMPLE_DEPARTMENT_LIST = Collections.unmodifiableList(departmentList);
		
		List<VendorReference> referenceList = new ArrayList<>();
		referenceList.add(SAMPLE_REFERENCE1);
		referenceList.add(SAMPLE_REFERENCE2);
		SAMPLE_REFERENCE_LIST = Collections.unmodifiableList(referenceList);
		
		List<Category> categoryList = new ArrayList<>();
		categoryList.add(SAMPLE_CATEGORY1);
		categoryList.add(SAMPLE_CATEGORY2);
		SAMPLE_CATEGORY_LIST = Collections.unmodifiableList(categoryList);
		
		List<Documents> documentList = new ArrayList<>();
		documentList.add(SAMPLE_DOCUMENT1);
		documentList.add(SAMPLE_DOCUMENT2);
		SAMPLE_DOCUMENT_LIST = Collections.unmodifiableList(documentList);
		
		List<Requirement> requirementList = new ArrayList<>();
		requirementList.add(SAMPLE_REQUIREMENT1);
		requirementList.add(SAMPLE_REQUIREMENT2);
		SAMPLE_REQUIREMENT_LIST = Collections.unmodifiableList(requirementList);
		
		SAMPLE_VENDOR = new Company("CS123213", "Credit-Suisse", 
				"Switzerland", "cs@cs.com", "+31123123", "www.creditsuisse.com",
				65000000L, SAMPLE_ROLE_LIST, SAMPLE_DEPARTMENT_LIST, SAMPLE_CATEGORY_LIST);
		
		List<Company> vendorList = new ArrayList<>();
		vendorList.add(SAMPLE_VENDOR);
		SAMPLE_VENDOR_LIST = Collections.unmodifiableList(vendorList);
		
		SAMPLE_CLIENT = new Company("GOV1232", "Gov-Tech", 
				"Singapore", "gov@gov.com", "+1243432", "www.govtech.com",
				1023023L, SAMPLE_ROLE_LIST, SAMPLE_DEPARTMENT_LIST, SAMPLE_CATEGORY_LIST);
		
		List<Company> clientList = new ArrayList<>();
		clientList.add(SAMPLE_CLIENT);
		SAMPLE_CLIENT_LIST = Collections.unmodifiableList(clientList);
		
		SAMPLE_APPLICATION1 = new Application("ASD1111", SAMPLE_REFERENCE_LIST, SAMPLE_CATEGORY1,
				"CS123213", "Johhny", "No remarks", ApplicationStatus.VETTING, new Date(1000000L), 
				1L, SAMPLE_DOCUMENT_LIST, SAMPLE_REQUIREMENT_LIST);
		
		SAMPLE_APPLICATION2 = new Application("YRS1231", SAMPLE_REFERENCE_LIST, SAMPLE_CATEGORY1,
				"CS123213", "Mary", "No remarks", ApplicationStatus.VETTING, new Date(1000L), 
				1L, SAMPLE_DOCUMENT_LIST, SAMPLE_REQUIREMENT_LIST);
		
		SAMPLE_APPLICATION3 = new Application("ZZC123", SAMPLE_REFERENCE_LIST, SAMPLE_CATEGORY1,
				"5634654gf", "Richard", "No remarks", ApplicationStatus.REJECT, new Date(103300L), 
				1L, SAMPLE_DOCUMENT_LIST, SAMPLE_REQUIREMENT_LIST);
		
		List<Application> applicationList = new ArrayList<>();
		applicationList.add(SAMPLE_APPLICATION1);
		applicationList.add(SAMPLE_APPLICATION2);
		SAMPLE_APPLICATION_LIST = Collections.unmodifiableList(applicationList);
		
		SAMPLE_COMPANY = new Company("CTS123", "Cognizant", 
				"India", "ZhiYi.Tan@cognizant.com", "+6122343243", "www.cognizant.com", 
				25000000L, SAMPLE_APPLICATION_LIST, 
				SAMPLE_ROLE_LIST, SAMPLE_DEPARTMENT_LIST, SAMPLE_CATEGORY_LIST);
		
		SAMPLE_EMPLOYEE = new EmployeeAccount("Employee123", "123123", 
				"645686", "hello@cognizant.com", "+54243325", 
				"CTS123", SAMPLE_ROLE1, SAMPLE_DEPARTMENT1);
		
		SAMPLE_COMPANY_ADMINISTRATOR = new CompanyAdministratorAccount("CTS222111", "asd", "CTS123");
	}
}
