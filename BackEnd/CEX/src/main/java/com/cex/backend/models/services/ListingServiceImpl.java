package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IListingDAO;
import com.cex.backend.models.entity.Listing;

@Service
public class ListingServiceImpl implements IListingService{

	@Autowired
	private IListingDAO listingDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Listing> findAll(){
		return (List<Listing>) listingDao.findAll(Sort.by(Sort.Direction.ASC,"date").and(Sort.by(Sort.Direction.ASC,"room")));
	}
	
	@Override
	@Transactional
	public void save(Listing listing) {
		listingDao.save(listing);
	}

	@Override
	@Transactional(readOnly = true)
	public Listing findById(Long id) {
		return listingDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		listingDao.deleteById(id);
	}
}
