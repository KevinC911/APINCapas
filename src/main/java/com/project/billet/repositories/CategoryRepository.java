package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	Category findOnebyName(String name);

}
