package com.beta.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VendorReference {
	
	@Id
	@GeneratedValue
	private Long referenceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

	private String companyName;

	private String companyAddress;
	
	private String contactPerson;
	
	private String phoneNumber;
	
	private String emailAddress;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	public VendorReference(String companyName, String companyAddress, String contactPerson, String phoneNumber,
			String emailAddress, Date startDate, Date endDate) {
		super();
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	
	public VendorReference() {
		super();
	}


	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "VendorReference [referenceId=" + referenceId + ", companyName="
				+ companyName + ", companyAddress=" + companyAddress
				+ ", contactPerson=" + contactPerson + ", phoneNumber="
				+ phoneNumber + ", emailAddress=" + emailAddress
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", getReferenceId()=" + getReferenceId()
				+ ", getCompanyName()=" + getCompanyName()
				+ ", getCompanyAddress()=" + getCompanyAddress()
				+ ", getContactPerson()=" + getContactPerson()
				+ ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getEmailAddress()=" + getEmailAddress()
				+ ", getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
