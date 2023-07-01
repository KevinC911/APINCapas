package com.project.billet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.billet.models.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findOneByUsernameOrEmail(String username, String email);

}