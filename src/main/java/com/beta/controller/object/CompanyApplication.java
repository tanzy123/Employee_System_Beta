package com.beta.controller.object;

import com.beta.entity.Application;
import com.beta.entity.Company;

public class CompanyApplication {

	private Company company;
	private Application application;
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
	public CompanyApplication(Company company, Application application) {
		super();
		this.company = company;
		this.application = application;
	}
	public CompanyApplication() {
		super();
	}
}
