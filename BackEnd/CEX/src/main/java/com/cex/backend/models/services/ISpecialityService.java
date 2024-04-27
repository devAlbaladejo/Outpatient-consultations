package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Speciality;

public interface ISpecialityService {

	public List<Speciality> findAll();
}
