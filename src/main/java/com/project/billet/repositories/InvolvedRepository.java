package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.Involved;

public interface InvolvedRepository extends JpaRepository<Involved, UUID> {
	Involved findOnebyName(String name);
}
