package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Doctor;

public interface IDoctorService {

	public List<Doctor> findAll();
	
	public void save(Doctor doctor);
	
	public Doctor findById(Long id);
	
	public void delete(Long id);
}
