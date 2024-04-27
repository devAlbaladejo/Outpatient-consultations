package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Role;

public interface IRoleService {

	public List<Role> findAll();
}
