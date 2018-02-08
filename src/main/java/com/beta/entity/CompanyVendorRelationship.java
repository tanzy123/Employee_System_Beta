package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompanyVendorRelationship {

	@Id
	@GeneratedValue
	private Long relationshipId;
	
	private String companyReferenceNumber;
	
	private String vendorReferenceNumber;

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

	public String getVendorReferenceNumber() {
		return vendorReferenceNumber;
	}

	public void setVendorReferenceNumber(String vendorReferenceNumber) {
		this.vendorReferenceNumber = vendorReferenceNumber;
	}
}
