package com.beta.controller;

import javax.validation.constraints.NotNull;

public class Companies {

	
	@NotNull
	private String companyName;

	private int companyId;

	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
