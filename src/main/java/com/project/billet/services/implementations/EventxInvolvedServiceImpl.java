package com.project.billet.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.EventxInvolved;
import com.project.billet.models.entities.Involved;
import com.project.billet.repositories.EventxInvolvedRepository;
import com.project.billet.services.EventxInvolvedService;

@Service
public class EventxInvolvedServiceImpl implements EventxInvolvedService{
	
	@Autowired
	EventxInvolvedRepository eventxinvolvedRepository;

	@Override
	public void save(Event event, Involved involved) throws Exception {
		EventxInvolved aEventInvolved = new EventxInvolved(
				event, involved);
		eventxinvolvedRepository.save(aEventInvolved);
		
	}
	
	

}
