package com.project.billet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.billet.models.entities.Category;
import com.project.billet.services.CategoryService;
import com.project.billet.services.EventService;
import com.project.billet.services.EventxCategoryService;

@RestController
@RequestMapping("")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/category/add")
	public ResponseEntity<?> addCategory(@ModelAttribute String name) {
		Category aCategory = categoryService.findOneByName(name);
		
		if(aCategory != null) {
			return new ResponseEntity<>("Categoria ya existente", HttpStatus.FOUND);
		}
		try {
			categoryService.addCategory(name);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Categoria creada", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/category/delete")
	public ResponseEntity<?> deleteCategory(@ModelAttribute String name){
		Category aCategory = categoryService.findOneByName(name);
		
		if(aCategory == null) {
			return new ResponseEntity<>("No se ha encontrado la categoria a borrar", HttpStatus.NOT_FOUND);
		}
		try {
			categoryService.deleteCategory(name);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Categoria borrada con exito", HttpStatus.OK);
	}
	

}
