package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.HashMap;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Users;
import com.cex.backend.models.services.IUserService;
import com.cex.backend.utils.Utils;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public List<Users> getUsers(){
		return (List<Users>) userService.findAll();
	}
	
	@GetMapping("/users/{email}/{password}")
	public Users getUser(@PathVariable String email, @PathVariable String password){
		
		String encriptedPassword = Utils.encriptPasswword(password);
		
		List<Users> allUsers = userService.findAll();
		
		Users user = allUsers.stream().filter(e -> e.getEmail().equals(email) && e.getPassword().equals(encriptedPassword)).findFirst().orElse(null);
				
		return user;
	}
	
	@PostMapping("/users/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@Valid @RequestBody Users user, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			user.setToken(Utils.generateNewToken());
			user.setPassword(Utils.encriptPasswword(user.getPassword()));
			
			userService.save(user);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "User has been created!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateUser(@Valid @RequestBody Users user, BindingResult result, @PathVariable Long id) {
		Users currentUser = this.userService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentUser == null) {
			response.put("message", "User with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			if(user.getEmail() != null)
				currentUser.setEmail(user.getEmail());
			if(user.getName() != null)
				currentUser.setName(user.getName());
			if(user.getPassword() != null)
				currentUser.setPassword(Utils.encriptPasswword(user.getPassword()));
			if(user.getRole() != null)
				currentUser.setRole(user.getRole());
			
			userService.save(currentUser);
		}catch(DataAccessException e) {
			response.put("message", "Error to update the user");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("message", "User has been updated!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Users currentUser = this.userService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(currentUser == null) {
			response.put("message", "User with ID(".concat(id.toString()
					.concat(") doesn't exist in the database!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			userService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error to delete the user");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "User has been deleted!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}