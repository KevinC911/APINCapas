package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.Event;

public interface EventRepository extends JpaRepository<Event, UUID>{
	
	Event findOneByTitle(String title);
	Event findOneById(UUID id);

}
