package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.billet.models.entities.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor, UUID>{
	
	Sponsor findOnebyName(String name);

}
