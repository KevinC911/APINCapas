package com.project.billet.services;

import com.project.billet.models.entities.Category;
import com.project.billet.models.entities.Event;

public interface EventxCategoryService {
	void save (Event event, Category category) throws Exception;
}
