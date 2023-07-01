package com.project.billet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.billet.models.entities.Sponsor;
import com.project.billet.services.SponsorService;

@RestController
@RequestMapping("")
public class SponsorController {
	
	@Autowired
	private SponsorService sponsorService;
	
	@PostMapping("/sponsor/add")
	public ResponseEntity<?> addSponsor(@ModelAttribute String name){
		Sponsor aSponsor = sponsorService.findOneByName(name);
		
		if(aSponsor != null) {
			return new ResponseEntity<>("Sponsor ya existe", HttpStatus.FOUND);
		}
		
		try {
			sponsorService.addSponsor(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Sponsor creado", HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/sponsor/delete")
	public ResponseEntity<?> deleteSponsor(@ModelAttribute String name){
		Sponsor aSponsor = sponsorService.findOneByName(name);
		
		if(aSponsor == null) {
			return new ResponseEntity<>("No se ha encontrado el patrocinador a borrar", HttpStatus.NOT_FOUND);
		}
		
		try {
			sponsorService.deleteSponsor(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Patrocinador borrado con exito", HttpStatus.OK);
		
		
	}

}
