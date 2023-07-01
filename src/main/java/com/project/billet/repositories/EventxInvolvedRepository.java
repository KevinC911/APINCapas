package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.EventxInvolved;

public interface EventxInvolvedRepository extends JpaRepository<EventxInvolved, UUID>{

}
