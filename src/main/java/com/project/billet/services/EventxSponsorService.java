package com.project.billet.services;

import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.Sponsor;

public interface EventxSponsorService {
	void save (Event event, Sponsor sponsor) throws Exception;

}
