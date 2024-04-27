package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Room;

public interface IRoomService {

	public List<Room> findAll();
	
	public void save(Room room);
	
	public Room findById(Long id);
}
