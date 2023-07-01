package com.project.billet.services;

import com.project.billet.models.entities.Category;

public interface CategoryService {
	
	Category findOneByName(String name);
	void addCategory(String name) throws Exception;
	void deleteCategory(String name) throws Exception;

}
