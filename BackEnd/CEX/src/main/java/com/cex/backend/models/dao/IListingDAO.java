package com.cex.backend.models.dao;

/*
 * @author Ángel Albaladejo Flores
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.backend.models.entity.Listing;

public interface IListingDAO extends JpaRepository<Listing, Long>{

}
