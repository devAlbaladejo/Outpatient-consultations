package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Speciality;
import com.cex.backend.models.services.ISpecialityService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class SpecialityRestController {

	@Autowired
	private ISpecialityService specialityService;
	
	@GetMapping("/specialities")
	public List<Speciality> showSpecialities(){
		return specialityService.findAll();
	}
}
