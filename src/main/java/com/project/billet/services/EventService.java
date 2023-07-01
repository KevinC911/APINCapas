package com.project.billet.services;

import java.util.UUID;

import com.project.billet.models.dtos.EventDataDTO;
import com.project.billet.models.entities.Event;

public interface EventService {
	Event findOneByTitle(String title);
	Event findOneById(String id);
	void saveEvent(EventDataDTO info) throws Exception;
	void deleteEventById(String id) throws Exception;
	
}
