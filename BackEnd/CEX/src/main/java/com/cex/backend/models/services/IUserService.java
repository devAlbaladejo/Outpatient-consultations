package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Users;

public interface IUserService {
	
	public List<Users> findAll();
	
	public void save(Users user);
	
	public Users findById(Long id);
	
	public void delete(Long id);
}