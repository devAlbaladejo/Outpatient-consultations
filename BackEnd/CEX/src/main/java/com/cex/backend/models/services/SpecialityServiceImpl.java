package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.ISpecialityDAO;
import com.cex.backend.models.entity.Speciality;

@Service
public class SpecialityServiceImpl implements ISpecialityService{
	
	@Autowired
	private ISpecialityDAO specialityDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Speciality> findAll(){
		return (List<Speciality>) specialityDao.findAll(Sort.by(Sort.Direction.ASC,"name"));
	}	
}
