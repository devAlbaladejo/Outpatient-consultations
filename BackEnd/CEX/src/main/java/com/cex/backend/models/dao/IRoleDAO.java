package com.cex.backend.models.dao;

/*
 * @author Ángel Albaladejo Flores
 */


import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.backend.models.entity.Role;

public interface IRoleDAO extends JpaRepository<Role, Long> {

}
