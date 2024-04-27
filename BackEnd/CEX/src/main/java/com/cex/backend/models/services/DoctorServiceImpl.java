package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IDoctorDAO;
import com.cex.backend.models.entity.Doctor;

@Service
public class DoctorServiceImpl implements IDoctorService{

	@Autowired
	private IDoctorDAO doctorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAll(){
		return (List<Doctor>) doctorDao.findAll(Sort.by(Sort.Direction.ASC,"speciality.name").and(Sort.by(Sort.Direction.ASC, "name")));
	}

	@Override
	@Transactional
	public void save(Doctor doctor) {
		doctorDao.save(doctor);
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor findById(Long id) {
		return doctorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		doctorDao.deleteById(id);
	}
}
