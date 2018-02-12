package com.beta.service;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;

public interface VendorVettingProcess {

	void vetVendor(String userName, Application application, ApprovalStatus updatedStatus, String requirements) throws Exception;
}
