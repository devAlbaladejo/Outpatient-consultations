package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IRoomDAO;
import com.cex.backend.models.entity.Room;

@Service
public class RoomServiceImpl implements IRoomService {

	@Autowired
	private IRoomDAO roomDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Room> findAll(){
		return (List<Room>) roomDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
	}
	
	@Override
	@Transactional
	public void save(Room room) {
		roomDao.save(room);
	}

	@Override
	@Transactional(readOnly = true)
	public Room findById(Long id) {
		return roomDao.findById(id).orElse(null);
	}
}
