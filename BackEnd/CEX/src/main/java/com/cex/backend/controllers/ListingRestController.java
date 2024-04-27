package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Listing;
import com.cex.backend.models.services.IListingService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ListingRestController {
	
	@Autowired
	private IListingService listingService;
	
	@GetMapping("/listings")
	public List<Listing> showListings(){
		return listingService.findAll();
	}
	
	@GetMapping("/listings/{id}")
	public ResponseEntity<?> showListing(@PathVariable Long id){
		Listing listing = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			listing = listingService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(listing == null) {
			response.put("message", "Listing with ID(".concat(id.toString().concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Listing>(listing, HttpStatus.OK);
	}
	
	@PostMapping("/listings/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createListing(@Valid @RequestBody Listing listing, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			listingService.save(listing);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Listing has been created!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/listings/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateListing(@Valid @RequestBody Listing listing, BindingResult result, @PathVariable Long id) {
		Listing currentListing = this.listingService.findById(id);
		
		Map<String, Object> response = new HashMap<>();

		
		if(currentListing == null) {
			response.put("message", "Listing with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			if(listing.getDoctor() != null)
				currentListing.setDoctor(listing.getDoctor());
			if(listing.getDoctorComment() != null)
				currentListing.setDoctorComment(listing.getDoctorComment());
			
			currentListing.setAssistant(listing.getAssistant());
			
			if(listing.getAssistantComment() != null)
				currentListing.setAssistantComment(listing.getAssistantComment());
			if(listing.getRoom() != null)
				currentListing.setRoom(listing.getRoom());
			if(listing.getDate() != null)
				currentListing.setDate(listing.getDate());
			if(listing.getSchedule() != null)
				currentListing.setSchedule(listing.getSchedule());
			
			currentListing.setDiary(listing.getDiary());
				
			listingService.save(currentListing);
		}catch(DataAccessException e) {
			response.put("message", "Error to update Listing");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("message", "Listing has been updated!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/listings/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteListing(@PathVariable Long id) {		
		Listing currentListing = this.listingService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentListing == null) {
			response.put("message", "Listing with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			listingService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error to delete Listing");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Listing has been deleted!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
