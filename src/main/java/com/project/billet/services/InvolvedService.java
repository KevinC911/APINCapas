package com.project.billet.services;

import com.project.billet.models.entities.Involved;

public interface InvolvedService {
	Involved findOneByName(String name);
	void addInvolved(String name) throws Exception;
	void deleteInvolved(String name) throws Exception;
}
