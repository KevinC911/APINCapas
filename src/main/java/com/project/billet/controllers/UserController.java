package com.project.billet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.billet.models.dtos.ErrorsDTO;
import com.project.billet.models.dtos.LogInUserDataDTO;
import com.project.billet.models.dtos.MessageDTO;
import com.project.billet.models.dtos.TokenDTO;
import com.project.billet.models.dtos.UserDataDTO;
import com.project.billet.models.entities.Token;
import com.project.billet.models.entities.User;
import com.project.billet.services.UserService;
import com.project.billet.utils.ErrorHandlers;

import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ErrorHandlers errorHandler;
	
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@ModelAttribute @Valid UserDataDTO info, BindingResult validations) {
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		User tempEmail = userService.getUserByIdentifier(info.getEmail());
		User tempUserName = userService.getUserByIdentifier(info.getUsername());
		if(tempEmail != null || tempUserName != null) {
			return new ResponseEntity<>("El usuario ya existe", 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			userService.register(info);
			return new ResponseEntity<>(new MessageDTO("User Created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("auth/login")
	public ResponseEntity<?> login(@ModelAttribute @Valid LogInUserDataDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.getUserByIdentifier(info.getIdentifier());
		//System.out.println(userService.comparePassword(info.getPassword(), user.getPassword()));
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(!userService.comparePassword(info.getPassword(), user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			Token token = userService.registerToken(user);
			return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
