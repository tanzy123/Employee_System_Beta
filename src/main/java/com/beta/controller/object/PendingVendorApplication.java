package com.beta.controller.object;

import com.beta.entity.Application;
import com.beta.entity.Company;
import com.beta.entity.Requirement;

public class PendingVendorApplication {

	
	private Requirement requirement;
	private Company company;
	private Application application;
	
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public PendingVendorApplication(Requirement requirement,Company company,Application application)
	{
		super();
		this.requirement=requirement;
		this.company=company;
		this.application=application;
	}
	
	public PendingVendorApplication()
	{
		super();
	}
}
