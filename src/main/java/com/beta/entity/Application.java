package com.beta.entity;

import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Application {
	
	@Id
	@GeneratedValue
	private Long applicationId;
	
	@Column(nullable = false, unique = true)
	private String applicationRef;

	@OneToMany
	private List<VendorReference> vendorReferences;

	@ManyToOne
	@JoinColumn(name="categoryName")
	private Category category;

	private String POC;

	private String remarks;
	
	private Long companyId;
	
	private Long vendorId;
	
	private String currentStatus;

	private String approvalStatus;

	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	private Period vendorPeriod;
	
	private Date modifiedDate;

	private String officialRemarks;

	@OneToMany
	private List<Documents> supportingDocument;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Requirement> vetterRequirement;

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

	public Period getVendorPeriod() {
		return vendorPeriod;
	}

	public void setVendorPeriod(Period vendorPeriod) {
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

	public String getOfficialRemarks() {
		return officialRemarks;
	}

	public void setOfficialRemarks(String officialRemarks) {
		this.officialRemarks = officialRemarks;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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
	

}
