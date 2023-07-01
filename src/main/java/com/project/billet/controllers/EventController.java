package com.project.billet.controllers;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.billet.models.dtos.CategoryToEventDTO;
import com.project.billet.models.dtos.ErrorsDTO;
import com.project.billet.models.dtos.EventDataDTO;
import com.project.billet.models.dtos.InvolvedToEventDTO;
import com.project.billet.models.dtos.MessageDTO;
import com.project.billet.models.dtos.SponsorToEventDTO;
import com.project.billet.models.entities.Category;
import com.project.billet.models.entities.Event;
import com.project.billet.models.entities.Involved;
import com.project.billet.models.entities.Sponsor;
import com.project.billet.services.CategoryService;
import com.project.billet.services.EventService;
import com.project.billet.services.EventxCategoryService;
import com.project.billet.services.EventxInvolvedService;
import com.project.billet.services.EventxSponsorService;
import com.project.billet.services.InvolvedService;
import com.project.billet.services.SponsorService;
import com.project.billet.utils.ErrorHandlers;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventxCategoryService eventxcategoryService;
	
	@Autowired
	private EventxSponsorService eventxsponsorService;
	
	@Autowired
	private EventxInvolvedService eventxinvolvedService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private InvolvedService involvedService;
	
	@Autowired
	private ErrorHandlers errorHandler;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEvent(@ModelAttribute @Valid EventDataDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			eventService.saveEvent(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Evento creado", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> deleteEvent(@PathVariable(name = "code") String eventCode){
		
		Event aEvent = eventService.findOneById(eventCode);
		if(aEvent == null) {
			return new ResponseEntity<>(new MessageDTO("Evento no encontrado para borrar"), HttpStatus.NOT_FOUND);
		}
		try {
			eventService.deleteEventById(eventCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Evento borrado", HttpStatus.OK);
	}
	
	@PostMapping("/addcategory")
	public ResponseEntity<?> categoryToEvent(@ModelAttribute @Valid CategoryToEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		Event aEvent = eventService.findOneById(info.getId());
		Category aCategory = categoryService.findOneByName(info.getCategoryName());
		
		try {
			eventxcategoryService.save(aEvent, aCategory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Categoria asignada a evento", HttpStatus.CREATED);
		
	}
	
	@PostMapping("/addsponsor")
	public ResponseEntity<?> sponsorToEvent(@ModelAttribute @Valid SponsorToEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		Event aEvent = eventService.findOneById(info.getId());
		Sponsor sponsor = sponsorService.findOneByName(info.getSponsorName());
		
		try {
			eventxsponsorService.save(aEvent, sponsor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Patrocinador asignada a evento", HttpStatus.CREATED);
		
	}
	
	@PostMapping("/addinvolved")
	public ResponseEntity<?> involvedToEvent(@ModelAttribute @Valid InvolvedToEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST);
		}
		
		Event aEvent = eventService.findOneById(info.getId());
		Involved involved = involvedService.findOneByName(info.getInvolvedName());
		
		try {
			eventxinvolvedService.save(aEvent, involved);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("Involucrado asignada a evento", HttpStatus.CREATED);
		
	}
}
