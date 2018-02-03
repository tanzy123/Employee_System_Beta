package com.beta.service;

import com.beta.entity.Application;
import com.beta.entity.ApplicationResult;

public interface VettingRequestService {

	ApplicationResult vettingRequest(final Application application);
}
