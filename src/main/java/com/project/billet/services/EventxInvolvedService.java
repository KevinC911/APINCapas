package com.project.billet.services;

import com.project.billet.models.entities.Category;
import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.Involved;

public interface EventxInvolvedService {
	void save (Event event, Involved involved) throws Exception;
}
