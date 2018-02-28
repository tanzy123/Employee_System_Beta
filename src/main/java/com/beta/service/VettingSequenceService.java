package com.beta.service;

import java.util.List;

import com.beta.entity.Requirement;

public interface VettingSequenceService {

	void validateVetterSequence(List<Requirement> requirementList);
}
