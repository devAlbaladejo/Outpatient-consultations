package com.cex.backend.models.dao;

/*
 * @author Ángel Albaladejo Flores
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.backend.models.entity.Speciality;

public interface ISpecialityDAO extends JpaRepository<Speciality, String>{

}
