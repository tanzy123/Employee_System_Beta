package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CompanyAdministrator {

	@Id
	@GeneratedValue
	private Long adminId;

	@OneToOne
	@JoinColumn(name = "companyEmail")
	private Company company;

	private String userName;

	private String password;

	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.COMPANY_ADMINISTRATOR;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public LogInType getLogInType() {
		return logInType;
	}

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

}
