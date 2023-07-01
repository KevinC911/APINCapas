package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.EventxCategory;

public interface EventxCategoryRepository extends JpaRepository<EventxCategory, UUID>{
	

}
