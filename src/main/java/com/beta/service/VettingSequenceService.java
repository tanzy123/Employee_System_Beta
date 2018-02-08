package com.beta.service;

import java.util.List;

import com.beta.entity.Requirement;

public interface VettingSequenceService {

	List<Requirement> VetterSequenceGenerator(Requirement requirement);

}
