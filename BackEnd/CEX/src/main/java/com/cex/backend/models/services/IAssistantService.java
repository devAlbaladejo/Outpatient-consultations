package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Assistant;

public interface IAssistantService {
	
	public List<Assistant> findAll();
	
	public void save(Assistant assistant);
	
	public Assistant findById(Long id);
	
	public void delete(Long id);
}
