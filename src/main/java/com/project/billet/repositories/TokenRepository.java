package com.project.billet.repositories;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.Token;
import com.project.billet.models.entities.User;

public interface TokenRepository 
extends JpaRepository<Token, UUID>{ 

List<Token> findByUserAndActive(User user, Boolean active);

}
