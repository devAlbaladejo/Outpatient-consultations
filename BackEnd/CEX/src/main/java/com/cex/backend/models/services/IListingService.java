package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Listing;

public interface IListingService {
	
	public List<Listing> findAll();
	
	public void save(Listing listing);
	
	public Listing findById(Long id);
	
	public void delete(Long id);

}
