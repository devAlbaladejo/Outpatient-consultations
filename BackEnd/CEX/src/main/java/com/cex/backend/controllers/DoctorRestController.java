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

import com.cex.backend.models.entity.Doctor;
import com.cex.backend.models.services.IDoctorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class DoctorRestController {

	@Autowired
	private IDoctorService doctorService;
	
	@GetMapping("/doctors")
	public List<Doctor> showDoctors(){
		return doctorService.findAll();
	}
	
	@GetMapping("/doctors/{id}")
	public ResponseEntity<?> showDoctor(@PathVariable Long id){
		Doctor doctor = doctorService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if(doctor == null) {
			response.put("message", "The doctor with ID(".concat(id.toString().concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}
	
	@PostMapping("/doctors/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
		
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
			doctor.setActive(1);
			doctorService.save(doctor);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Doctor has been created!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/doctors/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateDoctor(@Valid @RequestBody Doctor doctor, BindingResult result, @PathVariable Long id) {
		Doctor currentDoctor = this.doctorService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentDoctor == null) {
			response.put("message", "The doctor with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			if(doctor.getName() != null)
				currentDoctor.setName(doctor.getName());
			if(doctor.getSpeciality() != null)
				currentDoctor.setSpeciality(doctor.getSpeciality());
			if(doctor.getActive() != currentDoctor.getActive())
				currentDoctor.setActive(doctor.getActive());
			doctorService.save(currentDoctor);
		}catch(DataAccessException e) {
			response.put("message", "Error to update the doctor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("message", "Doctor has been updated!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/doctors/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
		Doctor currentDoctor = this.doctorService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentDoctor == null) {
			response.put("message", "The doctor with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentDoctor.setActive(0);
			doctorService.save(currentDoctor);
		}catch(DataAccessException e) {
			response.put("message", "Error to delete the doctor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Doctor has been deleted!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
