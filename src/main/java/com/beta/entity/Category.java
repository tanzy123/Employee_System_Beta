package com.beta.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Category.findByNameAndRefNo",
                query="SELECT c FROM Category c where c.companyReferenceNumber = :companyReferenceNumber"
                		+ " AND c.categoryName = :categoryName"),
    @NamedQuery(name="Category.findByCompRefNo",
    query="SELECT c FROM Category c where c.companyReferenceNumber = :companyReferenceNumber"),
})
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810775852751032088L;

	@Id
	@GeneratedValue
	private Long categoryId;
	
    private String companyReferenceNumber;
	
	private String categoryName;

	public String getCategoryName() 
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Category() {
		super();
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", companyReferenceNumber=" + companyReferenceNumber
				+ ", categoryName=" + categoryName + "]";
	}
	
	
}