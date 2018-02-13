package com.beta.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;

@MappedSuperclass
@NamedQuery(name="findToken", query="select p.token from company c join c.CompanyAdministor p where companyEmail=:companyEmail") 
public class UserAccount {

	@Id
	@GeneratedValue
	private Long accountId;

	private String token;

	private String userName;

	private String password;
	
	private String companyReferenceNumber;
	
	private Boolean isValidated;
	
	private Boolean isLogin;
	

	public UserAccount(String userName, String password, String companyReferenceNumber) {
		this.userName = userName;
		this.password = password;
		this.companyReferenceNumber = companyReferenceNumber;
	}
	
	public UserAccount() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(Boolean isValidated) {
		this.isValidated = isValidated;
	}

	public String getCompanyReferenceNumber() {
		return companyReferenceNumber;
	}

	public void setCompanyReferenceNumber(String companyReferenceNumber) {
		this.companyReferenceNumber = companyReferenceNumber;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
}
