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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyReferenceNumber == null) ? 0 : companyReferenceNumber.hashCode());
		result = prime * result + ((vendorReferenceNumber == null) ? 0 : vendorReferenceNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyVendorRelationship other = (CompanyVendorRelationship) obj;
		if (companyReferenceNumber == null) {
			if (other.companyReferenceNumber != null)
				return false;
		} else if (!companyReferenceNumber.equals(other.companyReferenceNumber))
			return false;
		if (vendorReferenceNumber == null) {
			if (other.vendorReferenceNumber != null)
				return false;
		} else if (!vendorReferenceNumber.equals(other.vendorReferenceNumber))
			return false;
		return true;
	}
	
	
}
