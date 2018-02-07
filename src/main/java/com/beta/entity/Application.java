package com.beta.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7471758161505829102L;

	@Id
	@GeneratedValue
	private Long applicationId;

	@Column(nullable = false, unique = true)
	private String applicationRef;

	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VendorReference> vendorReferences;

	@ManyToOne
	@JoinColumn(name = "categoryName", referencedColumnName="categoryName")
	private Category category;

	private String companyReferenceNumber;

	private String vendorReferenceNumber;

	private String POC;

	private String remarks;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	private Long vendorPeriod;

	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="applicationRef", referencedColumnName="applicationRef")
	private List<Documents> supportingDocument;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="applicationRef", referencedColumnName="applicationRef")
	private List<Requirement> vetterRequirement;

	public Application(String applicationRef, List<VendorReference> vendorReferences, Category category, String vendorReferenceNumber,
			String pOC, String remarks, ApplicationStatus applicationStatus, Date applicationDate, Long vendorPeriod,
			List<Documents> supportingDocument, List<Requirement> vetterRequirement) {
		super();
		this.applicationRef = applicationRef;
		this.vendorReferences = vendorReferences;
		this.category = category;
		this.vendorReferenceNumber = vendorReferenceNumber;
		POC = pOC;
		this.remarks = remarks;
		this.applicationStatus = applicationStatus;
		this.applicationDate = applicationDate;
		this.vendorPeriod = vendorPeriod;
		this.supportingDocument = supportingDocument;
		this.vetterRequirement = vetterRequirement;
	}

	public Application() {
		super();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPOC() {
		return POC;
	}

	public void setPOC(String pOC) {
		POC = pOC;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getVendorPeriod() {
		return vendorPeriod;
	}

	public void setVendorPeriod(Long vendorPeriod) {
		this.vendorPeriod = vendorPeriod;
	}

	public List<Documents> getSupportingDocument() {
		return supportingDocument;
	}

	public void setSupportingDocument(List<Documents> supportingDocument) {
		this.supportingDocument = supportingDocument;
	}

	public List<Requirement> getVettorRequirement() {
		return vetterRequirement;
	}

	public void setVettorRequirement(List<Requirement> clientRequirement) {
		this.vetterRequirement = clientRequirement;
	}

	public List<VendorReference> getVendorReferences() {
		return vendorReferences;
	}

	public void setVendorReferences(List<VendorReference> vendorReferences) {
		this.vendorReferences = vendorReferences;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getApplicationRef() {
		return applicationRef;
	}

	public void setApplicationRef(String applicationRef) {
		this.applicationRef = applicationRef;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

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
