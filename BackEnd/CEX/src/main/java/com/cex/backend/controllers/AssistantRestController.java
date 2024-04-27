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

import com.cex.backend.models.entity.Assistant;
import com.cex.backend.models.services.IAssistantService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class AssistantRestController {

	@Autowired
	private IAssistantService assistantService;
	
	@GetMapping("/assistants")
	public List<Assistant> showAssistants(){
		return assistantService.findAll();
	}
	
	@GetMapping("/assistants/{id}")
	public ResponseEntity<?> showAssistant(@PathVariable Long id){
		Assistant assistant = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			assistant = assistantService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(assistant == null) {
			response.put("message", "The assistant ID(".concat(id.toString().concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Assistant>(assistant, HttpStatus.OK);
	}
	
	@PostMapping("/assistants/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createAssistant(@Valid @RequestBody Assistant assistant, BindingResult result) {
		
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
			assistant.setActive(1);
			assistantService.save(assistant);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Assistant has been created!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/assistants/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateAssistant(@Valid @RequestBody Assistant assistant, BindingResult result, @PathVariable Long id) {
		Assistant currentAssistant = this.assistantService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentAssistant == null) {
			response.put("message", "The assistant ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			if(assistant.getName() != null)
				currentAssistant.setName(assistant.getName());
			if(assistant.getColor() != null)
				currentAssistant.setColor(assistant.getColor());
			if(assistant.getActive() != currentAssistant.getActive())
				currentAssistant.setActive(assistant.getActive());
			assistantService.save(currentAssistant);
		}catch(DataAccessException e) {
			response.put("message", "Error to update the assistant");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("message", "Assistant has been updated!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/assistants/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteAssistant(@PathVariable Long id) {
		Assistant currentAssistant = this.assistantService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentAssistant == null) {
			response.put("message", "The assistant ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentAssistant.setActive(0);
			assistantService.save(currentAssistant);
		}catch(DataAccessException e) {
			response.put("message", "Error to delete assistant");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Assistant has been deleted!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
