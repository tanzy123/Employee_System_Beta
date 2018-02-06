package com.beta.service;

import com.beta.entity.Application;

public interface VendorApplication {

	Application collectVendorApplication(Application application);
	Application validateVendorApplication(Application application);
}
