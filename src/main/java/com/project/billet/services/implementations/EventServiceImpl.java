package com.project.billet.services.implementations;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.dtos.EventDataDTO;
import com.project.billet.models.entities.Event;
import com.project.billet.repositories.EventRepository;
import com.project.billet.services.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository eventRepository;

	@Override
	public Event findOneByTitle(String title) {
		Event aEvent = eventRepository.findOneByTitle(title);
		return aEvent;
	}

	@Override
	public void saveEvent(EventDataDTO info) throws Exception {
		Event aEvent = new Event(
				info.getTitle(),
				info.getImage(),
				info.getDate_hour(),
				info.getDuration_min(),
				info.isStatus());
		eventRepository.save(aEvent);
		
	}

	@Override
	public void deleteEventById(String id) throws Exception{
		UUID uid = UUID.fromString(id);
		eventRepository.deleteById(uid);
		
	}

	@Override
	public Event findOneById(String id) {
		UUID uid = UUID.fromString(id);
		Event event = eventRepository.findOneById(uid);
		return event;
	}
	
	

}
