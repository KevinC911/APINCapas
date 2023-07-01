package com.project.billet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.billet.models.entities.Involved;
import com.project.billet.services.InvolvedService;

@RestController
@RequestMapping("")
public class InvolvedController {
	
	@Autowired
	InvolvedService involvedService;
	
	@PostMapping("/involved/add")
	public ResponseEntity<?> addInvolved(@ModelAttribute String name){
		Involved aInvolved = involvedService.findOneByName(name);
		
		if(aInvolved != null) {
			return new ResponseEntity<>("Involucrado ya existe", HttpStatus.FOUND);
		}
		
		try {
			involvedService.addInvolved(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Involucrado creado", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/involved/delete")
	public ResponseEntity<?> deleteInvolved(@ModelAttribute String name){
		Involved aInvolved = involvedService.findOneByName(name);
		
		if(aInvolved == null) {
			return new ResponseEntity<>("No se ha encontrado el involucrado a borrar", HttpStatus.NOT_FOUND);
		}
		
		try {
			involvedService.deleteInvolved(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Involved borrado con exito", HttpStatus.OK);
		
	}

}
