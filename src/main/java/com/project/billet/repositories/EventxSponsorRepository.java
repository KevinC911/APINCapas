package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.EventxSponsor;
import com.project.billet.models.entities.Sponsor;

public interface EventxSponsorRepository extends JpaRepository<EventxSponsor, UUID>{

}
