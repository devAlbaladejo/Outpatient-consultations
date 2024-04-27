package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Room;
import com.cex.backend.models.services.IRoomService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class RoomRestController {

	@Autowired
	private IRoomService roomService;
	
	@GetMapping("/rooms")
	public List<Room> showRooms(){
		return roomService.findAll();
	}
	
	@PutMapping("/rooms/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateRoom(@Valid @RequestBody Room room, BindingResult result, @PathVariable Long id) {
		Room currentRoom = this.roomService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentRoom == null) {
			response.put("message", "Room with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentRoom.setActive(room.getActive());
			roomService.save(currentRoom);
		}catch(DataAccessException e) {
			response.put("message", "Error to update room");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("message", "Room has been updated!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
