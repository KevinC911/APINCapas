package com.project.billet.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.EventxSponsor;
import com.project.billet.models.entities.Sponsor;
import com.project.billet.repositories.EventxSponsorRepository;
import com.project.billet.services.EventxSponsorService;

import jakarta.transaction.Transactional;

@Service
public class EventxSponsorServiceImpl implements EventxSponsorService{
	
	@Autowired
	EventxSponsorRepository eventxsponsorRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(Event event, Sponsor sponsor) throws Exception {
		EventxSponsor aEventSponsor = new EventxSponsor(
				event, sponsor);
		eventxsponsorRepository.save(aEventSponsor);
		
	}

}
