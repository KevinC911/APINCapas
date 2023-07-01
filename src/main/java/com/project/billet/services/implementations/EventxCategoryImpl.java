package com.project.billet.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Category;
import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.EventxCategory;

import com.project.billet.repositories.EventxCategoryRepository;
import com.project.billet.services.EventxCategoryService;

import jakarta.transaction.Transactional;

@Service
public class EventxCategoryImpl implements EventxCategoryService{
	
	@Autowired
	EventxCategoryRepository eventCategoryRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(Event event, Category category) throws Exception {
		EventxCategory aEvxCat = new EventxCategory(
				event, category);
		eventCategoryRepo.save(aEvxCat);
		
	}
	
	
}
