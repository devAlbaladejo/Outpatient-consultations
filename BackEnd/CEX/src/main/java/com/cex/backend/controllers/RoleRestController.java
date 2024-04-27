package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Role;
import com.cex.backend.models.services.IRoleService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class RoleRestController {

	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/roles")
	public List<Role> showRoles(){
		return roleService.findAll();
	}
}
