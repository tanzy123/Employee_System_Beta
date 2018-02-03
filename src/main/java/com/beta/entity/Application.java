package com.beta.entity;

import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Application {
	
	@Id
	private Long applicationId;
	
	private Company company;

	private List<String> vendorReferences;

	@ManyToOne
	private Category category;

	private String POC;

	private String remarks;

	private ApprovalStatus approvalStatus;

	private Date applicationDate;

	private Period vendorPeriod;

	@OneToOne
	private Official official;

	private List<Documents> supportingDocument;

	private List<Requirement> clientRequirement;

	private List<Employee> vetter;

	// start date
	// application date
	// status in open ended enum
	// All application request should have a type so that we can send it to the
	// right approver

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

	public List<String> getVendorReferences() {
		return vendorReferences;
	}

	public void setVendorReferences(List<String> vendorReferences) {
		this.vendorReferences = vendorReferences;
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

	public Official getOfficial() {
		return official;
	}

	public void setOfficial(Official official) {
		this.official = official;
	}

	public List<Documents> getSupportingDocument() {
		return supportingDocument;
	}

	public void setSupportingDocument(List<Documents> supportingDocument) {
		this.supportingDocument = supportingDocument;
	}

	public List<Employee> getVetter() {
		return vetter;
	}

	public void setVetter(List<Employee> vetter) {
		this.vetter = vetter;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Requirement> getClientRequirement() {
		return clientRequirement;
	}

	public void setClientRequirement(List<Requirement> clientRequirement) {
		this.clientRequirement = clientRequirement;
	}

}
