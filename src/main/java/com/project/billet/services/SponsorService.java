package com.project.billet.services;

import com.project.billet.models.entities.Sponsor;

public interface SponsorService {
	
	Sponsor findOneByName(String name);
	void addSponsor(String name) throws Exception;
	void deleteSponsor(String name) throws Exception;

}
