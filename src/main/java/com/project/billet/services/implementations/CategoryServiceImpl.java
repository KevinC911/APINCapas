package com.project.billet.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Category;
import com.project.billet.repositories.CategoryRepository;
import com.project.billet.services.CategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category findOneByName(String name) {
		Category aCategory = categoryRepository.findOnebyName(name);
		return aCategory;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void addCategory(String name) throws Exception{
		Category aCategory = new Category();
		aCategory.setName(name);
		categoryRepository.save(aCategory);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteCategory(String name) throws Exception{
		List<Category> catList = categoryRepository.findAll();
		Category tempCategory = catList.stream()
				.filter(category -> category.getName().equals(name))
				.findAny()
				.orElse(null);
		categoryRepository.deleteById(tempCategory.getCode());
	}

}
